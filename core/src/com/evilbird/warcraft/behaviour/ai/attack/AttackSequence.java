/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.attack;

import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.branch.Selector;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.evilbird.engine.behaviour.framework.guard.RandomWait;

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
    public AttackSequence(AttackPosition position, AttackTrigger trigger, AttackTargets target, AttackTask attack) {
        super(position, trigger, target, withCooldown(attack));
    }

    private static Task<AttackData> withCooldown(AttackTask attack) {
        return new Selector<>(attack, new RandomWait<AttackData>()
            .waitMinimum(0.5f)
            .waitMaximum(1.0f));
    }
}
