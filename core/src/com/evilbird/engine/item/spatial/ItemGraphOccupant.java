/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.spatial;

import com.evilbird.engine.item.Item;

import java.util.Collection;

/**
 * Marks an {@link Item} variant as beloning to an {@link ItemGraph}.
 *
 * @author Blair Butterworth
 */
public interface ItemGraphOccupant extends Item
{
    default Collection<ItemNode> getNodes(ItemGraph graph) {
        return graph.getNodes(getPosition(), getSize());
    }
}
