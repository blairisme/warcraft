package com.evilbird.warcraft.desktop;

import com.evilbird.engine.device.Device;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DesktopModule
{
    @Provides
    @Singleton
    public static Device provideDevice()
    {
        return new DesktopDevice();
    }
}
