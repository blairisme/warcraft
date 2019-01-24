/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.component;

import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemNode;
import com.evilbird.warcraft.item.common.capability.Movable;

import java.util.Arrays;
import java.util.Collection;

/**
 * Instances of this class filter the spatial graph, removing those nodes that
 * a given item doesn't have the capability to traverse.
 *
 * @author Blair Butterworth
 */
class MovePathFilter implements Predicate<ItemNode>
{
    private Collection<Item> permittedItems;
    private Collection<Identifier> requiredTypes;

    public MovePathFilter(Movable movable) {
        this(movable.getMovementCapability(), Arrays.asList((Item)movable));
    }

    public MovePathFilter(Movable movable, Item permitted) {
        this(movable.getMovementCapability(), Arrays.asList((Item)movable, permitted));
    }

    public MovePathFilter(Collection<Identifier> requiredTypes, Collection<Item> permittedItems) {
        this.requiredTypes = requiredTypes;
        this.permittedItems = permittedItems;
    }

    @Override
    public boolean test(ItemNode node) {
        for (Item occupant : node.getOccupants()) {
            if (permittedItems.contains(occupant)) {
                return true;
            }
            if (!requiredTypes.contains(occupant.getType())) {
                return false;
            }
        }
        return true;
    }
}
