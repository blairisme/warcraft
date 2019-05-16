/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.select;

import com.evilbird.engine.item.Item;

/**
 * Implementors of this interface provide methods that are called when an items
 * selection changes.
 *
 * @author Blair Butterworth
 */
public interface SelectObserver
{
    void onSelect(Item item, boolean selected);
}
