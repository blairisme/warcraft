/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft;

import com.evilbird.engine.device.Device;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Defines the bindings between the device services and their Android
 * implementations.
 *
 * @author Blair Butterworth
 */
@Module
public class AndroidModule
{
    private AndroidModule() {
    }

    @Provides
    @Singleton
    public static Device provideDevice() {
        return new AndroidDevice();
    }
}
