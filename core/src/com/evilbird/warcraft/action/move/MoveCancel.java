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
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.Viewable;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.warcraft.action.move.MoveEvents.notifyMoveCancelled;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;

/**
 * Instances of this class stop a moving {@link Item}, returning it to an idle
 * state.
 *
 * @author Blair Butterworth
 */
public class MoveCancel extends BasicAction
{
    private transient Events events;

    /**
     * Constructs a new instance of this class given a {@link Events} queue
     * used to report the cancellation of the move operation.
     *
     * @param events  an {@code Events} instance. This parameter
     *                  cannot be {@code null}.
     */
    @Inject
    public MoveCancel(Events events) {
        this.events = events;
        setIdentifier(MoveActions.MoveCancel);
    }

    @Override
    public boolean act(float delta) {
        Viewable item = (Viewable)getItem();
        item.setAnimation(Idle);
        notifyMoveCancelled(events, item);
        return ActionComplete;
    }
}
