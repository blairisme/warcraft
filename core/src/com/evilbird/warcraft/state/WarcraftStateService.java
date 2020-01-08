/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.state;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.common.error.UnknownEntityException;
import com.evilbird.engine.common.file.FileUtils;
import com.evilbird.engine.common.serialization.JsonSerializer;
import com.evilbird.engine.common.serialization.Serializer;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceStorage;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameState;
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
    public List<StateIdentifier> list(StateType type) {
        if (type == WarcraftStateType.AssetState) {
            return listScenarios();
        }
        if (type == WarcraftStateType.UserState) {
            return listSaves();
        }
        throw new UnknownEntityException(type);
    }

    private List<StateIdentifier> listScenarios() {
        return Arrays.asList(WarcraftCampaign.values());
    }

    private List<StateIdentifier> listSaves() {
        try {
            return CollectionUtils.convert(saveFiles(), WarcraftSave::new);
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
        FileHandle handle = create(identifier);
        try (Writer writer = handle.writer(false)) {
            serializer.serialize((WarcraftState)state, WarcraftState.class, writer);
        }
        catch (IOException error) {
            FileUtils.deleteQuietly(handle.file());
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

    protected FileHandle create(StateIdentifier identifier) {
        FileHandle handle = resolve(identifier);
        create(handle);
        return handle;
    }

    protected boolean create(FileHandle handle) {
        try {
            File file = handle.file();
            if (! file.exists()) {
                File parent = file.getParentFile();
                return parent.mkdirs() && file.createNewFile();
            }
            return true;
        }
        catch (IOException error){
            throw new StateLoadError(error);
        }
    }

    protected FileHandle resolve(StateIdentifier identifier) {
        if (identifier instanceof GameState) {
            WarcraftSave save = new WarcraftSave((GameState)identifier);
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
        if (identifier instanceof WarcraftScenario) {
            WarcraftScenario scenario = (WarcraftScenario)identifier;
            return assetFile(scenario.getRoot(), scenario.getFile());
        }
        throw new IllegalArgumentException();
    }

    protected FileHandle saveFile(String name) {
        FileHandle saves = deviceStorage.resolve(SAVE_DIRECTORY);
        return saves.child(name);
    }

    protected Collection<FileHandle> saveFiles() {
        FileHandle directory = deviceStorage.resolve(SAVE_DIRECTORY);
        Collection<FileHandle> saves = Arrays.asList(directory.list());
        return CollectionUtils.filter(saves, this::isValidSave);
    }

    protected boolean isValidSave(FileHandle handle) {
        if (handle != null) {
            String extension = handle.extension();
            return extension.equalsIgnoreCase(WarcraftSave.getExtension());
        }
        return false;
    }

    protected FileHandle assetFile(String parent, String name) {
        FileHandle assets = assetStorage.resolve(ASSET_DIRECTORY);
        FileHandle directory = assets.child(parent);
        return directory.child(name);
    }
}
