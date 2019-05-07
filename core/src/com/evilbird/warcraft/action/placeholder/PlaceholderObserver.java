/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.placeholder;

import com.evilbird.engine.item.Item;

/**
 * Implementors of this interface provide methods that are called when a
 * placeholder is added or removed.
 *
 * @author Blair Butterworth
 */
public interface PlaceholderObserver
{
    void onPlaceholderAdded(Item builder, Item placeholder);

    void onPlaceholderRemoved(Item builder, Item placeholder);
}
