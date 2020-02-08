/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew.construct;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.UnitArchetype;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;

import java.util.Collection;

/**
 * A bundle of attributes encapsulating the data required by construct
 * behaviour.
 *
 * @author Blair Butterworth
 */
public class ConstructData
{
    private Player player;
    private Player neutral;
    private Gatherer builder;
    private UnitType building;
    private UnitArchetype archetype;
    private Vector2 location;
    private ConstructOrder order;
    private ConstructManifest manifest;
    private GameObjectGraph graph;
    private GameObjectContainer container;

    public ConstructData(Player player) {
        this.player = player;
        this.order = ConstructOrder.forPlayer(player);
    }

    public Gatherer getBuilder() {
        return builder;
    }

    public UnitType getBuilding() {
        return building;
    }

    public UnitArchetype getBuildingArchetype() {
        return archetype;
    }

    public GameObjectContainer getContainer() {
        if (container == null) {
            container = player.getRoot();
        }
        return container;
    }

    public GameObjectGraph getGraph() {
        if (graph == null) {
            GameObjectContainer container = getContainer();
            graph = container.getSpatialGraph();
        }
        return graph;
    }

    public Vector2 getLocation() {
        return location;
    }

    public ConstructManifest getManifest() {
        return manifest;
    }

    public ConstructOrder getOrder() {
        return order;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getNeutralPlayer() {
        if (neutral == null) {
            GameObjectContainer container = getContainer();
            neutral = UnitOperations.getNeutralPlayer(container);
        }
        return neutral;
    }

    public void setBuilder(GameObject builder) {
        this.builder = (Gatherer)builder;
    }

    public void setBuilding(UnitType building) {
        this.building = building;
        this.archetype = building.getArchetype();
    }

    public void setLocation(Vector2 location) {
        this.location = location;
    }

    public void updateManifest(Collection<GameObject> buildings) {
        manifest = new ConstructManifest(buildings);
    }
}
