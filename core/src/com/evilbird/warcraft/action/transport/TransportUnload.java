/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.transport;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionResult;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.warcraft.action.common.exclusion.ItemExclusion;
import com.evilbird.warcraft.action.move.MoveAdjacent;
import com.evilbird.warcraft.object.common.capability.MovableObject;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.combatant.naval.Transport;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionResult.Complete;
import static com.evilbird.engine.action.ActionResult.Failed;

/**
 * Represents an {@link Action} that moves a disembarkee from a vessel.
 *
 * @author Blair Butterworth
 */
public class TransportUnload extends BasicAction
{
    private transient ItemExclusion exclusion;
    private transient MoveAdjacent movement;

    @Inject
    public TransportUnload(ItemExclusion exclusion, MoveAdjacent movement) {
        this.exclusion = exclusion;
        this.movement = movement;
    }

    @Override
    public ActionResult act(float time) {
        Transport vessel = (Transport)getSubject();
        for (Unit passenger: vessel.getPassengers()) {
            if (movement.reposition((MovableObject)passenger, vessel)) {
                vessel.removePassenger(passenger);
                exclusion.restore(passenger);
            }
        }
        if (vessel.hasPassengers()) {
            setFailed("Unable to offload all units");
            return Failed;
        }
        return Complete;
    }
}
