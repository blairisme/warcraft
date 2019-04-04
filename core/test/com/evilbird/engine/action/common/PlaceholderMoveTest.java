/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.common;

import com.evilbird.engine.item.Item;
import com.evilbird.test.data.item.TestItems;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.warcraft.action.placeholder.PlaceholderMove;
import org.junit.Before;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link PlaceholderMove} class.
 *
 * @author Blair Butterworth
 */
public class PlaceholderMoveTest
{
    private PlaceholderMove action;

    @Before
    public void setup() {
        action = new PlaceholderMove();
        action.setItem(TestItems.newItem("repositionaction"));
    }

//    @Test
//    public void serializeTest() throws IOException {
//        SerializationVerifier.forClass(PlaceholderMove.class)
//            .withDeserializedForm(action)
//            .withSerializedResource("/action/common/repositionaction.json")
//            .verify();
//    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(PlaceholderMove.class)
            .withMockedTransientFields(Item.class)
            .excludeTransientFields()
            .verify();
    }
}