package com.evilbird.warcraft;

import com.evilbird.engine.device.Device;

import dagger.Module;
import dagger.Provides;

@Module
public class AndroidModule
{
    @Provides
    public static Device provideDevice()
    {
        return new AndroidDevice();
    }
}
