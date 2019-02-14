/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.device.DeviceStorage;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class IOSStorage implements DeviceStorage
{
    @Override
    public boolean delete(String path) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean exists(String path) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> list(String path) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T read(String path) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> void write(T object, String path) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public AssetManager getAssets() {
        throw new UnsupportedOperationException();
    }
}
