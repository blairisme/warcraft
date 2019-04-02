/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.capability.Movable;

import javax.inject.Inject;

/**
 * Instances of this {@link Action action} move an {@link Item} from its
 * current location to a given destination, specified as an Item. The moving
 * item will be animated with a movement animation, as well choose a path that
 * avoids obstacles.
 *
 * @author Blair Butterworth
 */
class MoveToItemAction extends MoveAction
{
    private MovePathFilter filter;
    private MoveDestination destination;

    @Inject
    public MoveToItemAction() {
        super();
    }

    @Override
    public MoveDestination getDestination() {
        if (destination == null) {
            destination = new MoveDestinationItem(getTarget());
        }
        return destination;
    }

    @Override
    public MovePathFilter getPathFilter() {
        if (filter == null) {
            Item item = getItem();
            Item target = getTarget();

            filter = new MovePathFilter();
            filter.addRequiredTypes((Movable)item);
            filter.addPermittedItem(item);
            filter.addPermittedItem(target);
        }
        return filter;
    }

    @Override
    public void reset() {
        super.reset();
        filter = null;
        destination = null;
    }

    public static MoveToItemAction moveToItem(MoveObserver observer) {
        MoveToItemAction result = new MoveToItemAction();
        result.setObserver(observer);
        return result;
    }
}
