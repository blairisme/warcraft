/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import com.evilbird.warcraft.item.unit.UnitType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

/**
 * Instances of this unit test validate the {@link Item} class.
 *
 * @author Blair Butterworth
 */
public class ItemTest extends GameTestCase
{
    private Item item;

    @Before
    public void setup() {
        super.setup();
        item = newItem();
        Mockito.when(itemFactory.newItem(Mockito.any())).thenReturn(item);
    }

    private Item newItem() {
        Item item = new Item();
        item.setType(UnitType.Footman);
        item.setPosition(12, 34);
        item.setSize(56, 78);
        return item;
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(Item.class)
            .withDeserializedForm(item)
            .withSerializedResource("/item.json")
            .verify();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(Item.class)
            .withMockedTransientFields()
            .excludeTransientFields()
            .verify();
    }
}