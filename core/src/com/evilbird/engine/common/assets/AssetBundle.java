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
import com.evilbird.engine.common.audio.LazyLoadedMusic;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.audio.SoundUtils;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.common.collection.SuppliedMap;
import com.evilbird.engine.common.function.ParameterizedSupplier;
import com.evilbird.engine.common.graphics.TextureUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.text.StringSubstitutor;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

    public AssetBundle(AssetManager assetManager, ParameterizedSupplier<String, String> pathProperties) {
        this(assetManager, new SuppliedMap<>(pathProperties));
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

    protected void registerOptional(Object id, String path, Class<?> type) {
        String file = resolver.replace(path);
        FileHandleResolver fileResolver = manager.getFileHandleResolver();
        FileHandle fileHandle = fileResolver.resolve(file);
        if (fileHandle.exists()) {
            register(id, file, type);
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

    protected SoundEffect getSoundEffect(Object id) {
        AssetDescriptor asset = assets.get(id);
        return SoundUtils.newSoundEffect(manager, asset.fileName);
    }

    protected SoundEffect getSoundEffectSet(Object ... ids) {
        Collection<AssetDescriptor> effects = Maps.getAll(assets, Arrays.asList(ids));
        Collection<String> paths = effects.stream().map(desc -> desc.fileName).collect(toList());
        return SoundUtils.newSoundEffect(manager, paths);
    }

    protected I18NBundle getStrings(Object id) {
        AssetDescriptor asset = assets.get(id);
        return manager.get(asset.fileName, I18NBundle.class);
    }

    protected Texture getTexture(Object id) {
        AssetDescriptor asset = assets.get(id);
        return manager.get(asset.fileName, Texture.class);
    }

    protected SyntheticTexture getSyntheticTexture(Object id) {
        AssetDescriptor asset = assets.get(id);
        return manager.get(asset.fileName, SyntheticTexture.class);
    }
}
