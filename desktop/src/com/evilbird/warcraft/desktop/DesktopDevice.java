/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.desktop;

import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceInput;
import com.evilbird.engine.device.DeviceStorage;

public class DesktopDevice implements Device
{
    private DeviceInput input;
    private DeviceStorage assets;

    public DesktopDevice() {
        this.input = new DesktopInput();
        this.assets = new DesktopStorage();
    }

    @Override
    public DeviceInput getDeviceInput() {
        return input;
    }

    @Override
    public DeviceStorage getAssetStorage() {
        return assets;
    }

    @Override
    public DeviceStorage getInternalStorage() {
        throw new UnsupportedOperationException();
    }
}
