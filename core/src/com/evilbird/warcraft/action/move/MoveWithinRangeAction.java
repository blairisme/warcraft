/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.common.path.ItemPathFilter;
import com.evilbird.warcraft.item.common.movement.Movable;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;

/**
 * Instances of this {@link Action action} move an {@link Item} from its
 * current location to within attack range of a given target. The moving item
 * will be animated with a movement animation, as well choosing a path that
 * avoids obstacles.
 *
 * @author Blair Butterworth
 */
public class MoveWithinRangeAction extends MoveAction
{
    private ItemPathFilter filter;
    private MoveDestination destination;

    @Inject
    public MoveWithinRangeAction() {
        super();
    }

    @Override
    public MoveDestination getDestination() {
        if (destination == null) {
            Item target = getTarget();
            Combatant combatant = (Combatant)getItem();
            destination = new MoveDestinationRange(target, combatant.getRange());
        }
        return destination;
    }

    @Override
    public ItemPathFilter getPathFilter() {
        if (filter == null) {
            Movable item = (Movable)getItem();
            filter = new ItemPathFilter();
            filter.addTraversableItem(item);
            filter.addTraversableItem(getTarget());
            filter.addTraversableCapability(item.getMovementCapability());
        }
        return filter;
    }

    @Override
    public void reset() {
        super.reset();
        filter = null;
        destination = null;
    }

    @Override
    public void restart() {
        super.restart();
        filter = null;
        destination = null;
    }

    public static MoveWithinRangeAction moveWithinRange(Events events) {
        MoveWithinRangeAction result = new MoveWithinRangeAction();
        result.setObserver(events);
        return result;
    }
}