/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.ios;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceControls;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.device.DeviceInput;
import com.evilbird.engine.device.DeviceStorage;

import javax.inject.Inject;

/**
 * Represents a device running Apple iOS devices. Methods are provided that
 * supply access to device input and storage mediums.
 *
 * @author Blair Butterworth
 */
public class IosDevice implements Device
{
    private AssetManager assets;
    private DeviceInput input;
    private DeviceStorage storage;
    private DeviceDisplay display;
    private DeviceControls controls;

    @Inject
    public IosDevice() {
        this.assets = new IosAssets();
        this.input = new IosInput();
        this.storage = new IosStorage();
        this.display = new IosDisplay();
        this.controls = new IosControls();
    }

    @Override
    public DeviceControls getDeviceControls() {
        return controls;
    }

    @Override
    public DeviceDisplay getDeviceDisplay() {
        return display;
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
