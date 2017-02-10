package com.evilbird.engine.device;

public interface Device
{
    DeviceInput getDeviceInput();

    DeviceStorage getAssetStorage();

    DeviceStorage getInternalStorage();
}
