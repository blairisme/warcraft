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
import com.evilbird.test.verifier.SerializationVerifier;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Instances of this unit test validate the {@link AlignAction} class.
 *
 * @author Blair Butterworth
 */
public class DirectionActionTest
{
    private DirectionAction action;

    @Before
    public void setup() {
        action = new DirectionAction();
        action.setItem(TestItems.newItem("directionaction"));
    }

//    @Test
//    public void serializeTest() throws IOException {
//        SerializationVerifier.forClass(DirectionAction.class)
//                .withDeserializedForm(action)
//                .withSerializedResource("/action/common/directionaction.json")
//                .verify();
//    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(DirectionAction.class)
                .withMockedTransientFields(Item.class)
                .excludeTransientFields()
                .verify();
    }
}