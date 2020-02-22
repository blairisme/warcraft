/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.move;

import com.evilbird.engine.action.framework.AbstractAction;
import com.evilbird.engine.object.AnimatedObject;
import com.evilbird.engine.object.GameObject;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;

/**
 * Instances of this class stop a moving {@link GameObject}, returning it to an idle
 * state.
 *
 * @author Blair Butterworth
 */
public class MoveCancel extends AbstractAction
{
    private transient MoveEvents events;

    /**
     * Constructs a new instance of this class given a {@link MoveEvents} queue
     * used to report the cancellation of the move operation.
     *
     * @param events  an {@code MoveEvents} instance. This parameter
     *                  cannot be {@code null}.
     */
    @Inject
    public MoveCancel(MoveEvents events) {
        this.events = events;
        setIdentifier(MoveActions.MoveCancel);
    }

    @Override
    public boolean act(float delta) {
        AnimatedObject item = (AnimatedObject) getSubject();
        item.setAnimation(Idle);
        events.moveCancelled(item);
        return ActionComplete;
    }
}
