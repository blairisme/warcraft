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
import com.evilbird.test.data.action.TestBasicAction;
import com.evilbird.test.data.item.TestItems;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Instances of this unit test validate the {@link ReplacementAction} class.
 *
 * @author Blair Butterworth
 */
public class ReplacementActionTest
{
    private TestBasicAction replacement;
    private ReplacementAction action;

    @Before
    public void setup() {
        replacement = new TestBasicAction();
        action = new ReplacementAction(replacement);
        action.setItem(TestItems.newItem("replacementaction"));
    }

//    @Test
//    public void serializeTest() throws IOException {
//        SerializationVerifier.forClass(ReplacementAction.class)
//            .withDeserializedForm(action)
//            .withSerializedResource("/action/common/replacementaction.json")
//            .verify();
//    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(ReplacementAction.class)
            .withMockedTransientFields(Item.class)
            .excludeTransientFields()
            .verify();
    }
}