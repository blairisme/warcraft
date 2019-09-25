/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.device;

/**
 * Defines constants used in {@link DeviceInput} implementations.
 *
 * @author Blair Butterworth
 */
public class DeviceInputConstants
{
    public static final boolean InputConsumed = true;
    public static final boolean InputNotConsumed = false;

    /**
     * Disable construction of static helper class.
     */
    private DeviceInputConstants() {
    }
}
