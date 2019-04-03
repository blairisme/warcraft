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
import com.evilbird.engine.item.spatial.ItemNode;

/**
 * Implementors of this interface provide methods that are called when an item
 * moves.
 *
 * @author Blair Butterworth
 */
public interface MoveObserver
{
    void onMove(Item subject, ItemNode location);
}
