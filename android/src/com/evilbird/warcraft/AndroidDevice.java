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
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceInput;
import com.evilbird.engine.device.DeviceStorage;

public class AndroidDevice implements Device
{
    private DeviceInput input;
    private DeviceStorage storage;
    private AssetManager assets;

    public AndroidDevice() {
        this.input = new AndroidInput();
        this.storage = new AndroidStorage();
        this.assets = new AndroidAssets();
    }

    @Override
    public DeviceInput getDeviceInput() {
        return input;
    }

    @Override
    public DeviceStorage getDeviceStorage() {
        return storage;
    }

    @Override
    public AssetManager getAssetStorage() {
        return assets;
    }
}
