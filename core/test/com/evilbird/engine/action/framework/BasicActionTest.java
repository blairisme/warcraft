/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.item.Item;
import com.evilbird.test.data.action.TestBasicAction;
import com.evilbird.test.data.action.TestBasicActions;
import com.evilbird.test.verifier.EqualityVerifier;
import org.junit.Before;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link BasicAction} class.
 *
 * @author Blair Butterworth
 */
public class BasicActionTest
{
    private Action action;

    @Before
    public void setup() {
        action = TestBasicActions.newBasicAction();
    }

//    @Test
//    public void serializeTest() throws IOException {
//        SerializationVerifier.forClass(TestBasicAction.class)
//            .withDeserializedForm(action)
//            .withSerializedResource("/action/framework/basicaction.json")
//            .verify();
//    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(TestBasicAction.class)
            .withMockedTransientFields(Item.class)
            .excludeTransientFields()
            .verify();
    }
}