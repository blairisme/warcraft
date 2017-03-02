package com.evilbird.warcraft;

import com.evilbird.engine.device.Device;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AndroidModule
{
    @Provides
    @Singleton
    public static Device provideDevice()
    {
        return new AndroidDevice();
    }
}
