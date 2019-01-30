/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.desktop;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.evilbird.engine.device.DeviceStorage;

import java.io.IOException;
import java.util.Collection;

public class DesktopStorage implements DeviceStorage
{
    private AssetManager assetManager;

    public DesktopStorage() {
        this.assetManager = new AssetManager();
        this.assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
    }

    public AssetManager getAssets() {
        return assetManager;
    }

    @Override
    public <T> T read(String path) throws IOException {
        return assetManager.get(path);
    }

    @Override
    public boolean delete(String path) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean exists(String path) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<String> list() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> void write(T object, String path) throws IOException {
        throw new UnsupportedOperationException();
    }
}
