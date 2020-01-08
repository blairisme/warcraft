/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.test.data.item;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.building.Building;

import static com.evilbird.test.data.item.TestItemRoots.newTestRoot;
import static com.evilbird.test.data.item.TestPlayers.newTestPlayer;
import static com.evilbird.test.data.item.TestSkins.newTestSkin;

public class TestBuildings
{
    private TestBuildings() {
    }

    public static Building newTestBuilding(String id) {
        return newTestBuilding(new TextIdentifier(id), UnitType.Barracks);
    }

    public static Building newTestBuilding(String id, Identifier type) {
        return newTestBuilding(new TextIdentifier(id), type);
    }

    public static Building newTestBuilding(Identifier identifier, Identifier type) {
        return newTestBuilding(identifier, type, newTestRoot("root"), newTestPlayer("parent"));
    }

    public static Building newTestBuilding(Identifier identifier, Identifier type, GameObjectContainer root, Player parent) {
        Building item = new Building(newTestSkin());
        item.setIdentifier(identifier);
        item.setType(type);
        item.setPosition(12, 34);
        item.setSize(56, 78);
        item.setParent(parent);
        item.setRoot(root);
        item.setHealth(100);
        item.setHealthMaximum(100);
        return item;
    }
}
