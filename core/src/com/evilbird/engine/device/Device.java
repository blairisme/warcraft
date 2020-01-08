/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.device;

import com.badlogic.gdx.assets.AssetManager;

import javax.inject.Singleton;

/**
 * Implementors of this interface represent a device or system capable of
 * running the application. Methods are provided that supply access to the
 * devices input and storage mediums.
 *
 * @author Blair Butterworth
 */
@Singleton
public interface Device
{
    DeviceControls getDeviceControls();

    DeviceDisplay getDeviceDisplay();

    DeviceInput getDeviceInput();

    AssetManager getAssetStorage();

    DeviceStorage getDeviceStorage();
}
