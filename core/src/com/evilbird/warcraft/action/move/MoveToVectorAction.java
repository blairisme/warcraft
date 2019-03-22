/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.item.common.capability.Movable;

import javax.inject.Inject;

/**
 * Instances of this {@link Action action} move an {@link Item} from its
 * current location to a given destination, specified as world position. The
 * moving item will be animated with a movement animation, as well choose a
 * path that avoids obstacles.
 *
 * @author Blair Butterworth
 */
class MoveToVectorAction extends MoveAction
{
    private MovePathFilter filter;
    private MoveDestination destination;

    @Inject
    public MoveToVectorAction() {
        super();
    }

    @Override
    public MoveDestination getDestination() {
        if (destination == null) {
            Item item = getItem();
            ItemRoot root = item.getRoot();
            UserInput cause = getCause();
            Vector2 projected = cause.getPosition();
            Vector2 position =  root.unproject(projected);
            destination = new MoveDestinationVector(position);
        }
        return destination;
    }

    @Override
    public MovePathFilter getPathFilter() {
        if (filter == null) {
            Item item = getItem();
            filter = new MovePathFilter();
            filter.addRequiredTypes((Movable)item);
            filter.addPermittedItem(item);
        }
        return filter;
    }

    @Override
    public void reset() {
        super.reset();
        filter = null;
        destination = null;
    }
}