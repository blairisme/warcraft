/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.utility;

import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.warcraft.item.data.player.Player;
import org.junit.Test;

import static com.evilbird.engine.item.utility.ItemPredicates.withClazz;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Instances of this unit test validate the {@link ItemOperations} class.
 *
 * @author Blair Butterworth
 */
public class ItemOperationsTest
{
    @Test
    public void findAncestorTest() {
        Player player = mock(Player.class);
        when(player.isCorporeal()).thenReturn(true);

        ItemGroup child = mock(ItemGroup.class);
        when(child.getParent()).thenReturn(player);

        Item grandChild = mock(Item.class);
        when(grandChild.getParent()).thenReturn(child);

        Item result = ItemOperations.findAncestor(grandChild, withClazz(Player.class));
        assertEquals(player, result);
    }

    @Test
    public void findAncestorNoMatchTest() {
        Player player = mock(Player.class);
        when(player.isCorporeal()).thenReturn(true);

        ItemGroup child = mock(ItemGroup.class);
        when(child.getParent()).thenReturn(player);

        Item grandChild = mock(Item.class);
        when(grandChild.getParent()).thenReturn(child);

        Item result = ItemOperations.findAncestor(grandChild, withClazz(String.class));
        assertNull(result);
    }

    @Test
    public void findAncestorParentTest() {
        Player player = mock(Player.class);
        when(player.isCorporeal()).thenReturn(true);

        ItemGroup child = mock(ItemGroup.class);
        when(child.getParent()).thenReturn(player);

        Item result = ItemOperations.findAncestor(child, withClazz(Player.class));
        assertEquals(player, result);
    }

    @Test
    public void findAncestorSelfTest() {
        Player player = mock(Player.class);
        when(player.isCorporeal()).thenReturn(true);

        Item result = ItemOperations.findAncestor(player, withClazz(Player.class));
        assertEquals(player, result);
    }

    @Test (expected = NullPointerException.class)
    public void findAncestorNullHierarchyTest() {
        ItemOperations.findAncestor(null, withClazz(Player.class));
    }

    @Test (expected = NullPointerException.class)
    public void findAncestorNullConditionTest() {
        Player player = mock(Player.class);
        ItemOperations.findAncestor(player, null);
    }
}