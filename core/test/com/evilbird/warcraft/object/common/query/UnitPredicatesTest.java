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
import com.evilbird.warcraft.object.common.capability.OffensiveCapability;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.building.Building;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import com.evilbird.warcraft.object.unit.critter.Critter;
import org.junit.Test;

import java.util.function.Predicate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Instances of this unit test validate the {@link UnitPredicates} class.
 *
 * @author Blair Butterworth
 */
public class UnitPredicatesTest
{
    @Test
    public void isAlive() {
        Predicate<GameObject> predicate = UnitPredicates.isAlive();
        PerishableObject item = mock(PerishableObject.class);

        when(item.isAlive()).thenReturn(true);
        assertTrue(predicate.test(item));

        when(item.isAlive()).thenReturn(false);
        assertFalse(predicate.test(item));
    }

    @Test
    public void isAliveNonUnit() {
        Predicate<GameObject> predicate = UnitPredicates.isAlive();
        GameObject gameObject = mock(GameObject.class);
        assertFalse(predicate.test(gameObject));
    }

    @Test
    public void isAliveNull() {
        Predicate<GameObject> predicate = UnitPredicates.isAlive();
        assertFalse(predicate.test(null));
    }

    @Test
    public void isCorporealTest() {
        Player player = mock(Player.class);
        when(player.isCorporeal()).thenReturn(true);

        Predicate<GameObject> predicate = UnitPredicates.isCorporeal();
        assertTrue(predicate.test(player));
    }

    @Test
    public void isCorporealNullTest() {
        Predicate<GameObject> predicate = UnitPredicates.isCorporeal();
        assertFalse(predicate.test(null));
    }

    @Test
    public void isCorporealParentTest() {
        Player player = mock(Player.class);
        when(player.isCorporeal()).thenReturn(true);

        GameObject child = mock(GameObject.class);
        when(child.getParent()).thenReturn(player);

        Predicate<GameObject> predicate = UnitPredicates.isCorporeal();
        assertTrue(predicate.test(child));
    }

    @Test
    public void isCorporealNoParentTest() {
        GameObject child = mock(GameObject.class);
        when(child.getParent()).thenReturn(null);

        Predicate<GameObject> predicate = UnitPredicates.isCorporeal();
        assertFalse(predicate.test(child));
    }

    @Test
    public void isCorporealHierarchyTest() {
        Player player = mock(Player.class);
        when(player.isCorporeal()).thenReturn(true);

        GameObjectGroup child = mock(GameObjectGroup.class);
        when(child.getParent()).thenReturn(player);

        GameObject grandChild = mock(GameObject.class);
        when(grandChild.getParent()).thenReturn(child);

        Predicate<GameObject> predicate = UnitPredicates.isCorporeal();
        assertTrue(predicate.test(grandChild));
    }

    @Test
    public void isAiTest() {
        Player player = mock(Player.class);
        when(player.isCorporeal()).thenReturn(false);

        Predicate<GameObject> predicate = UnitPredicates.isAi();
        assertTrue(predicate.test(player));
    }

    @Test
    public void isAiNullTest() {
        Predicate<GameObject> predicate = UnitPredicates.isAi();
        assertFalse(predicate.test(null));
    }

    @Test
    public void isAiParentTest() {
        Player player = mock(Player.class);
        when(player.isCorporeal()).thenReturn(false);

        GameObject child = mock(GameObject.class);
        when(child.getParent()).thenReturn(player);

        Predicate<GameObject> predicate = UnitPredicates.isAi();
        assertTrue(predicate.test(child));
    }

    @Test
    public void isAiNoParentTest() {
        GameObject child = mock(GameObject.class);
        when(child.getParent()).thenReturn(null);

        Predicate<GameObject> predicate = UnitPredicates.isAi();
        assertFalse(predicate.test(child));
    }

