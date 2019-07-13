/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.evilbird.engine.common.error.UnknownEntityException;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.serialization.JsonSerializer;
import com.evilbird.engine.common.serialization.Serializer;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceStorage;
import com.evilbird.engine.state.State;
import com.evilbird.engine.state.StateIdentifier;
import com.evilbird.engine.state.StateLoadError;
import com.evilbird.engine.state.StateService;
import com.evilbird.engine.state.StateType;
import com.evilbird.warcraft.state.campaign.Campaign;
import com.evilbird.warcraft.state.scenario.ScenarioState;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Instances of this {@link StateService} provide access to {@link ScenarioState
 * WarcraftStates}, snapshots of all game objects and their properties at a
 * given point in time.
 *
 * @author Blair Butterworth
 */
public class WarcraftStateService implements StateService
{
    private static final String SAVES = "saves";

    private Serializer serializer;
    private DeviceStorage deviceStorage;
    private FileHandleResolver assetStorage;

    @Inject
    public WarcraftStateService(Device device, JsonSerializer serializer) {
        this.serializer = serializer;
        this.deviceStorage = device.getDeviceStorage();
        this.assetStorage = new InternalFileHandleResolver();
    }

    public WarcraftStateService(
        DeviceStorage deviceStorage,
        FileHandleResolver assetStorage,
        JsonSerializer serializer)
    {
        this.serializer = serializer;
        this.assetStorage = assetStorage;
        this.deviceStorage = deviceStorage;
    }

    @Override
    public void load() {
    }

    @Override
    public List<Identifier> list(StateType type) {
        if (type == WarcraftStateType.AssetState) {
            return listScenarios();
        }
        if (type == WarcraftStateType.UserState) {
            return listSaves();
        }
        throw new UnknownEntityException(type);
    }

    private List<Identifier> listScenarios() {
        return Arrays.asList(Campaign.values());
    }

    private List<Identifier> listSaves() {
        try {
            List<Identifier> result = new ArrayList<>();
            for (String path : deviceStorage.list(SAVES)) {
                result.add(toState(path));
            }
            return result;
        }
        catch (IOException error){
            throw new StateLoadError(error);
        }
    }

    @Override
    public State get(StateIdentifier identifier) {
        Validate.isInstanceOf(WarcraftStateIdentifier.class, identifier);
        return get((WarcraftStateIdentifier)identifier);
    }

    private State get(WarcraftStateIdentifier identifier) {
        if (identifier instanceof WarcraftSave) {
            return getSave((WarcraftSave)identifier);
        }
        return getScenario(identifier);
    }

    private State getScenario(WarcraftStateIdentifier identifier) {
        try (Reader reader = assetStorage.resolve(identifier.getFilePath()).reader()) {
            return serializer.deserialize(reader, WarcraftState.class);
        }
        catch (IOException error){
            throw new StateLoadError(error);
        }
    }

    private State getSave(WarcraftSave identifier) {
        try (Reader reader = deviceStorage.read(toPath(identifier))) {
            return serializer.deserialize(reader, WarcraftState.class);
        }
        catch (IOException error){
            throw new StateLoadError(error);
        }
    }

    @Override
    public void set(StateIdentifier identifier, State state) {
        Validate.isInstanceOf(ScenarioState.class, state);
        Validate.isInstanceOf(WarcraftSave.class, identifier);

        try (Writer writer = deviceStorage.write(toPath((WarcraftSave)identifier))) {
            serializer.serialize((ScenarioState)state, ScenarioState.class, writer);
        }
        catch (IOException error){
            throw new StateLoadError(error);
        }
    }

    @Override
    public void remove(StateIdentifier identifier) {
        Validate.isInstanceOf(WarcraftSave.class, identifier);
        try {
            deviceStorage.remove(toPath((WarcraftSave)identifier));
        }
        catch (IOException error){
            throw new StateLoadError(error);
        }
    }

    private WarcraftSave toState(String path) {
        return new WarcraftSave(FilenameUtils.getBaseName(path));
    }

    private String toPath(WarcraftSave identifier) {
        return identifier.getFilePath();
    }
}
