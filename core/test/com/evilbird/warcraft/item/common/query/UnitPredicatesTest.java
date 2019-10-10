/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.query;

import com.evilbird.warcraft.item.common.state.PerishableObject;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.critter.Critter;
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
        Predicate<Item> predicate = UnitPredicates.isAlive();
        PerishableObject item = mock(PerishableObject.class);

        when(item.getHealth()).thenReturn(40f);
        assertTrue(predicate.test(item));

        when(item.getHealth()).thenReturn(0f);
        assertFalse(predicate.test(item));
    }

    @Test
    public void isAliveNonUnit() {
        Predicate<Item> predicate = UnitPredicates.isAlive();
        Item item = mock(Item.class);
        assertFalse(predicate.test(item));
    }

    @Test
    public void isAliveNull() {
        Predicate<Item> predicate = UnitPredicates.isAlive();
        assertFalse(predicate.test(null));
    }

    @Test
    public void isCorporealTest() {
        Player player = mock(Player.class);
        when(player.isCorporeal()).thenReturn(true);

        Predicate<Item> predicate = UnitPredicates.isCorporeal();
        assertTrue(predicate.test(player));
    }

    @Test
    public void isCorporealNullTest() {
        Predicate<Item> predicate = UnitPredicates.isCorporeal();
        assertFalse(predicate.test(null));
    }

    @Test
    public void isCorporealParentTest() {
        Player player = mock(Player.class);
        when(player.isCorporeal()).thenReturn(true);

        Item child = mock(Item.class);
        when(child.getParent()).thenReturn(player);

        Predicate<Item> predicate = UnitPredicates.isCorporeal();
        assertTrue(predicate.test(child));
    }

    @Test
    public void isCorporealNoParentTest() {
        Item child = mock(Item.class);
        when(child.getParent()).thenReturn(null);

        Predicate<Item> predicate = UnitPredicates.isCorporeal();
        assertFalse(predicate.test(child));
    }

    @Test
    public void isCorporealHierarchyTest() {
        Player player = mock(Player.class);
        when(player.isCorporeal()).thenReturn(true);

        ItemGroup child = mock(ItemGroup.class);
        when(child.getParent()).thenReturn(player);

        Item grandChild = mock(Item.class);
        when(grandChild.getParent()).thenReturn(child);

        Predicate<Item> predicate = UnitPredicates.isCorporeal();
        assertTrue(predicate.test(grandChild));
    }

    @Test
    public void isAiTest() {
        Player player = mock(Player.class);
        when(player.isCorporeal()).thenReturn(false);

        Predicate<Item> predicate = UnitPredicates.isAi();
        assertTrue(predicate.test(player));
    }

    @Test
    public void isAiNullTest() {
        Predicate<Item> predicate = UnitPredicates.isAi();
        assertFalse(predicate.test(null));
    }

    @Test
    public void isAiParentTest() {
        Player player = mock(Player.class);
        when(player.isCorporeal()).thenReturn(false);

        Item child = mock(Item.class);
        when(child.getParent()).thenReturn(player);

        Predicate<Item> predicate = UnitPredicates.isAi();
        assertTrue(predicate.test(child));
    }

    @Test
    public void isAiNoParentTest() {
        Item child = mock(Item.class);
        when(child.getParent()).thenReturn(null);

        Predicate<Item> predicate = UnitPredicates.isAi();
        assertFalse(predicate.test(child));
    }

    @Test
    public void isAiHierarchyTest() {
        Player player = mock(Player.class);
        when(player.isCorporeal()).thenReturn(false);

        ItemGroup child = mock(ItemGroup.class);
        when(child.getParent()).thenReturn(player);

        Item grandChild = mock(Item.class);
        when(grandChild.getParent()).thenReturn(child);

        Predicate<Item> predicate = UnitPredicates.isAi();
        assertTrue(predicate.test(grandChild));
    }

    @Test
    public void isNeutralTest() {
        Player player = mock(Player.class);
        when(player.isNeutral()).thenReturn(true);

        Predicate<Item> predicate = UnitPredicates.isNeutral();
        assertTrue(predicate.test(player));
    }

    @Test
    public void isNeutralNullTest() {
        Predicate<Item> predicate = UnitPredicates.isNeutral();
        assertFalse(predicate.test(null));
    }

    @Test
    public void isNeutralParentTest() {
        Player player = mock(Player.class);
        when(player.isNeutral()).thenReturn(true);

        Item child = mock(Item.class);
        when(child.getParent()).thenReturn(player);

        Predicate<Item> predicate = UnitPredicates.isNeutral();
        assertTrue(predicate.test(child));
    }

    @Test
    public void isNeutralNoParentTest() {
        Item child = mock(Item.class);
        when(child.getParent()).thenReturn(null);

        Predicate<Item> predicate = UnitPredicates.isNeutral();
        assertFalse(predicate.test(child));
    }

    @Test
    public void isNeutralHierarchyTest() {
        Player player = mock(Player.class);
        when(player.isNeutral()).thenReturn(true);

        ItemGroup child = mock(ItemGroup.class);
        when(child.getParent()).thenReturn(player);

        Item grandChild = mock(Item.class);
        when(grandChild.getParent()).thenReturn(child);

        Predicate<Item> predicate = UnitPredicates.isNeutral();
        assertTrue(predicate.test(grandChild));
    }

    @Test
    public void isBuildingTest() {
        Item building = mock(Building.class);
        Item notBuilding = mock(Combatant.class);

        Predicate<Item> predicate = UnitPredicates.isBuilding();
        assertTrue(predicate.test(building));
        assertFalse(predicate.test(notBuilding));
        assertFalse(predicate.test(null));
    }

    @Test
    public void isCombatantTest() {
        Item combatant = mock(Combatant.class);
        Item notCombatant = mock(Building.class);

        Predicate<Item> predicate = UnitPredicates.isCombatant();
        assertTrue(predicate.test(combatant));
        assertFalse(predicate.test(notCombatant));
        assertFalse(predicate.test(null));
    }

    @Test
    public void isCritterTest() {
        Item critter = mock(Critter.class);
        Item notCritter = mock(Building.class);

        Predicate<Item> predicate = UnitPredicates.isCritter();
        assertTrue(predicate.test(critter));
        assertFalse(predicate.test(notCritter));
        assertFalse(predicate.test(null));
    }

    @Test
    public void isAttackerTest() {
        Combatant combatant = mock(Combatant.class);
        when(combatant.getAttackSpeed()).thenReturn(10f);

        Predicate<Item> predicate = UnitPredicates.isAttacker();
        assertTrue(predicate.test(combatant));
    }

    @Test
    public void isAttackerWithoutDamageTest() {
        Combatant combatant = mock(Combatant.class);
        when(combatant.getAttackSpeed()).thenReturn(0f);

        Predicate<Item> predicate = UnitPredicates.isAttacker();
        assertFalse(predicate.test(combatant));
    }

    @Test
    public void isAttackerNonCombatantTest() {
        Building building = mock(Building.class);
        Predicate<Item> predicate = UnitPredicates.isAttacker();
        assertFalse(predicate.test(building));
    }

    @Test
    public void isAttackerNullTest() {
        Predicate<Item> predicate = UnitPredicates.isAttacker();
        assertFalse(predicate.test(null));
    }
}