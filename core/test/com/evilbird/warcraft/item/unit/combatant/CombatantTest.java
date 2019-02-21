/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.serialization.JsonSerializer;
import com.evilbird.engine.common.serialization.Serializer;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.test.mock.device.AssetManagerMocks;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.combatant.human.FootmanFactory;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Instances of this unit test validate logic in the {@link Combatant} class.
 *
 * @author Blair Butterworth
 */
public class CombatantTest
{
    @Test
    //@Ignore
    public void serializeTest() {
        AssetManager assets = AssetManagerMocks.newAssetManagerMock();

        FootmanFactory footmanFactory = new FootmanFactory(assets);
        Item foo = footmanFactory.get();

        ItemFactory itemFactory = Mockito.mock(ItemFactory.class);
        Mockito.when(itemFactory.newItem(UnitType.Footman)).thenReturn(foo);

        Combatant expected = (Combatant)footmanFactory.get();
        expected.setHealth(42);
        expected.setMovementSpeed(2);
        expected.setSelected(true);

        Serializer serializer = new JsonSerializer(assets, itemFactory);
        String json = serializer.serialize(expected, Item.class);

        System.out.println(json);

        Combatant actual = (Combatant)serializer.deserialize(json, Item.class);
        //Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }
}
