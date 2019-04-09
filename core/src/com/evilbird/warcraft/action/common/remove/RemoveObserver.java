/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.remove;

import com.evilbird.engine.item.Item;

/**
 * Implementors of this interface provide methods that are called when
 * {@link Item Items} are removed.
 *
 * @author Blair Butterworth
 */
public interface RemoveObserver
{
    /**
     * Called when an {@link Item} has been removed.
     *
     * @param removed   an {@code Item} instance. This parameter will not be
     *                  {@code null}.
     */
    void onRemove(Item removed);
}
