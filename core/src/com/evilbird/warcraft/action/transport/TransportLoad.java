/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.transport;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.StateTransitionAction;
import com.evilbird.engine.object.utility.GameObjectOperations;
import com.evilbird.warcraft.action.move.MoveToItemAction;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.combatant.naval.Transport;

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
        Transport vessel = (Transport)getTarget();
        return nextAction(embarkee, vessel);
    }

    protected Action nextAction(Unit embarkee, Transport vessel) {
        if (!GameObjectOperations.isNear(embarkee, embarkee.getWidth(), vessel)) {
            return reposition;
        }
        if (!vessel.hasPassenger(embarkee)) {
            return embark;
        }
        return null;
    }
}
