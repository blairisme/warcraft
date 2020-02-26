/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.invade;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.common.collection.Lists;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.capability.MovableObject;
import com.evilbird.warcraft.object.common.capability.TerrainType;
import com.evilbird.warcraft.object.data.player.Player;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

import static com.badlogic.gdx.ai.btree.Task.Status.FAILED;
import static com.badlogic.gdx.ai.btree.Task.Status.SUCCEEDED;
import static com.evilbird.warcraft.behaviour.ai.operation.invade.InvasionQueries.AttackPossible;
import static com.evilbird.warcraft.behaviour.ai.operation.invade.InvasionQueries.PotentialTargets;

/**
 * A {@link LeafTask} implementation that selects an enemy unit or building
 * that will be target of the invasion.
 *
 * @author Blair Butterworth
 */
public class SelectTarget extends LeafTask<InvasionData>
{
    @Inject
    public SelectTarget() {
    }

    @Override
    public Status execute() {
        InvasionData data = getObject();
        GameObject target = getTarget(data);
        data.setTarget(target);
        return target == null ? FAILED : SUCCEEDED;
    }

    private GameObject getTarget(InvasionData data) {
        Player enemy = data.getEnemy();
        TerrainType terrain = getAttackTerrain(data);

        List<GameObject> potentials = Lists.toList(enemy.findAll(PotentialTargets));
        List<GameObject> targets = CollectionUtils.filter(potentials, AttackPossible(terrain));
        Lists.sort(targets, new SelectTargetPriority());

        return CollectionUtils.first(targets);
    }

    private TerrainType getAttackTerrain(InvasionData data) {
        Collection<GameObject> attackers = data.getAttackers();
        GameObject attackerSample = CollectionUtils.first(attackers);
        MovableObject movableObject = (MovableObject)attackerSample;
        return movableObject.getMovementCapability();
    }

    @Override
    protected Task<InvasionData> copyTo(Task<InvasionData> task) {
        return task;
    }
}
