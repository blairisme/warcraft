/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.invade.sea;

import com.evilbird.engine.behaviour.framework.branch.SubTree;
import com.evilbird.warcraft.behaviour.ai.operation.invade.InvasionData;

import javax.inject.Inject;

/**
 * A behaviour tree that instructs groups of idle attackers to attack an
 * enemy by transporting them over a body of water.
 *
 * @author Blair Butterworth
 */
public class SeaInvasionBehaviour extends SubTree<InvasionData, SeaInvasionData>
{
    @Inject
    @SuppressWarnings("unchecked")
    public SeaInvasionBehaviour(SeaInvasionSequence seaInvasionSequence) {
        super(seaInvasionSequence);
    }

    @Override
    protected SeaInvasionData convertObject(InvasionData invasionData) {
        return new SeaInvasionData(invasionData);
    }
}
