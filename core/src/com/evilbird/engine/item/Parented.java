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
 * Implementors of this interface represent an object that belongs to an item
 * hierarchy.
 *
 * @author Blair Butterworth
 */
//TODO: Move into common and make generic
public interface Parented
{
    /**
     * Returns the highest item the hierarchy.
     *
     * @return  the root item.
     */
    ItemRoot getRoot();
}
