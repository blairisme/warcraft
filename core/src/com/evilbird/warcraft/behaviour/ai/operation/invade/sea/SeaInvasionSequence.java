/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.invade.sea;

import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.evilbird.warcraft.behaviour.ai.operation.invade.common.AttackTask;

import javax.inject.Inject;

/**
 * A behaviour sequence that instructs groups of idle attackers to attack an
 * enemy by transporting them over a body of water.
 *
 * @author Blair Butterworth
 */
public class SeaInvasionSequence extends Sequence
{
    @Inject
    @SuppressWarnings("unchecked")
    public SeaInvasionSequence(
        SelectTransport selectTransport,
        SelectLandingLocations selectLandings,
        TransportDock transportDock,
        TransportEmbark transportEmbark,
        TransportDeliver transportDeliver,
        TransportDisembark transportDisembark,
        AttackTask attackTask)
    {
        super(selectTransport,
              selectLandings,
              transportDock,
              transportEmbark,
              transportDeliver,
              transportDisembark,
              attackTask);
    }
}
