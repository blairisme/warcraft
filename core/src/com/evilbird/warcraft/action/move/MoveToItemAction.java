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
import com.evilbird.engine.common.lang.Movable;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.common.path.ItemPathFilter;

import javax.inject.Inject;

/**
 * Instances of this {@link Action action} move an {@link Item} from its
 * current location to a given destination, specified as an Item. The moving
 * item will be animated with a movement animation, as well choose a path that
 * avoids obstacles.
 *
 * @author Blair Butterworth
 */
public class MoveToItemAction extends MoveAction
{
    private ItemPathFilter filter;
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
    public ItemPathFilter getPathFilter() {
        if (filter == null) {
            Movable item = (Movable)getItem();
            filter = new ItemPathFilter();
            filter.addTraversableItem(item);
            filter.addTraversableItem(getTarget());
            filter.addTraversableTypes(item.getMovementCapability());
        }
        return filter;
    }

    @Override
    public void reset() {
        super.reset();
        filter = null;
        destination = null;
    }

    public static MoveToItemAction move(MoveObserver observer) {
        return moveToItem(observer);
    }

    public static MoveToItemAction moveToItem(MoveObserver observer) {
        MoveToItemAction result = new MoveToItemAction();
        result.setObserver(observer);
        return result;
    }
}
