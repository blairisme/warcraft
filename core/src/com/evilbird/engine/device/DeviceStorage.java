/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.device;

import com.badlogic.gdx.assets.AssetManager;

import java.io.IOException;
import java.util.List;

public interface DeviceStorage
{
    boolean delete(String path)  throws IOException;

    boolean exists(String path)  throws IOException;

    List<String> list(String path)  throws IOException;

    <T> T read(String path)  throws IOException;

    <T> void write(T object, String path)  throws IOException;


    AssetManager getAssets(); //TODO - Figure our what to do with this - remove/merge?
}
