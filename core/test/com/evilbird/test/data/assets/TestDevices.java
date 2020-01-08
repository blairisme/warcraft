/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.test.data.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceDisplay;
import org.mockito.Mockito;

import static com.evilbird.test.data.assets.TestAssets.newAssetManagerMock;

public class TestDevices
{
    private TestDevices() {
    }

    public static Device newTestDevice() {
        AssetManager assets = newAssetManagerMock();
        DeviceDisplay display = Mockito.mock(DeviceDisplay.class);
        Mockito.when(display.getScaleFactor()).thenReturn(1f);

        Device device = Mockito.mock(Device.class);
        Mockito.when(device.getAssetStorage()).thenReturn(assets);
        Mockito.when(device.getDeviceDisplay()).thenReturn(display);

        return device;
    }
}
