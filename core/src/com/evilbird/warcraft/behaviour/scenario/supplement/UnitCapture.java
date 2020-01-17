/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.scenario.supplement;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.common.collection.Lists;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectComposite;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.action.selection.SelectFlash;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.Unit;

import javax.inject.Inject;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.evilbird.warcraft.object.unit.UnitSound.Captured;

/**
 * Defines additional scenario behaviour allowing units of a given type to be
 * captured by an adjacent player.
 *
 * @author Blair Butterworth
 */
public class UnitCapture implements SupplementaryBehaviour
{
    private Map<GameObjectNode, List<GameObject>> capturableItems;

    @Inject
    public UnitCapture() {
    }

    @Override
    public void accept(GameObjectContainer state, EventQueue events) {
        initializeCapturableItems(state);
        evaluateMovedItems(events);
    }

    private void initializeCapturableItems(GameObjectContainer state) {
        if (capturableItems == null) {
            capturableItems = getCapturableItems(state, state.getSpatialGraph());
        }
    }

    private Map<GameObjectNode, List<GameObject>> getCapturableItems(
        GameObjectContainer state, GameObjectGraph graph)
    {
        Map<GameObjectNode, List<GameObject>> result = new HashMap<>();

        for (Player player: UnitOperations.getPlayers(state)) {
            if (player.isCapturable()) {
                Collection<GameObject> units = player.getObjects();
                result.putAll(getAdjacentNodes(units, graph));
            }
        }
        return result;
    }

    private Map<GameObjectNode, List<GameObject>> getAdjacentNodes(
        Collection<GameObject> gameObjects, GameObjectGraph graph)
    {
        Map<GameObjectNode, List<GameObject>> result = new HashMap<>(gameObjects.size());
        for (GameObject gameObject : gameObjects) {
            for (GameObjectNode node: graph.getAdjacentNodes(gameObject)) {
                result.merge(node, Lists.asList(gameObject), Lists::union);
            }
        }
        return result;
    }

    private void evaluateMovedItems(EventQueue queue) {
        for (MoveEvent moveEvent: queue.getEvents(MoveEvent.class)) {
            if (moveEvent.isUpdate()) {
                evaluateMovedItem(moveEvent.getLocation(), moveEvent.getSubject());
            }
        }
    }

    private void evaluateMovedItem(GameObjectNode location, GameObject owner) {
        if (capturableItems.containsKey(location)) {
            List<GameObject> captured = capturableItems.remove(location);
            captureItems(captured, owner);
        }
    }

    private void captureItems(Collection<GameObject> captured, GameObject owner) {
        CollectionUtils.forEach(capturableItems.values(), items -> items.removeAll(captured));
        CollectionUtils.removeIf(capturableItems.values(), Collection::isEmpty);

        for (GameObject captive: captured) {
            captureItem(captive, owner);
        }
    }

    private void captureItem(GameObject captured, GameObject owner) {
        setOwner(captured, owner);
        setSoundEffect(captured);
        setAnimation(captured);
    }

    private void setOwner(GameObject captured, GameObject owner) {
        GameObjectComposite oldParent = captured.getParent();
        GameObjectComposite newParent = owner.getParent();

        oldParent.removeObject(captured);
        newParent.addObject(captured);
    }

    private void setSoundEffect(GameObject gameObject) {
        if (gameObject instanceof Unit) {
            Unit unit = (Unit)gameObject;
            unit.setSound(Captured);
        }
    }

    private void setAnimation(GameObject gameObject) {
        Action action = new SelectFlash();
        action.setSubject(gameObject);
        gameObject.addAction(action);
    }
}
