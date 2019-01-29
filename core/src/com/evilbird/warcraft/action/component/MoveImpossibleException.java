/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.component;

import com.evilbird.engine.item.Item;

/**
 * @author Blair Butterworth
 */
public class MoveImpossibleException extends RuntimeException
{
    public MoveImpossibleException(Item item) {
        super("Unable to move item " + item.getIdentifier() + " to destination");
    }
}
