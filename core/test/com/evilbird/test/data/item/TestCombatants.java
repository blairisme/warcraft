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
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import static com.evilbird.test.data.item.TestPlayers.newTestPlayer;

public class TestCombatants
{
    private TestCombatants() {
    }

    public static Combatant newTestCombatant(String id) {
        return newTestCombatant(new TextIdentifier(id), UnitType.Footman);
    }

    public static Combatant newTestCombatant(Identifier identifier, Identifier type) {
        return newTestCombatant(identifier, type, TestItemRoots.newTestRoot("root"), newTestPlayer("parent"));
    }

    public static Combatant newTestCombatant(Identifier identifier, Identifier type, ItemRoot root, Player parent) {
        Combatant item = new Combatant();
        item.setIdentifier(identifier);
        item.setType(type);
        item.setPosition(12, 34);
        item.setSize(56, 78);
        item.setParent(parent);
        item.setRoot(root);
        return item;
    }
}