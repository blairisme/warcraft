package com.evilbird.warcraft.desktop;

import com.evilbird.engine.device.Device;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

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
