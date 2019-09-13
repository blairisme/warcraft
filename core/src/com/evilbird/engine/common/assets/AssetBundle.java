/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.engine.common.audio.music.LazyLoadedMusic;
import com.evilbird.engine.common.audio.sound.SilentSound;
import com.evilbird.engine.common.audio.sound.Sound;
import com.evilbird.engine.common.audio.sound.SoundFactory;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.common.graphics.TextureUtils;
import com.evilbird.engine.common.text.StringSubstitutor;
import org.apache.commons.io.FilenameUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.badlogic.gdx.graphics.Pixmap.Format.RGBA8888;
import static java.util.stream.Collectors.toList;

/**
 * Represents a collection of assets.
 *
 * @author Blair Butterworth
 */
public class AssetBundle
{
    private AssetManager manager;
    private StringSubstitutor resolver;
    private Map<Object, AssetDescriptor> assets;

    public AssetBundle(AssetManager assetManager) {
        this(assetManager, Collections.emptyMap());
    }

    public AssetBundle(AssetManager assetManager, Map<String, String> pathProperties) {
        this.manager = assetManager;
        this.assets = new HashMap<>();
        this.resolver = new StringSubstitutor(pathProperties);
    }

    public Collection<AssetDescriptor> getAssets() {
        return Collections.unmodifiableCollection(assets.values());
    }

    public void load() {
        for (AssetDescriptor descriptor: assets.values()) {
            if (!manager.isLoaded(descriptor)){
                manager.load(descriptor);
            }
        }
    }

    public void loadSynchronous() {
        load();
        manager.finishLoading();
    }

    public void unload() {
        for (AssetDescriptor descriptor: assets.values()) {
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

    protected void registerSequence(String idPrefix, String pathPrefix, String pathSuffix, int count) {
        for (int i = 1; i < count + 1; i++) {
            registerOptional(idPrefix + "-" + i, pathPrefix + i + pathSuffix);
        }
    }

    protected boolean isRegistered(Object id) {
        return assets.containsKey(id);
    }

    protected Drawable getDrawable(Object id) {
        AssetDescriptor asset = assets.get(id);
        return TextureUtils.getDrawable(manager, asset.fileName);
    }

    protected Drawable getDrawable(Object id, int x, int y, int width, int height) {
        AssetDescriptor asset = assets.get(id);
        return TextureUtils.getDrawable(manager, asset.fileName, x, y, width, height);
    }

    protected Drawable getTiledDrawable(Object id) {
        AssetDescriptor asset = assets.get(id);
        return TextureUtils.getTiledDrawable(manager, asset.fileName);
    }

    protected BitmapFont getFont(Object id) {
        AssetDescriptor asset = assets.get(id);
        return manager.get(asset.fileName, BitmapFont.class);
    }

    protected Music getMusic(Object id) {
        AssetDescriptor asset = assets.get(id);
        return manager.get(asset.fileName, Music.class);
    }

    protected Music getLazyLoadedMusic(Object id) {
        AssetDescriptor asset = assets.get(id);
        return manager.get(asset.fileName, LazyLoadedMusic.class);
    }

    protected Sound getSoundEffect(Object id) {
        AssetDescriptor asset = assets.get(id);
        return SoundFactory.newSound(manager, asset.fileName);
    }

    protected Sound getOptionalSoundEffect(Object id) {
        return isRegistered(id) ? getSoundEffect(id) : new SilentSound();
    }

    protected Sound getSoundEffectSet(String prefix, int count) {
        List<String> paths = new ArrayList<>();
        for (int i = 1; i < count + 1; i++) {
            String id = prefix + "-" + i;
            if (assets.containsKey(id)) {
                AssetDescriptor descriptor = assets.get(id);
                paths.add(descriptor.fileName);
            }
        }
        return SoundFactory.newSound(manager, paths);
    }

    protected Sound getSoundEffectSet(Object ... ids) {
        Collection<AssetDescriptor> effects = Maps.getAll(assets, Arrays.asList(ids));
        effects.removeIf(Objects::isNull);
        Collection<String> paths = effects.stream().map(desc -> desc.fileName).collect(toList());
        return SoundFactory.newSound(manager, paths);
    }

    protected I18NBundle getStrings(Object id) {
        AssetDescriptor asset = assets.get(id);
        return manager.get(asset.fileName, I18NBundle.class);
    }

    protected Texture getTexture(Object id) {
        AssetDescriptor asset = assets.get(id);
        return manager.get(asset.fileName, Texture.class);
    }

    protected Texture getOptionalTexture(Object id) {
        return isRegistered(id) ? getTexture(id) : new Texture(0, 0, RGBA8888);
    }

    protected SyntheticTexture getSyntheticTexture(Object id) {
        AssetDescriptor asset = assets.get(id);
        return manager.get(asset.fileName, SyntheticTexture.class);
    }
}
