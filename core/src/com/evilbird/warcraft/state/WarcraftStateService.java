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
import com.evilbird.engine.common.file.FileType;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.serialization.JsonSerializer;
import com.evilbird.engine.common.serialization.Serializer;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceStorage;
import com.evilbird.engine.state.State;
import com.evilbird.engine.state.StateIdentifier;
import com.evilbird.engine.state.StateService;
import com.evilbird.engine.state.StateType;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Instances of this {@link StateService} provide access to {@link WarcraftState
 * WarcraftStates}, snapshots of all game objects and their properties at a
 * given point in time.
 *
 * @author Blair Butterworth
 */
public class WarcraftStateService implements StateService
{
    private static final String SAVES = "saves";
    private static final String JSON = FileType.JSON.getFileExtension();

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
    public List<Identifier> list(StateType type) throws IOException {
        if (type == WarcraftStateType.AssetState) {
            return listScenarios();
        }
        if (type == WarcraftStateType.UserState) {
            return listSaves();
        }
        throw new UnknownEntityException(type);
    }

    private List<Identifier> listScenarios() {
        return Arrays.asList(WarcraftStateScenario.values());
    }

    private List<Identifier> listSaves() throws IOException {
        List<Identifier> result = new ArrayList<>();
        for (String path: deviceStorage.list(SAVES)) {
            result.add(toState(path));
        }
        return result;
    }

    @Override
    public State get(StateIdentifier identifier) throws IOException {
        if (identifier instanceof WarcraftStateScenario) {
            return getScenario((WarcraftStateScenario)identifier);
        }
        if (identifier instanceof WarcraftStateIdentifier) {
            return getSave((WarcraftStateIdentifier)identifier);
        }
        throw new UnknownEntityException(identifier);
    }

    private State getScenario(WarcraftStateScenario identifier) throws IOException {
        try (Reader reader = assetStorage.resolve(identifier.getFilePath()).reader()) {
            return serializer.deserialize(reader, WarcraftState.class);
        }
    }

    private State getSave(WarcraftStateIdentifier identifier) throws IOException {
        try (Reader reader = deviceStorage.read(toPath(identifier))) {
            return serializer.deserialize(reader, WarcraftState.class);
        }
    }

    @Override
    public void set(StateIdentifier identifier, State state) throws IOException {
        Validate.isInstanceOf(WarcraftState.class, state);
        Validate.isInstanceOf(WarcraftStateIdentifier.class, identifier);

        try (Writer writer = deviceStorage.write(toPath((WarcraftStateIdentifier)identifier))) {
            serializer.serialize((WarcraftState)state, WarcraftState.class, writer);
        }
    }

    @Override
    public void remove(StateIdentifier identifier) throws IOException {
        Validate.isInstanceOf(WarcraftStateIdentifier.class, identifier);
        deviceStorage.remove(toPath((WarcraftStateIdentifier)identifier));
    }

    private WarcraftStateIdentifier toState(String path) {
        return new WarcraftStateIdentifier(FilenameUtils.getBaseName(path));
    }

    private String toPath(WarcraftStateIdentifier identifier) {
        return SAVES + File.separator + identifier.getName() + JSON;
    }
}
