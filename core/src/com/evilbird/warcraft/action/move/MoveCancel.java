/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.evilbird.engine.action.framework.BasicAction;
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
public class MoveCancel extends BasicAction
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
        events.notifyMoveCancelled(item);
        return ActionComplete;
    }
}
