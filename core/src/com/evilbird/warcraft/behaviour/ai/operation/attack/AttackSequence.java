/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.attack;

import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.branch.Selector;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.evilbird.engine.behaviour.framework.guard.ConditionGuard;
import com.evilbird.engine.behaviour.framework.guard.RandomWait;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;

import javax.inject.Inject;

/**
 * A behaviour sequence that instructs an attacker to attack an available
 * target.
 *
 * @author Blair Butterworth
 */
@SuppressWarnings("unchecked")
public class AttackSequence extends Sequence<AttackData>
{
    @Inject
    public AttackSequence(
        SelectAttackRange selectAttackRange,
        SelectTargets selectTargets,
        AttackTask attackTarget)
    {
        super(whenValid(selectAttackRange),
              cooldownIfFailed(selectTargets),
              cooldownIfComplete(attackTarget));
    }

    private static Task<AttackData> whenValid(Task<AttackData> task) {
        Task<AttackData> guard = new ConditionGuard<AttackData, OffensiveObject>()
            .from(AttackData::getAttacker)
            .pass(AttackStatus::isValidAttacker);
        return new Sequence<>(guard, task);
    }

    private static Task<AttackData> cooldownIfFailed(Task<AttackData> task) {
        return new Selector<>(task, new RandomWait<AttackData>()
                .waitMinimum(0.5f)
                .waitMaximum(1.0f));
    }

    private static Task<AttackData> cooldownIfComplete(Task<AttackData> task) {
        return new Sequence<>(task, new RandomWait<AttackData>()
            .waitMinimum(0.5f)
            .waitMaximum(1.0f));
    }
}
