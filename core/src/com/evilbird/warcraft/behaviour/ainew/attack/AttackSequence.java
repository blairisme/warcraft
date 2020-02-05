/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew.attack;

import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.evilbird.warcraft.behaviour.ainew.common.guard.ConditionGuard;
import com.evilbird.warcraft.behaviour.ainew.common.guard.RandomWait;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;

import javax.inject.Inject;

/**
 * A behaviour sequence that instructs an attacker to attack an available
 * target.
 *
 * @author Blair Butterworth
 */
public class AttackSequence extends Sequence<AttackData>
{
    @Inject
    public AttackSequence(AttackTargets selectTargets, AttackTask attackTargets) {
        super(afterCooldown(), whenReady(), selectTargets, attackTargets);
    }

    private static Task<AttackData> afterCooldown() {
        return new RandomWait<AttackData>()
            .waitMinimum(0.5f)
            .waitMaximum(1.0f);
    }

    private static Task<AttackData> whenReady() {
        return new ConditionGuard<AttackData, OffensiveObject>()
            .from(AttackData::getAttacker)
            .pass(AttackStatus::isValidAttacker);
    }
}
