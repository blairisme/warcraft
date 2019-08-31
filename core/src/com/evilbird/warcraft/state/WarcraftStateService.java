/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.evilbird.engine.common.error.UnknownEntityException;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.serialization.JsonSerializer;
import com.evilbird.engine.common.serialization.Serializer;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceStorage;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.state.ApplicationState;
import com.evilbird.engine.state.State;
import com.evilbird.engine.state.StateIdentifier;
import com.evilbird.engine.state.StateLoadError;
import com.evilbird.engine.state.StateService;
import com.evilbird.engine.state.StateType;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Instances of this {@link StateService} provide access to {@link WarcraftState
 * WarcraftStates}, snapshots of all game objects and their properties at a
 * given point in time.
 *
 * @author Blair Butterworth
 */
public class WarcraftStateService implements StateService
{
    private static final String SAVE_DIRECTORY = "saves";
    private static final String ASSET_DIRECTORY = "data/levels";

    private Serializer serializer;
    private FileHandleResolver deviceStorage;
    private FileHandleResolver assetStorage;

    @Inject
    public WarcraftStateService(Device device, JsonSerializer serializer) {
        this(device.getDeviceStorage(), device.getAssetStorage(), serializer);
    }

    public WarcraftStateService(DeviceStorage devices, AssetManager assets, JsonSerializer serializer) {
        this(devices.getFileHandleResolver(), assets.getFileHandleResolver(), serializer);
    }

    public WarcraftStateService(FileHandleResolver storage, FileHandleResolver assets, JsonSerializer serializer) {
        this.serializer = serializer;
        this.deviceStorage = storage;
        this.assetStorage = assets;
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
        return Arrays.asList(WarcraftCampaign.values());
    }

    private List<Identifier> listSaves() {
        try {
            return saveFiles().stream().map(WarcraftSave::new).collect(toList());
        }
        catch (Throwable error){
            throw new StateLoadError(error);
        }
    }

    @Override
    public GameContext context(StateIdentifier identifier) {
        FileHandle handle = resolve(identifier);
        try (Reader reader = handle.reader()) {
            return serializer.deserialize(reader, WarcraftContext.class);
        }
        catch (IOException error){
            throw new StateLoadError(error);
        }
    }

    @Override
    public State get(StateIdentifier identifier) {
        FileHandle handle = resolve(identifier);
        try (Reader reader = handle.reader()) {
            return serializer.deserialize(reader, WarcraftState.class);
        }
        catch (IOException error){
            throw new StateLoadError(error);
        }
    }

    @Override
    public void set(StateIdentifier identifier, State state) {
        FileHandle handle = resolve(identifier);
        try {
            File file = handle.file();
            if (! file.exists()) {
                File parent = file.getParentFile();
                parent.mkdirs();
                file.createNewFile();
            }
        }
        catch (IOException error){
            throw new StateLoadError(error);
        }
        try (Writer writer = handle.writer(false)) {
            serializer.serialize((WarcraftState)state, WarcraftState.class, writer);
        }
        catch (IOException error){
            throw new StateLoadError(error);
        }
    }

    @Override
    public void remove(StateIdentifier identifier) {
        try {
            FileHandle handle = resolve(identifier);
            handle.delete();
        }
        catch (Throwable error) {
            throw new StateLoadError(error);
        }
    }

    private FileHandle resolve(StateIdentifier identifier) {
        if (identifier instanceof ApplicationState) {
            WarcraftSave save = new WarcraftSave((ApplicationState)identifier);
            return saveFile(save.getFileName());
        }
        if (identifier instanceof WarcraftSave) {
            WarcraftSave save = (WarcraftSave)identifier;
            return saveFile(save.getFileName());
        }
        if (identifier instanceof WarcraftCampaign) {
            WarcraftCampaign campaign = (WarcraftCampaign)identifier;
            return assetFile(campaign.getFactionName(), campaign.getFileName());
        }
        throw new IllegalArgumentException();
    }

    private Collection<FileHandle> saveFiles() {
        FileHandle saves = deviceStorage.resolve(SAVE_DIRECTORY);
        return Arrays.asList(saves.list());
    }

    private FileHandle saveFile(String name) {
        FileHandle saves = deviceStorage.resolve(SAVE_DIRECTORY);
        return saves.child(name);
    }

    private FileHandle assetFile(String parent, String name) {
        FileHandle assets = assetStorage.resolve(ASSET_DIRECTORY);
        FileHandle faction = assets.child(parent);
        return faction.child(name);
    }
}
