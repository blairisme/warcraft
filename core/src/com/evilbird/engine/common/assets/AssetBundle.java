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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.engine.common.collection.SuppliedMap;
import com.evilbird.engine.common.function.ParameterizedSupplier;
import com.evilbird.engine.common.graphics.TextureUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.text.StringSubstitutor;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
        register(FilenameUtils.getName(path), path, type, null);
    }

    protected <T> void register(Object id, String path, Class<T> type, AssetLoaderParameters<T> parameters) {
        String file = resolver.replace(path);
        assets.put(id, new AssetDescriptor<>(file, type, parameters));
    }

    protected Drawable getDrawable(Object id) {
        AssetDescriptor asset = assets.get(id);
        return TextureUtils.getDrawable(manager, asset.fileName);
    }

    protected Drawable getDrawable(Object id, int x, int y, int width, int height) {
        AssetDescriptor asset = assets.get(id);
        return TextureUtils.getDrawable(manager, asset.fileName, x, y, width, height);
    }

    protected BitmapFont getFont(Object id) {
        AssetDescriptor asset = assets.get(id);
        return manager.get(asset.fileName, BitmapFont.class);
    }

    protected I18NBundle getStrings(Object id) {
        AssetDescriptor asset = assets.get(id);
        return manager.get(asset.fileName, I18NBundle.class);
    }
}
