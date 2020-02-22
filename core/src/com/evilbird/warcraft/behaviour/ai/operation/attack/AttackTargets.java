/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.attack;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.evilbird.engine.common.collection.Lists;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;

import javax.inject.Inject;
import java.util.Collection;
import java.util.HashSet;

import static com.evilbird.engine.common.collection.CollectionUtils.filter;
import static com.evilbird.engine.common.collection.CollectionUtils.findFirst;
import static com.evilbird.warcraft.object.common.capability.OffensivePlurality.Multiple;

/**
 * A {@link LeafTask} that selects an attackable target within sight of an
 * attacker.
 *
 * @author Blair Butterworth
 */
public class AttackTargets extends LeafTask<AttackData>
{
    @Inject
    public AttackTargets() {
    }

    @Override
    public Status execute() {
        AttackData data = getObject();
        OffensiveObject attacker = data.getAttacker();
        Collection<GameObject> targets = getTargets(attacker, data);
        data.setTargets(targets);
        return targets.isEmpty() ? Status.FAILED : Status.SUCCEEDED;
    }

    private Collection<GameObject> getTargets(OffensiveObject attacker, AttackData data) {
        if (attacker.getAttackPlurality() == Multiple) {
            return getMultipleTargets(attacker, data);
        }
        return getSingleTarget(attacker, data);
    }

    private Collection<GameObject> getMultipleTargets(OffensiveObject attacker, AttackData data) {
        Iterable<GameObject> potentials = data.getAttackablePositions();
        Collection<GameObject> result = filter(potentials, target -> AttackStatus.isValidTarget(attacker, target));
        return new HashSet<>(result);
    }

    private Collection<GameObject> getSingleTarget(OffensiveObject attacker, AttackData data) {
        Iterable<GameObject> potentials = data.getAttackablePositions();
        GameObject result = findFirst(potentials, target -> AttackStatus.isValidTarget(attacker, target));
        return Lists.asImmutableList(result);
    }

    @Override
    protected Task<AttackData> copyTo(Task<AttackData> task) {
        return task;
    }
}
