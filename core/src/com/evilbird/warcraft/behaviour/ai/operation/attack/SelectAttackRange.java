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
import com.evilbird.engine.events.Events;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.engine.object.spatial.GameObjectNodeSet;
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;

import javax.inject.Inject;
import java.util.Collection;

/**
 * A leaf task that stores the attackable positions (spatial nodes) within
 * sight of a the attacker stored in the tasks blackboard.
 *
 * @author Blair Butterworth
 */
public class SelectAttackRange extends LeafTask<AttackData>
{
    private Events events;

    @Inject
    public SelectAttackRange(Events events) {
        this.events = events;
    }

    @Override
    public Status execute() {
        AttackData data = getObject();
        OffensiveObject attacker = data.getAttacker();
        GameObjectNodeSet positions = data.getAttackablePositions();

        if (positions == null || hasMoved(attacker)) {
            GameObjectGraph graph = data.getGraph();
            positions = new GameObjectNodeSet(graph.getNodes(attacker, attacker.getSight()));
            data.setAttackablePositions(positions);
        }
        return Status.SUCCEEDED;
    }

    protected boolean hasMoved(OffensiveObject attacker) {
        for (MoveEvent event: events.getEvents(MoveEvent.class)) {
            if (event.getSubject().equals(attacker)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected Task<AttackData> copyTo(Task<AttackData> task) {
        return task;
    }
}
