/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
