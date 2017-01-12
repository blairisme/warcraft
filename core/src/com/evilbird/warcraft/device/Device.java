package com.evilbird.warcraft.device;

public interface Device
{
    DeviceInput getDeviceInput();

    DeviceStorage getAssetStorage();

    DeviceStorage getInternalStorage();
}
