/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.spatial;

import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.item.ItemGroupObserver;

/**
 * Instances of this class observe {@link ItemGroup} events and use these to
 * apply the occupancy of an {@link ItemGraph}.
 *
 * @author Blair Butterworth
 */
public class ItemGraphUpdater implements ItemGroupObserver
{
    private ItemGraph graph;

    public ItemGraphUpdater() {
    }

    public void setGraph(ItemGraph graph) {
        this.graph = graph;
    }

    @Override
    public void itemAdded(Item item) {
        if (item instanceof ItemGroup) {
            ItemGroup group = (ItemGroup)item;
            group.addObserver(this);
            addOccupants(group);
        }
        else {
            addOccupants(item);
        }
    }

    private void addOccupants(Item item) {
        if (graph != null) {
            graph.addOccupants(item);
        }
    }

    private void addOccupants(ItemGroup group) {
        if (graph != null) {
            graph.addOccupants(group);
            graph.addOccupants(group.getItems());
        }
    }

    @Override
    public void itemRemoved(Item item) {
        if (item instanceof ItemGroup) {
            ItemGroup group = (ItemGroup)item;
            group.removeObserver(this);
            removeOccupants(group);
        }
        else {
            removeOccupants(item);
        }
    }

    private void removeOccupants(Item item) {
        if (graph != null) {
            graph.removeOccupants(item);
        }
    }

    private void removeOccupants(ItemGroup group) {
        if (graph != null) {
            graph.removeOccupants(group);
            graph.removeOccupants(group.getItems());
        }
    }

    @Override
    public void itemsCleared() {
        if (graph != null) {
            graph.clearOccupants();
        }
    }
}
