/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.device;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;

/**
 * Implementors of this interface provide methods that facilitate access to a
 * storage area on the device.
 *
 * @author Blair Butterworth
 */
public interface DeviceStorage
{
    FileHandleResolver getFileHandleResolver();
}
