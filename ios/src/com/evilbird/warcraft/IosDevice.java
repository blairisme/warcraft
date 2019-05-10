/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceInput;
import com.evilbird.engine.device.DeviceStorage;

/**
 * Represents a device running Apple iOS devices. Methods are provided that
 * supply access to device input and storage mediums.
 *
 * @author Blair Butterworth
 */
public class IosDevice implements Device
{
    private DeviceInput input;
    private DeviceStorage storage;


    public IosDevice() {
        this.input = new IosInput();
        this.storage = new IosStorage();
    }

    @Override
    public DeviceInput getDeviceInput() {
        return input;
    }

    @Override
    public DeviceStorage getDeviceStorage() {
        throw new UnsupportedOperationException();
    }

    @Override
    public AssetManager getAssetStorage() {
        throw new UnsupportedOperationException();
    }
}