    @Test
    public void isAiHierarchyTest() {
        Player player = mock(Player.class);
        when(player.isCorporeal()).thenReturn(false);

        GameObjectGroup child = mock(GameObjectGroup.class);
        when(child.getParent()).thenReturn(player);

        GameObject grandChild = mock(GameObject.class);
        when(grandChild.getParent()).thenReturn(child);

        Predicate<GameObject> predicate = UnitPredicates.isAi();
        assertTrue(predicate.test(grandChild));
    }

    @Test
    public void isNeutralTest() {
        Player player = mock(Player.class);
        when(player.isNeutral()).thenReturn(true);

        Predicate<GameObject> predicate = UnitPredicates.isNeutral();
        assertTrue(predicate.test(player));
    }

    @Test
    public void isNeutralNullTest() {
        Predicate<GameObject> predicate = UnitPredicates.isNeutral();
        assertFalse(predicate.test(null));
    }

    @Test
    public void isNeutralParentTest() {
        Player player = mock(Player.class);
        when(player.isNeutral()).thenReturn(true);

        GameObject child = mock(GameObject.class);
        when(child.getParent()).thenReturn(player);

        Predicate<GameObject> predicate = UnitPredicates.isNeutral();
        assertTrue(predicate.test(child));
    }

    @Test
    public void isNeutralNoParentTest() {
        GameObject child = mock(GameObject.class);
        when(child.getParent()).thenReturn(null);

        Predicate<GameObject> predicate = UnitPredicates.isNeutral();
        assertFalse(predicate.test(child));
    }

    @Test
    public void isNeutralHierarchyTest() {
        Player player = mock(Player.class);
        when(player.isNeutral()).thenReturn(true);

        GameObjectGroup child = mock(GameObjectGroup.class);
        when(child.getParent()).thenReturn(player);

        GameObject grandChild = mock(GameObject.class);
        when(grandChild.getParent()).thenReturn(child);

        Predicate<GameObject> predicate = UnitPredicates.isNeutral();
        assertTrue(predicate.test(grandChild));
    }

    @Test
    public void isBuildingTest() {
        GameObject building = mock(Building.class);
        GameObject notBuilding = mock(Combatant.class);

        Predicate<GameObject> predicate = UnitPredicates.isBuilding();
        assertTrue(predicate.test(building));
        assertFalse(predicate.test(notBuilding));
        assertFalse(predicate.test(null));
    }

    @Test
    public void isCombatantTest() {
        GameObject combatant = mock(Combatant.class);
        GameObject notCombatant = mock(Building.class);

        Predicate<GameObject> predicate = UnitPredicates.isCombatant();
        assertTrue(predicate.test(combatant));
        assertFalse(predicate.test(notCombatant));
        assertFalse(predicate.test(null));
    }

    @Test
    public void isCritterTest() {
        GameObject critter = mock(Critter.class);
        GameObject notCritter = mock(Building.class);

        Predicate<GameObject> predicate = UnitPredicates.isCritter();
        assertTrue(predicate.test(critter));
        assertFalse(predicate.test(notCritter));
        assertFalse(predicate.test(null));
    }

    @Test
    public void isAttackerTest() {
        Combatant combatant = mock(Combatant.class);
        when(combatant.getAttackCapability()).thenReturn(OffensiveCapability.Air);

        Predicate<GameObject> predicate = UnitPredicates.isAttacker();
        assertTrue(predicate.test(combatant));
    }

    @Test
    public void isAttackerWithoutDamageTest() {
        Combatant combatant = mock(Combatant.class);
        when(combatant.getAttackCapability()).thenReturn(OffensiveCapability.None);

        Predicate<GameObject> predicate = UnitPredicates.isAttacker();
        assertFalse(predicate.test(combatant));
    }

    @Test
    public void isAttackerNonCombatantTest() {
        Building building = mock(Building.class);
        Predicate<GameObject> predicate = UnitPredicates.isAttacker();
        assertFalse(predicate.test(building));
    }

    @Test
    public void isAttackerNullTest() {
        Predicate<GameObject> predicate = UnitPredicates.isAttacker();
        assertFalse(predicate.test(null));
    }
}