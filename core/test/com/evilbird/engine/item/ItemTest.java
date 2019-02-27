/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

import com.evilbird.test.data.item.TestItems;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import org.junit.Before;
import org.junit.Test;

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
        item = TestItems.newItem("1");
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(Item.class)
            .withDeserializedForm(item)
            .withSerializedResource("/item/item.json")
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