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
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.SpatialItemNode;
import com.evilbird.warcraft.item.common.capability.Movable;

import java.util.Collection;

/**
 * Instances of this class filter the spatial graph, removing those nodes that
 * a given item doesn't have the capability to traverse.
 *
 * @author Blair Butterworth
 */
class MovePathFilter implements Predicate<SpatialItemNode>
{
    private Predicate<SpatialItemNode> delegate;

    public MovePathFilter(Movable movable) {
        Collection<Identifier> allowedTypes = movable.getMovementCapability();

        if (allowedTypes.isEmpty()) {
            delegate = new PermitAll();
        }
        else if (allowedTypes.size() == 1) {
            delegate = new PermitSingleton(movable, allowedTypes.iterator().next());
        }
        else {
            delegate = new PermitSet(movable, allowedTypes);
        }
    }

    @Override
    public boolean test(SpatialItemNode node) {
        return delegate.test(node);
    }

    private static class PermitAll implements Predicate<SpatialItemNode> {
        @Override
        public boolean test(SpatialItemNode value) {
            return true;
        }
    }

    private static class PermitSingleton implements Predicate<SpatialItemNode> {
        private Movable movable;
        private Identifier allowedType;

        public PermitSingleton(Movable movable, Identifier allowedType) {
            this.allowedType = allowedType;
            this.movable = movable;
        }

        @Override
        public boolean test(SpatialItemNode node) {
            for (Item occupant : node.getOccupants()) {
                if (Objects.equals(occupant, movable)) {
                    return true;
                }
                if (!Objects.equals(allowedType, occupant.getType())) {
                    return false;
                }
            }
            return true;
        }
    }

    private static class PermitSet implements Predicate<SpatialItemNode> {
        private Movable movable;
        private Collection<Identifier> allowedTypes;

        public PermitSet(Movable movable, Collection<Identifier> allowedTypes) {
            this.allowedTypes = allowedTypes;
            this.movable = movable;
        }

        @Override
        public boolean test(SpatialItemNode node) {
            for (Item occupant : node.getOccupants()) {
                if (Objects.equals(occupant, movable)) {
                    return true;
                }
                if (!allowedTypes.contains(occupant.getType())) {
                    return false;
                }
            }
            return true;
        }
    }
}
