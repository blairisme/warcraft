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
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class AndroidModule
{
    @Provides
    @Singleton
    public static Device provideDevice() {
        return new AndroidDevice();
    }
}
