/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.path;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.spatial.ItemNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Instances of this class filter the spatial graph, removing those nodes that
 * a given item doesn't have the capability to traverse.
 *
 * @author Blair Butterworth
 */
public class ItemPathFilter implements Predicate<ItemNode>
{
    private Collection<Item> traversableItems;
    private Collection<Identifier> traversableTypes;

    /**
     * Constructs a new ItemPathFilter that filters all path content.
     */
    public ItemPathFilter() {
        traversableItems = new ArrayList<>();
        traversableTypes = new ArrayList<>();
    }

    /**
     * Specifies that the path to which the filter is applied can contain the
     * given {@link Item}.
     *
     * @param item an {@code Item} that can be traversed. This parameter cannot
     *             be {@code null}.
     */
    public void addTraversableItem(Item item) {
        Objects.requireNonNull(item);
        traversableItems.add(item);
    }

    /**
     * Specifies that the path to which the filter is applied can contain the
     * given {@link Item} {@link Collection}.
     *
     * @param items a {@code Collection} of {@code Items} that can be traversed.
     *             This parameter cannot be {@code null}.
     */
    public void addTraversableItems(Collection<Item> items) {
        Objects.requireNonNull(items);
        traversableItems.addAll(items);
    }

    /**
     * Specifies that the path to which the filter is applied can contain
     * {@link Item Items} of the given type.
     *
     * @param type an {@code Item} type. This parameter cannot be {@code null}.
     */
    public void addTraversableType(Identifier type) {
        Objects.requireNonNull(type);
        traversableTypes.add(type);
    }

    /**
     * Specifies that the path to which the filter is applied can contain
     * {@link Item Items} of the given types.
     *
     * @param types a {@code Collection} of {@code Item} type. This parameter
     *              cannot be {@code null}.
     */
    public void addTraversableTypes(Collection<Identifier> types) {
        Objects.requireNonNull(types);
        traversableTypes.addAll(types);
    }

    @Override
    public boolean test(ItemNode node) {
        for (Item occupant : node.getOccupants()) {
            if (traversableItems.contains(occupant)) {
                return true;
            }
            if (!traversableTypes.contains(occupant.getType())) {
                return false;
            }
        }
        return true;
    }
}
