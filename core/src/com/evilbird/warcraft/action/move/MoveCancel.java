/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.Unit;

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
@Deprecated
public class MoveCancel extends BasicAction
{
    private transient Events events;

    /**
     * Constructs a new instance of this class given a {@link EventQueue}
     * used to report the cancellation of the move operation.
     *
     * @param events  a {@code EventQueue} instance. This parameter
     *                  cannot be {@code null}.
     */
    @Inject
    public MoveCancel(EventQueue events) {
        this.events = events;
        setIdentifier(MoveActions.MoveCancel);
    }

    @Override
    public boolean act(float delta) {
        Unit unit = (Unit)getItem();
        unit.setAnimation(Idle);
        notifyMoveCancelled(events, unit);
        return ActionComplete;
    }
}
