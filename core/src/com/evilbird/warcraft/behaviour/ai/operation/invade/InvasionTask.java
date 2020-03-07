/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.invade;

import com.badlogic.gdx.ai.btree.SingleRunningChildBranch;
import com.badlogic.gdx.ai.btree.Task;
import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.action.common.spatial.SpatialPathUtils;
import com.evilbird.warcraft.behaviour.ai.operation.invade.common.AttackTask;
import com.evilbird.warcraft.behaviour.ai.operation.invade.sea.SeaInvasionBehaviour;
import com.evilbird.warcraft.object.common.capability.MovableObject;
import com.evilbird.warcraft.object.common.capability.TerrainType;

import javax.inject.Inject;

import static com.evilbird.warcraft.object.common.capability.TerrainType.Land;
import static com.evilbird.warcraft.object.common.capability.TerrainType.ShallowWater;
import static com.evilbird.warcraft.object.common.capability.TerrainType.Water;

/**
 * A task that assigns an attack action to the attackers contained in the
 * tasks blackboard. If the invading units can traverse through the air or
 * water then the target is attacked directly. If the invading units can
 * traverse across land then a check is made to see if water is obstructing
 * their invasion. If so, then the units are first transported to their target
 * before attacking.
 *
 * @author Blair Butterworth
 */
public class InvasionTask extends SingleRunningChildBranch<InvasionData>
{
    private Task<InvasionData> directInvasion;
    private Task<InvasionData> transportedInvasion;

    @Inject
    public InvasionTask(AttackTask directInvasion, SeaInvasionBehaviour transportedInvasion) {
        this.directInvasion = directInvasion;
        this.transportedInvasion = transportedInvasion;
    }

    @Override
    public void start() {
        InvasionData data = getObject();
        GameObject target = data.getTarget();
        GameObject attacker = CollectionUtils.first(data.getAttackers());
        Task<InvasionData> invasionTask = getInvasionTask(attacker, target);

        children.clear();
        if (invasionTask != null) {
            children.add(invasionTask);
        }
        super.start();
    }

    private Task<InvasionData> getInvasionTask(GameObject attacker, GameObject target) {
        TerrainType terrain = getTerrainType(attacker);
        switch (terrain) {
            case Air:
            case Water: return directInvasion;
            case Land: return getLandInvasionTask(attacker, target);
            default: throw new UnsupportedOperationException();
        }
    }

    private Task<InvasionData> getLandInvasionTask(GameObject attacker, GameObject target) {
        if (SpatialPathUtils.hasPathViaTerrain(attacker, target, Land)) {
            return directInvasion;
        }
        if (SpatialPathUtils.hasPathViaTerrain(attacker, target, Land, Water, ShallowWater)) {
            return transportedInvasion;
        }
        return null;
    }

    private TerrainType getTerrainType(GameObject attacker) {
        MovableObject movableObject = (MovableObject)attacker;
        return movableObject.getMovementCapability();
    }


    @Override
    public void childSuccess(Task<InvasionData> task) {
        success();
    }

    @Override
    public void childFail(Task<InvasionData> task) {
        fail();
    }
}
