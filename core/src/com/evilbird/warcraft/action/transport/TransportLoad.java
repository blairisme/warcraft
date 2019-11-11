/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.transport;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.StateTransitionAction;
import com.evilbird.engine.object.utility.GameObjectOperations;
import com.evilbird.warcraft.action.move.MoveToItemAction;
import com.evilbird.warcraft.item.unit.Unit;

import javax.inject.Inject;

/**
 * Represents an {@link Action} that moves an embarkee to a vessel and
 * facilitates it entering it.
 *
 * @author Blair Butterworth
 */
public class TransportLoad extends StateTransitionAction
{
    private transient MoveToItemAction reposition;
    private transient TransportEmbark embark;

    @Inject
    public TransportLoad(
        MoveToItemAction reposition,
        TransportEmbark embark)
    {
        super(reposition, embark);
        this.reposition = reposition;
        this.embark = embark;
    }

    @Override
    protected Action nextAction(Action previous) {
        Unit embarkee = (Unit) getSubject();
        Unit vessel = (Unit)getTarget();
        return nextAction(embarkee, vessel);
    }

    protected Action nextAction(Unit embarkee, Unit vessel) {
        if (!GameObjectOperations.isNear(embarkee, embarkee.getWidth(), vessel)) {
            return reposition;
        }
        if (!vessel.hasAssociatedItem(embarkee)) {
            return embark;
        }
        return null;
    }
}
