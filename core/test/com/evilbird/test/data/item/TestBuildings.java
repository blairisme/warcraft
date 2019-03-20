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

import static com.evilbird.test.data.item.TestPlayers.newTestPlayer;

public class TestBuildings
{
    private TestBuildings() {
    }

    public static Building newTestBuilding(String id) {
        return newTestBuilding(new TextIdentifier(id), UnitType.Barracks);
    }

    public static Building newTestBuilding(Identifier identifier, Identifier type) {
        return newTestBuilding(identifier, type, TestItemRoots.newTestRoot("root"), newTestPlayer("parent"));
    }

    public static Building newTestBuilding(Identifier identifier, Identifier type, ItemRoot root, Player parent) {
        Building item = new Building();
        item.setIdentifier(identifier);
        item.setType(type);
        item.setPosition(12, 34);
        item.setSize(56, 78);
        item.setParent(parent);
        item.setRoot(root);
        return item;
    }
}
