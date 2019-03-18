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
import com.evilbird.warcraft.item.unit.combatant.Combatant;

public class TestCombatants
{
    private TestCombatants() {
    }

    public static Combatant newTestCombatant(String id) {
        return newTestCombatant(new TextIdentifier(id), UnitType.Footman);
    }

    public static Combatant newTestCombatant(Identifier identifier, Identifier type) {
        Combatant item = new Combatant();
        item.setIdentifier(identifier);
        item.setType(type);
        item.setPosition(12, 34);
        item.setSize(56, 78);
        item.setParent(TestItemGroups.newItemGroup("parent"));
        item.setRoot(TestItemRoots.newItemRoot(new TextIdentifier("root")));
        return item;
    }
}
