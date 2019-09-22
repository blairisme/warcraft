/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
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
