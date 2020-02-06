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
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitType;

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
    private Unit builder;
    private UnitType building;
    private Vector2 location;
    private ConstructOrder order;
    private ConstructManifest manifest;

    public ConstructData(Player player) {
        this.player = player;
        this.order = ConstructOrder.forPlayer(player);
    }

    public Unit getBuilder() {
        return builder;
    }

    public UnitType getBuilding() {
        return building;
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

    public void setBuilder(GameObject builder) {
        this.builder = (Unit)builder;
    }

    public void setBuilding(UnitType building) {
        this.building = building;
    }

    public void setLocation(Vector2 location) {
        this.location = location;
    }

    public void updateManifest(Collection<GameObject> buildings) {
        manifest = new ConstructManifest(buildings);
    }
}
