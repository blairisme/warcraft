/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
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
