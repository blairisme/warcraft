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
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.Building;

public class TestBuildings
{
    private TestBuildings() {
    }

    public static Building newTestBuilding(String id) {
        return newTestBuilding(new TextIdentifier(id), UnitType.Barracks);
    }

    public static Building newTestBuilding(Identifier identifier, Identifier type) {
        Building item = new Building();
        item.setIdentifier(identifier);
        item.setType(type);
        item.setPosition(12, 34);
        item.setSize(56, 78);
        item.setRoot(TestItemRoots.newItemRoot(new TextIdentifier("root")));
        return item;
    }
}
