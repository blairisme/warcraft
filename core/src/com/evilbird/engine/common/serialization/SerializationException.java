/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
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
    public SerializationException(Throwable cause)
    {
        super(cause);
    }
}
