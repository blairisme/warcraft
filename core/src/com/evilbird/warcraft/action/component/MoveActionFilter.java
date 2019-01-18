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
import com.evilbird.engine.item.SpatialItemNode;

/**
 * Instances of this class filter the spatial graph, removing those nodes that
 * a given item doesn't have the capability to traverse.
 *
 * @author Blair Butterworth
 */
public class MoveActionFilter implements Predicate<SpatialItemNode>
{
    private Identifier type;

    public MoveActionFilter(Identifier type) {
        this.type = type;
    }

    @Override
    public boolean test(SpatialItemNode node) {
        Item occupant = node.getOccupant();
        Identifier occupantType = occupant.getType();
        return type.equals(occupantType);
    }
}
