/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.common.query;

import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectGroup;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.building.Building;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import com.evilbird.warcraft.object.unit.critter.Critter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Instances of this unit test validate the {@link UnitOperations} class.
 *
 * @author Blair Butterworth
 */
public class UnitOperationsTest
{
    @Test
    public void getPlayerTest() {
        Player player = mock(Player.class);
        when(player.isCorporeal()).thenReturn(true);

        GameObjectGroup child = mock(GameObjectGroup.class);
        when(child.getParent()).thenReturn(player);

        GameObject grandChild = mock(GameObject.class);
        when(grandChild.getParent()).thenReturn(child);

        Player actual = UnitOperations.getPlayer(grandChild);
        assertEquals(actual, player);
    }

    @Test
    public void getPlayerParentTest() {
        Player player = mock(Player.class);
        when(player.isCorporeal()).thenReturn(true);

        GameObjectGroup child = mock(GameObjectGroup.class);
        when(child.getParent()).thenReturn(player);

        Player actual = UnitOperations.getPlayer(child);
        assertEquals(actual, player);
    }

    @Test
    public void getPlayerSelfTest() {
        Player player = mock(Player.class);
        when(player.isCorporeal()).thenReturn(true);

        Player actual = UnitOperations.getPlayer(player);
        assertEquals(actual, player);
    }

    @Test
    public void getPlayerUnownedTest() {
        GameObjectGroup child = mock(GameObjectGroup.class);
        when(child.getParent()).thenReturn(null);

        Player player = UnitOperations.getPlayer(child);
        assertNull(player);
    }

    @Test (expected = NullPointerException.class)
    public void getPlayerNullTest() {
        UnitOperations.getPlayer(null);
    }

    @Test
    public void isAlive() {
        PerishableObject item = mock(PerishableObject.class);

        when(item.getHealth()).thenReturn(40f);
        assertTrue(UnitOperations.isAlive(item));

        when(item.getHealth()).thenReturn(0f);
        assertFalse(UnitOperations.isAlive(item));
    }

    @Test
    public void isAliveNonUnit() {
        GameObject gameObject = mock(GameObject.class);
        assertFalse(UnitOperations.isAlive(gameObject));
    }

    @Test
    public void isAliveNull() {
        assertFalse(UnitOperations.isAlive(null));
    }

    @Test
    public void isAiTest() {
        Player player = mock(Player.class);
        when(player.isCorporeal()).thenReturn(false);

        boolean result = UnitOperations.isAi(player);
        assertTrue(result);
    }

    @Test
    public void isAiNullTest() {
        assertFalse(UnitOperations.isAi(null));
    }

    @Test
    public void isAiParentTest() {
        Player player = mock(Player.class);
        when(player.isCorporeal()).thenReturn(false);

        GameObject child = mock(GameObject.class);
        when(child.getParent()).thenReturn(player);

        boolean result = UnitOperations.isAi(child);
        assertTrue(result);
    }

    @Test
    public void isAiNoParentTest() {
        GameObject child = mock(GameObject.class);
        when(child.getParent()).thenReturn(null);

        boolean result = UnitOperations.isAi(child);
        assertFalse(result);
    }

    @Test
    public void isAiHierarchyTest() {
        Player player = mock(Player.class);
        when(player.isCorporeal()).thenReturn(false);

        GameObjectGroup child = mock(GameObjectGroup.class);
        when(child.getParent()).thenReturn(player);

        GameObject grandChild = mock(GameObject.class);
        when(grandChild.getParent()).thenReturn(child);

        assertTrue(UnitOperations.isAi(grandChild));
    }

    @Test
    public void isArtificialTest() {
        Player player = mock(Player.class);
        when(player.isArtificial()).thenReturn(true);

        assertTrue(UnitOperations.isArtificial(player));
    }

    @Test
    public void isArtificialNullTest() {
        assertFalse(UnitOperations.isArtificial(null));
    }

    @Test
    public void isArtificialParentTest() {
        Player player = mock(Player.class);
        when(player.isArtificial()).thenReturn(true);

        GameObject child = mock(GameObject.class);
        when(child.getParent()).thenReturn(player);

        assertTrue(UnitOperations.isArtificial(child));
    }

    @Test
    public void isArtificialNoParentTest() {
        GameObject child = mock(GameObject.class);
        when(child.getParent()).thenReturn(null);

        assertFalse(UnitOperations.isArtificial(child));
    }

    @Test
    public void isArtificialHierarchyTest() {
        Player player = mock(Player.class);
        when(player.isArtificial()).thenReturn(true);

        GameObjectGroup child = mock(GameObjectGroup.class);
        when(child.getParent()).thenReturn(player);

        GameObject grandChild = mock(GameObject.class);
        when(grandChild.getParent()).thenReturn(child);

        assertTrue(UnitOperations.isArtificial(grandChild));
    }

