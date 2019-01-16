/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action;

/**
 * Thrown if a requested action doesn't exist.
 *
 * @author Blair Butterworth
 */
public class UnknownActionException extends RuntimeException
{
    public UnknownActionException(ActionIdentifier identifier) {
        super("Action doesn't exist: " + identifier);
    }
}
