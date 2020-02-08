/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.attack;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.engine.object.spatial.GameObjectNodeSet;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;

import javax.inject.Inject;
import java.util.Collection;

/**
 * A leaf task that stores the attackable positions (spatial nodes) within
 * sight of a the attacker stored in the tasks blackboard.
 *
 * @author Blair Butterworth
 */
public class AttackPosition extends LeafTask<AttackData>
{
    @Inject
    public AttackPosition() {
    }

    @Override
    public Status execute() {
        AttackData data = getObject();
        OffensiveObject attacker = data.getAttacker();

        GameObjectGraph graph = data.getGraph();
        GameObjectNode oldPosition = data.getAttackerPosition();
        GameObjectNode newPosition = graph.getNode(attacker.getPosition());
        GameObjectNodeSet attackablePositions = data.getAttackablePositions();

        if (!newPosition.equals(oldPosition) || attackablePositions == null) {
            Collection<GameObjectNode> nodes = graph.getNodes(attacker, attacker.getSight());
            attackablePositions = new GameObjectNodeSet(nodes);

            data.setAttackerPosition(newPosition);
            data.setAttackablePositions(attackablePositions);
        }
        return Status.SUCCEEDED;
    }

    @Override
    protected Task<AttackData> copyTo(Task<AttackData> task) {
        return task;
    }
}
