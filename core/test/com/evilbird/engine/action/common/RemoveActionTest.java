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
import com.evilbird.warcraft.action.common.remove.RemoveAction;
import org.junit.Before;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link com.evilbird.warcraft.action.common.remove.RemoveAction} class.
 *
 * @author Blair Butterworth
 */
public class RemoveActionTest
{
    private com.evilbird.warcraft.action.common.remove.RemoveAction action;

    @Before
    public void setup() {
        action = new com.evilbird.warcraft.action.common.remove.RemoveAction(ActionTarget.Item);
        action.setItem(TestItems.newItem("removeaction"));
    }

//    @Test
//    public void serializeTest() throws IOException {
//        SerializationVerifier.forClass(RemoveAction.class)
//            .withDeserializedForm(action)
//            .withSerializedResource("/action/common/removeaction.json")
//            .verify();
//    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(RemoveAction.class)
            .withMockedTransientFields(Item.class)
            .excludeTransientFields()
            .verify();
    }
}