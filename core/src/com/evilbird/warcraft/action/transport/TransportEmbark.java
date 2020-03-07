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
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.combatant.naval.Transport;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionResult.Complete;

/**
 * Represents an {@link Action} that facilitates an individual item entering a
 * vessel.
 *
 * @author Blair Butterworth
 */
public class TransportEmbark extends BasicAction
{
    private transient ItemExclusion exclusion;

    @Inject
    public TransportEmbark(ItemExclusion exclusion) {
        this.exclusion = exclusion;
    }

    @Override
    public ActionResult act(float time) {
        Unit embarkee = (Unit)getSubject();
        exclusion.disable(embarkee);

        Transport vessel = (Transport)getTarget();
        vessel.addPassenger(embarkee);

        return Complete;
    }
}
