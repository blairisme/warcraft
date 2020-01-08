/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.engine.audio.music.Music;
import com.evilbird.engine.audio.music.MusicFile;
import com.evilbird.engine.audio.music.MusicSequence;
import com.evilbird.engine.audio.sound.RandomSound;
import com.evilbird.engine.audio.sound.Sound;
import com.evilbird.engine.common.file.FilenameUtils;
import com.evilbird.engine.common.graphics.DrawableUtils;
import com.evilbird.engine.common.text.StringSubstitutor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.badlogic.gdx.graphics.Pixmap.Format.RGBA8888;
import static com.evilbird.engine.audio.sound.SilentSound.SilentSoundEffect;

/**
 * Represents a collection of assets.
 *
 * @author Blair Butterworth
 */
public class AssetBundle
{
    protected AssetManager manager;
    protected StringSubstitutor resolver;
    protected Map<Object, AssetDescriptor<?>> assets;

    /**
     * Constructs a new instance of this class given an {@link AssetManager}
     * from which assets will be loaded and loaded.
     *
     * @param manager   an asset manager used to load and unload assets.
     *
     * @throws IllegalArgumentException if the given asset manager is
     *                                  {@code null}.
     */
    public AssetBundle(AssetManager manager) {
        this(manager, Collections.emptyMap());
    }

    /**
     * Constructs a new instance of this class given an {@link AssetManager},
     * from which assets will be obtained, and a map of properties, whose
     * values will be substituted for tokens found in registered asset paths.
     *
     * @param manager       an asset manager used to load and unload assets.
     * @param properties    a map of key value pairs.
     *
     * @throws IllegalArgumentException if either the given asset manager or
     *                                  property map is {@code null}.
     */
    public AssetBundle(AssetManager manager, Map<String, String> properties) {
        Objects.requireNonNull(manager);
        Objects.requireNonNull(properties);

        this.manager = manager;
        this.assets = new HashMap<>();
        this.resolver = new StringSubstitutor(properties);
    }

    /**
     * Returns a collection of all registered assets.
     */
    public Collection<AssetDescriptor<?>> getAssets() {
        return Collections.unmodifiableCollection(assets.values());
    }

    /**
     * Asynchronously loads all registered assets.
     */
    public void load() {
        for (AssetDescriptor<?> descriptor: assets.values()) {
            if (!manager.isLoaded(descriptor)){
                manager.load(descriptor);
            }
        }
    }

    /**
     * Synchronously loads all registered assets.
     */
    public void loadSynchronous() {
        load();
        manager.finishLoading();
    }

    /**
     * Synchronously unloads all loaded assets.
     */
    public void unload() {
        for (AssetDescriptor<?> descriptor: assets.values()) {
            if (manager.isLoaded(descriptor)) {
                manager.unload(descriptor.fileName);
            }
        }
    }

    protected void register(String path) {
        register(path, AssetLoaders.getAssetLoader(path));
    }

    protected void register(String path, Class<?> type) {
        register(FilenameUtils.getName(path), path, type);
    }

    protected void register(Object id, String path) {
        register(id, path, AssetLoaders.getAssetLoader(path));
    }

    protected void register(Object id, String path, Class<?> type) {
        register(id, path, type, null);
    }

    protected <T> void register(Object id, String path, Class<T> type, AssetLoaderParameters<T> parameters) {
        String file = resolver.replace(path);
        assets.put(id, new AssetDescriptor<>(file, type, parameters));
    }

    protected void registerOptional(Object id, String path) {
        registerOptional(id, path, AssetLoaders.getAssetLoader(path));
    }

    protected void registerOptional(Object id, String path, Class<?> type) {
        String file = resolver.replace(path);
        FileHandleResolver fileResolver = manager.getFileHandleResolver();
        FileHandle fileHandle = fileResolver.resolve(file);
        if (fileHandle.exists()) {
            register(id, file, type);
        }
    }

    protected void registerOptionalSequence(String idPrefix, String pathPrefix, String pathSuffix, int count) {
        for (int i = 1; i < count + 1; i++) {
            registerOptional(idPrefix + "-" + i, pathPrefix + i + pathSuffix);
        }
    }

