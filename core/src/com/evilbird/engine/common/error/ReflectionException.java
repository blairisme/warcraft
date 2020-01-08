/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.error;

/**
 * Thrown when an error occurs whilst using reflection.
 *
 * @author Blair Butterworth
 */
public class ReflectionException extends RuntimeException
{
    public ReflectionException(Throwable cause) {
        super(cause);
    }
}
