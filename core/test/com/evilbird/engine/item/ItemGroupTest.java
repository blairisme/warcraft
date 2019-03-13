/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.test.data.item.TestItems;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.SerializationVerifier;
import com.evilbird.warcraft.item.data.DataType;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Instances of this unit test validate the {@link ItemGroup} class.
 *
 * @author Blair Butterworth
 */
public class ItemGroupTest extends GameTestCase
{
    private ItemGroup group;

    @Before
    public void setup() {
        super.setup();

        group = new ItemGroup();
        group.setIdentifier(new TextIdentifier("Player1"));
        group.setType(DataType.Player);
        group.addItem(TestItems.newItem("1"));
        group.addItem(TestItems.newItem("2"));
        group.addItem(TestItems.newItem("3"));
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(ItemGroup.class)
            .withDeserializedForm(group)
            .withSerializedResource("/item/itemgroup.json")
            .verify();
    }

//    @Test
//    public void equalsTest() {
//        EqualityVerifier.forClass(ItemGroup.class)
//            .withMockedTransientFields()
//            .excludeTransientFields()
//            .verify();
//    }
}