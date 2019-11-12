/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.object.specialized;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.evilbird.engine.object.GameObjectGroup;

import java.util.Objects;

/**
 * Represents a user interface control that contains a number of children, each
 * sized to match the StackItem and positioned on top of each other.
 *
 * @author Blair Butterworth
 */
public class Stack extends GameObjectGroup
{
    public void add(Actor actor) {
        Objects.requireNonNull(actor);
        Group group = (Group)toActor();
        group.addActor(actor);
    }
}
