/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.test.data.item;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.Building;

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

    public static Building newTestBuilding(Identifier identifier, Identifier type, ItemRoot root, Player parent) {
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
