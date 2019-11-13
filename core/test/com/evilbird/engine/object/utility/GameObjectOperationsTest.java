/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.object.utility;

import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectGroup;
import com.evilbird.test.data.item.TestCombatants;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import org.junit.Assert;
import org.junit.Test;

import static com.evilbird.engine.object.utility.GameObjectPredicates.withClazz;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Instances of this unit test validate the {@link GameObjectOperations} class.
 *
 * @author Blair Butterworth
 */
public class GameObjectOperationsTest extends GameTestCase
{
    @Test
    public void findAncestorTest() {
        Player player = mock(Player.class);
        when(player.isCorporeal()).thenReturn(true);

        GameObjectGroup child = mock(GameObjectGroup.class);
        when(child.getParent()).thenReturn(player);

        GameObject grandChild = mock(GameObject.class);
        when(grandChild.getParent()).thenReturn(child);

        GameObject result = GameObjectOperations.findAncestor(grandChild, withClazz(Player.class));
        assertEquals(player, result);
    }

    @Test
    public void findAncestorNoMatchTest() {
        Player player = mock(Player.class);
        when(player.isCorporeal()).thenReturn(true);

        GameObjectGroup child = mock(GameObjectGroup.class);
        when(child.getParent()).thenReturn(player);

        GameObject grandChild = mock(GameObject.class);
        when(grandChild.getParent()).thenReturn(child);

        GameObject result = GameObjectOperations.findAncestor(grandChild, withClazz(String.class));
        assertNull(result);
    }

    @Test
    public void findAncestorParentTest() {
        Player player = mock(Player.class);
        when(player.isCorporeal()).thenReturn(true);

        GameObjectGroup child = mock(GameObjectGroup.class);
        when(child.getParent()).thenReturn(player);

        GameObject result = GameObjectOperations.findAncestor(child, withClazz(Player.class));
        assertEquals(player, result);
    }

    @Test
    public void findAncestorSelfTest() {
        Player player = mock(Player.class);
        when(player.isCorporeal()).thenReturn(true);

        GameObject result = GameObjectOperations.findAncestor(player, withClazz(Player.class));
        assertEquals(player, result);
    }

    @Test (expected = NullPointerException.class)
    public void findAncestorNullHierarchyTest() {
        GameObjectOperations.findAncestor(null, withClazz(Player.class));
    }

    @Test (expected = NullPointerException.class)
    public void findAncestorNullConditionTest() {
        Player player = mock(Player.class);
        GameObjectOperations.findAncestor(player, null);
    }

    @Test
    public void isNearTest() {
        Combatant combatant1 = TestCombatants.newTestCombatant("footman");
        Combatant combatant2 = TestCombatants.newTestCombatant("grunt");

        combatant1.setSize(32, 32);
        combatant2.setSize(32, 32);

        combatant1.setPosition(100, 100);
        combatant2.setPosition(125, 125);

        Assert.assertTrue(GameObjectOperations.isNear(combatant1, 50, combatant2));
    }

    @Test
    public void isNearWithRadiusTest() {
        Combatant combatant1 = TestCombatants.newTestCombatant("footman");
        Combatant combatant2 = TestCombatants.newTestCombatant("grunt");

        combatant1.setSize(10, 10);
        combatant2.setSize(10, 10);

        combatant1.setPosition(0, 0);
        combatant2.setPosition(20, 20);

        Assert.assertTrue(GameObjectOperations.isNear(combatant1, 50, combatant2));
    }

    @Test
    public void isNotNearTest() {
        Combatant combatant1 = TestCombatants.newTestCombatant("footman");
        Combatant combatant2 = TestCombatants.newTestCombatant("grunt");

        combatant1.setSize(32, 32);
        combatant2.setSize(32, 32);

        combatant1.setPosition(100, 100);
        combatant2.setPosition(155, 155);

        Assert.assertFalse(GameObjectOperations.isNear(combatant1, 50, combatant2));
    }

    @Test (expected = NullPointerException.class)
    public void isNearNullLocusTest() {
        Combatant combatant2 = TestCombatants.newTestCombatant("grunt");
        GameObjectOperations.isNear(null, 50, combatant2);
    }

    @Test (expected = NullPointerException.class)
    public void isNearNullTargetTest() {
        Combatant combatant1 = TestCombatants.newTestCombatant("footman");
        GameObjectOperations.isNear(combatant1, 50, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void isNearNegativeRadiusTest() {
        Combatant combatant1 = TestCombatants.newTestCombatant("footman");
        Combatant combatant2 = TestCombatants.newTestCombatant("grunt");
        GameObjectOperations.isNear(combatant1, -50, combatant2);
    }
}