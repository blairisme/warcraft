/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.object.control;

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
