/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.invade;

import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.evilbird.engine.behaviour.framework.guard.RandomWait;

import javax.inject.Inject;

/**
 * A behaviour sequence that instructs groups of idle attackers to attack an
 * enemy.
 *
 * @author Blair Butterworth
 */
public class InvasionSequence extends Sequence<InvasionData>
{
    @Inject
    @SuppressWarnings("unchecked")
    public InvasionSequence(
        SelectAttackers selectAttackers,
        SelectEnemy selectEnemy,
        SelectTarget selectTarget,
        InvasionTask invadeTarget,
        IncrementPhase completePhase)
    {
        super(delayEvaluation(),
              selectEnemy,
              selectAttackers,
              selectTarget,
              invadeTarget,
              completePhase);
    }

    private static Task<InvasionData> delayEvaluation() {
        return new RandomWait<InvasionData>()
            .waitMinimum(1f)
            .waitMaximum(2f);
    }
}