    protected void registerSequence(String idPrefix, String pathPrefix, String pathSuffix, int count) {
        for (int i = 1; i < count + 1; i++) {
            register(idPrefix + "-" + i, pathPrefix + i + pathSuffix);
        }
    }

    protected void registerSequence(String idPrefix, String pathPrefix, String pathSuffix, int count, Class<?> type) {
        for (int i = 1; i < count + 1; i++) {
            register(idPrefix + "-" + i, pathPrefix + i + pathSuffix, type);
        }
    }

    protected boolean isRegistered(Object id) {
        return assets.containsKey(id);
    }

    protected boolean isSetRegistered(Object id) {
        return assets.containsKey(id + "-1");
    }

    protected Drawable getDrawable(Object id) {
        AssetDescriptor<?> asset = assets.get(id);
        return DrawableUtils.getDrawable(manager, asset.fileName);
    }

    protected Drawable getDrawable(Object id, int x, int y, int width, int height) {
        AssetDescriptor<?> asset = assets.get(id);
        return DrawableUtils.getDrawable(manager, asset.fileName, x, y, width, height);
    }

    protected Drawable getTiledDrawable(Object id) {
        AssetDescriptor<?> asset = assets.get(id);
        return DrawableUtils.getTiledDrawable(manager, asset.fileName);
    }

    protected BitmapFont getFont(Object id) {
        AssetDescriptor<?> asset = assets.get(id);
        return manager.get(asset.fileName, BitmapFont.class);
    }

    protected Music getMusic(Object id) {
        return getLazyLoadedMusic(id);
    }

    protected Music getLazyLoadedMusic(Object id) {
        AssetDescriptor<?> asset = assets.get(id);
        FileHandleResolver resolver = manager.getFileHandleResolver();
        return new MusicFile(resolver, asset.fileName);
    }

    protected Music getLazyLoadedMusicSequence(String prefix, int count) {
        List<Music> sequence = new ArrayList<>(count);
        for (int i = 1; i < count + 1; i++) {
            String id = prefix + "-" + i;
            if (assets.containsKey(id)) {
                sequence.add(getLazyLoadedMusic(id));
            }
        }
        return new MusicSequence(sequence);
    }

    protected Sound getSoundEffect(Object id) {
        AssetDescriptor<?> asset = assets.get(id);
        return manager.get(asset.fileName, Sound.class);
    }

    protected Sound getOptionalSoundEffect(Object id) {
        return isRegistered(id) ? getSoundEffect(id) : SilentSoundEffect;
    }

    protected Sound getSoundEffectSet(String prefix, int count) {
        List<String> paths = new ArrayList<>(count);
        for (int i = 1; i < count + 1; i++) {
            String id = prefix + "-" + i;
            if (assets.containsKey(id)) {
                AssetDescriptor<?> descriptor = assets.get(id);
                paths.add(descriptor.fileName);
            }
        }
        Collection<Sound> effects = new ArrayList<>(paths.size());
        for (String path : paths) {
            effects.add(manager.get(path, Sound.class));
        }
        return new RandomSound(effects);
    }

    protected I18NBundle getStrings(Object id) {
        AssetDescriptor<?> asset = assets.get(id);
        return manager.get(asset.fileName, I18NBundle.class);
    }

    protected Texture getTexture(Object id) {
        AssetDescriptor<?> asset = assets.get(id);
        return manager.get(asset.fileName, Texture.class);
    }

    protected TextureRegion getTexture(Object id, int x, int y, int width, int height) {
        Texture texture = getTexture(id);
        return new TextureRegion(texture, x, y, width, height);
    }

    protected Texture getOptionalTexture(Object id) {
        return isRegistered(id) ? getTexture(id) : new Texture(0, 0, RGBA8888);
    }

    protected SyntheticTexture getSyntheticTexture(Object id) {
        AssetDescriptor<?> asset = assets.get(id);
        return manager.get(asset.fileName, SyntheticTexture.class);
    }
}
