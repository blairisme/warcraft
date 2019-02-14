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

public class IOSDevice implements Device
{
    private DeviceInput input;
    private DeviceStorage storage;

    public IOSDevice() {
        this.input = new IOSInput();
        this.storage = new IOSStorage();
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
        throw new UnsupportedOperationException();
    }
}
