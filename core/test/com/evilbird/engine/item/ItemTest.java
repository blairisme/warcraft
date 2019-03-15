/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.serialization.SerializedType;
import com.evilbird.test.data.action.TestBasicAction;
import com.evilbird.test.data.item.TestItems;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
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
    private Action actionA;
    private Action actionB;

    @SerializedType("TestAction")
    private enum TestAction implements ActionIdentifier {
        ActionA,
        ActionB
    }

    @Before
    public void setup() {
        super.setup();
        actionA = new TestBasicAction();
        actionA.setIdentifier(TestAction.ActionA);
        Mockito.when(actionFactory.newAction(TestAction.ActionA)).thenReturn(actionA);

        actionB = new TestBasicAction();
        actionB.setIdentifier(TestAction.ActionB);
        Mockito.when(actionFactory.newAction(TestAction.ActionB)).thenReturn(actionB);

        item = TestItems.newItem("1");
        item.addAction(actionA);
        item.addAction(actionB);
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