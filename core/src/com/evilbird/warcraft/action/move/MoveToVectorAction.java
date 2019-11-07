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
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.spatial.ItemNode;
import com.evilbird.warcraft.action.common.path.ItemPathFilter;
import com.evilbird.warcraft.item.common.capability.MovableObject;

import javax.inject.Inject;
import java.util.Objects;

/**
 * Instances of this {@link Action action} move an {@link Item} from its
 * current location to a given destination, specified as world position. The
 * moving item will be animated with a movement animation, as well choose a
 * path that avoids obstacles.
 *
 * @author Blair Butterworth
 */
public class MoveToVectorAction extends MoveAction
{
    private ItemPathFilter filter;
    private Vector2 destination;

    @Inject
    public MoveToVectorAction(Events events) {
        super(events);
        setIdentifier(MoveActions.MoveToLocation);
    }

    @Override
    public void reset() {
        super.reset();
        filter = null;
        destination = null;
    }

    public void setDestination(Vector2 destination) {
        this.destination = destination;
    }

    @Override
    protected Vector2 getDestination() {
        if (destination == null) {
            Item item = getItem();
            ItemRoot root = item.getRoot();
            UserInput cause = getCause();
            Vector2 projected = cause.getPosition();
            destination = root.unproject(projected);
        }
        return destination;
    }

    @Override
    protected boolean destinationReached(ItemNode node) {
        Vector2 destination = getDestination();
        return Objects.equals(node.getWorldReference(), destination);
    }

    @Override
    protected ItemPathFilter getPathFilter() {
        if (filter == null) {
            MovableObject item = (MovableObject)getItem();
            filter = new ItemPathFilter();
            filter.addTraversableItem(item);
            filter.addTraversableCapability(item.getMovementCapability());
        }
        return filter;
    }
}