    @Test
    public void isCorporealTest() {
        Player player = mock(Player.class);
        when(player.isCorporeal()).thenReturn(true);

        boolean result = UnitOperations.isCorporeal(player);
        assertTrue(result);
    }

    @Test
    public void isCorporealNullTest() {
        assertFalse(UnitOperations.isCorporeal(null));
    }

    @Test
    public void isCorporealParentTest() {
        Player player = mock(Player.class);
        when(player.isCorporeal()).thenReturn(true);

        GameObject child = mock(GameObject.class);
        when(child.getParent()).thenReturn(player);

        boolean result = UnitOperations.isCorporeal(child);
        assertTrue(result);
    }

    @Test
    public void isCorporealNoParentTest() {
        GameObject child = mock(GameObject.class);
        when(child.getParent()).thenReturn(null);

        boolean result = UnitOperations.isCorporeal(child);
        assertFalse(result);
    }

    @Test
    public void isCorporealHierarchyTest() {
        Player player = mock(Player.class);
        when(player.isCorporeal()).thenReturn(true);

        GameObjectGroup child = mock(GameObjectGroup.class);
        when(child.getParent()).thenReturn(player);

        GameObject grandChild = mock(GameObject.class);
        when(grandChild.getParent()).thenReturn(child);

        boolean result = UnitOperations.isCorporeal(grandChild);
        assertTrue(result);
    }

    @Test
    public void isNeutralTest() {
        Player player = mock(Player.class);
        when(player.isNeutral()).thenReturn(true);

        boolean result = UnitOperations.isNeutral(player);
        assertTrue(result);
    }

    @Test
    public void isNeutralNullTest() {
        boolean result = UnitOperations.isNeutral(null);
        assertFalse(result);
    }

    @Test
    public void isNeutralParentTest() {
        Player player = mock(Player.class);
        when(player.isNeutral()).thenReturn(true);

        GameObject child = mock(GameObject.class);
        when(child.getParent()).thenReturn(player);

        boolean result = UnitOperations.isNeutral(child);
        assertTrue(result);
    }

    @Test
    public void isNeutralNoParentTest() {
        GameObject child = mock(GameObject.class);
        when(child.getParent()).thenReturn(null);

        boolean result = UnitOperations.isNeutral(child);
        assertFalse(result);
    }

    @Test
    public void isNeutralHierarchyTest() {
        Player player = mock(Player.class);
        when(player.isNeutral()).thenReturn(true);

        GameObjectGroup child = mock(GameObjectGroup.class);
        when(child.getParent()).thenReturn(player);

        GameObject grandChild = mock(GameObject.class);
        when(grandChild.getParent()).thenReturn(child);

        boolean result = UnitOperations.isNeutral(grandChild);
        assertTrue(result);
    }
    
    @Test
    public void isBuildingTest() {
        GameObject building = mock(Building.class);
        GameObject notBuilding = mock(Combatant.class);

        assertTrue(UnitOperations.isBuilding(building));
        assertFalse(UnitOperations.isBuilding(notBuilding));
        assertFalse(UnitOperations.isBuilding(null));
    }

    @Test
    public void isCombatantTest() {
        GameObject combatant = mock(Combatant.class);
        GameObject notCombatant = mock(Building.class);

        assertTrue(UnitOperations.isCombatant(combatant));
        assertFalse(UnitOperations.isCombatant(notCombatant));
        assertFalse(UnitOperations.isCombatant(null));
    }

    @Test
    public void isCritterTest() {
        GameObject critter = mock(Critter.class);
        GameObject notCritter = mock(Building.class);

        assertTrue(UnitOperations.isCritter(critter));
        assertFalse(UnitOperations.isCritter(notCritter));
        assertFalse(UnitOperations.isCritter(null));
    }

    @Test
    public void isAttackerTest() {
        Combatant combatant = mock(Combatant.class);
        when(combatant.getAttackSpeed()).thenReturn(10f);
        assertTrue(UnitOperations.isAttacker(combatant));
    }

    @Test
    public void isAttackerWithoutDamageTest() {
        Combatant combatant = mock(Combatant.class);
        when(combatant.getAttackSpeed()).thenReturn(0f);
        assertFalse(UnitOperations.isAttacker(combatant));
    }

    @Test
    public void isAttackerNonCombatantTest() {
        Building building = mock(Building.class);
        assertFalse(UnitOperations.isAttacker(building));
    }

    @Test
    public void isAttackerNullTest() {
        assertFalse(UnitOperations.isAttacker(null));
    }
}
