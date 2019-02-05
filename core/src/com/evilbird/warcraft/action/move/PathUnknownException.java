/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.evilbird.engine.item.Item;

/**
 * Instances of this error are produced when a {@link MoveAction} is unable
 * find a path to a given destination.
 *
 * @author Blair Butterworth
 */
public class PathUnknownException extends RuntimeException
{
    public PathUnknownException(Item item) {
        super("Unable to determine path to " + item.getIdentifier());
    }
}
