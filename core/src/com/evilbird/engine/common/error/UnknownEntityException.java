/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.error;

import com.evilbird.engine.common.lang.Identifier;

/**
 * Thrown if a requested entity doesn't exist.
 *
 * @author Blair Butterworth
 */
public class UnknownEntityException extends RuntimeException
{
    public UnknownEntityException(Identifier identifier) {
        super("Entity doesn't exist: " + identifier);
    }
}
