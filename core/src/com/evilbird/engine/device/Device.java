/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.device;

/**
 * Implementors of this interface represent a device or system capable of
 * running the application. Methods are provided that supply access to the
 * devices input and storage mediums.
 *
 * @author Blair Butterworth
 */
public interface Device
{
    DeviceInput getDeviceInput();

    DeviceStorage getAssetStorage();

    DeviceStorage getInternalStorage();
}
