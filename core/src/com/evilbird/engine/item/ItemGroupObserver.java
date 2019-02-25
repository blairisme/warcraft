/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

/**
 * Implementors of this interface provide methods that are called when
 * {@link Item Items} are added or removed from an {@link ItemGroup}.
 *
 * @author Blair Butterworth
 */
public interface ItemGroupObserver
{
    void itemAdded(Item item);

    void itemRemoved(Item item);

    void itemsCleared();
}
