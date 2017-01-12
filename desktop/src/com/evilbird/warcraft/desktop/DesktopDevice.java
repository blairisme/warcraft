package com.evilbird.warcraft.desktop;

import com.evilbird.warcraft.device.Device;
import com.evilbird.warcraft.device.DeviceInput;
import com.evilbird.warcraft.device.DeviceStorage;

public class DesktopDevice implements Device
{
    private DeviceInput input;
    private DeviceStorage assets;

    public DesktopDevice()
    {
        this.input = new DesktopInput();
        this.assets = new DesktopStorage();
    }

    @Override
    public DeviceInput getDeviceInput()
    {
        return input;
    }

    @Override
    public DeviceStorage getAssetStorage()
    {
        return assets;
    }

    @Override
    public DeviceStorage getInternalStorage()
    {
        throw new UnsupportedOperationException();
    }
}
