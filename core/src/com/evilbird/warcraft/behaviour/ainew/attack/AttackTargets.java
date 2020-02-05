/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew.attack;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.evilbird.engine.common.collection.Lists;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.engine.object.spatial.GameObjectNodeSet;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;
import com.evilbird.warcraft.object.data.player.Player;

import javax.inject.Inject;
import java.util.Collection;
import java.util.HashSet;

import static com.evilbird.engine.common.collection.CollectionUtils.filter;
import static com.evilbird.engine.common.collection.CollectionUtils.findFirst;
import static com.evilbird.warcraft.behaviour.ainew.attack.AttackStatus.isValidTarget;
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
        Iterable<GameObject> potentials = getPotentialTargets(attacker, data);
        Collection<GameObject> result = filter(potentials, target -> isValidTarget(attacker, target));
        return new HashSet<>(result);
    }

    private Collection<GameObject> getSingleTarget(OffensiveObject attacker, AttackData data) {
        Iterable<GameObject> potentials = getPotentialTargets(attacker, data);
        GameObject result = findFirst(potentials, target -> isValidTarget(attacker, target));
        return Lists.asImmutableList(result);
    }

    private Iterable<GameObject> getPotentialTargets(OffensiveObject attacker, AttackData data) {
        GameObjectGraph graph = getGraph(attacker);
        GameObjectNode oldPosition = data.getAttackerPosition();
        GameObjectNode newPosition = graph.getNode(attacker.getPosition());
        GameObjectNodeSet attackablePositions = data.getAttackablePositions();

        if (!newPosition.equals(oldPosition) || attackablePositions == null) {
            attackablePositions = getAttackablePositions(attacker, graph);
            data.setAttackerPosition(newPosition);
            data.setAttackablePositions(attackablePositions);
        }
        return attackablePositions;
    }

    private GameObjectNodeSet getAttackablePositions(OffensiveObject attacker, GameObjectGraph graph) {
        Collection<GameObjectNode> nodes = graph.getNodes(attacker, attacker.getSight());
        return new GameObjectNodeSet(nodes);
    }

    private GameObjectGraph getGraph(OffensiveObject attacker) {
        Player player = attacker.getTeam();
        GameObjectContainer container = player.getRoot();
        return container.getSpatialGraph();
    }

    @Override
    protected Task<AttackData> copyTo(Task<AttackData> task) {
        return task;
    }
}
