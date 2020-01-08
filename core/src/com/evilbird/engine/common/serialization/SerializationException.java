/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.serialization;

/**
 * Instances of this exception are thrown when an unexpected error occurs while
 * serializing or deserializing an object. The underlying reason for the error
 * can be obtained using the {@link #getCause()} method.
 *
 * @author Blair Butterworth
 */
public class SerializationException extends RuntimeException
{
    public SerializationException(Throwable cause) {
        super(cause);
    }

    public SerializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
