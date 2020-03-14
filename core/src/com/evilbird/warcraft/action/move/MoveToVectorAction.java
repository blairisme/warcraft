/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.move;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.warcraft.action.common.spatial.SpatialPathFilter;
import com.evilbird.warcraft.object.common.capability.MovableObject;

import javax.inject.Inject;
import java.util.Objects;

/**
 * Instances of this {@link Action action} move an {@link GameObject} from its
 * current location to a given destination, specified as world position. The
 * moving item will be animated with a movement animation, as well choose a
 * path that avoids obstacles.
 *
 * @author Blair Butterworth
 */
public class MoveToVectorAction extends MoveAction
{
    private SpatialPathFilter filter;
    private Vector2 destination;

    @Inject
    public MoveToVectorAction(MoveEvents events) {
        super(events);
        setIdentifier(MoveActions.MoveToLocation);
    }

    public void setDestination(Vector2 destination) {
        this.destination = destination;
    }

    @Override
    public Vector2 getDestination() {
        if (destination == null) {
            GameObject gameObject = getSubject();
            GameObjectContainer root = gameObject.getRoot();
            UserInput cause = getCause();
            Vector2 projected = cause.getPosition();
            destination = root.unproject(projected);
        }
        return destination;
    }

    @Override
    public boolean destinationReached(GameObjectNode node) {
        Vector2 destination = getDestination();
        return Objects.equals(node.getWorldReference(), destination);
    }

    @Override
    public SpatialPathFilter getPathFilter() {
        if (filter == null) {
            MovableObject item = (MovableObject) getSubject();
            filter = new SpatialPathFilter();
            filter.addTraversableItem(item);
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
}