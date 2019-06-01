/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.desktop;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceControls;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.device.DeviceInput;
import com.evilbird.engine.device.DeviceStorage;

/**
 * Represents a device running Microsoft Windows or Apple MacOS. Methods are
 * provided that supply access to device input and storage mediums.
 *
 * @author Blair Butterworth
 */
public class DesktopDevice implements Device
{
    private DeviceControls controls;
    private DeviceDisplay display;
    private DeviceInput input;
    private DeviceStorage storage;
    private AssetManager assets;

    public DesktopDevice() {
        this.input = new DesktopInput();
        this.assets = new DesktopAssets();
        this.storage = new DesktopStorage();
        this.display = new DesktopDisplay();
        this.controls = new DesktopControls();
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
    public AssetManager getAssetStorage() {
        return assets;
    }

    @Override
    public DeviceStorage getDeviceStorage() {
        return storage;
    }
}
