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
import org.junit.Before;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link SelectAction} class.
 *
 * @author Blair Butterworth
 */
public class SelectActionTest
{
    private SelectAction action;

    @Before
    public void setup() {
        action = new SelectAction(true);
        action.setItem(TestItems.newItem("selectaction"));
    }

//    @Test
//    public void serializeTest() throws IOException {
//        SerializationVerifier.forClass(SelectAction.class)
//            .withDeserializedForm(action)
//            .withSerializedResource("/action/common/selectaction.json")
//            .verify();
//    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(SelectAction.class)
            .withMockedTransientFields(Item.class)
            .excludeTransientFields()
            .verify();
    }
}