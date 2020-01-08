/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
        this.assets = new DesktopAssets();
        this.storage = new DesktopStorage();
        this.display = new DesktopDisplay();
        this.input = new DesktopInput(display);
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
