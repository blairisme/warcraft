/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft;

import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceInput;
import com.evilbird.engine.device.DeviceStorage;

public class AndroidDevice implements Device
{
    private DeviceInput input;
    private DeviceStorage storage;

    public AndroidDevice() {
        this.input = new AndroidInput();
        this.storage = new AndroidStorage();
    }

    @Override
    public DeviceInput getDeviceInput() {
        return input;
    }

    @Override
    public DeviceStorage getAssetStorage() {
        return storage;
    }

    @Override
    public DeviceStorage getInternalStorage() {
        return null;
    }
}
