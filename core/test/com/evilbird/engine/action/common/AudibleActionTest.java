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
import com.evilbird.warcraft.item.unit.UnitSound;
import org.junit.Before;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link AudibleAction} class.
 *
 * @author Blair Butterworth
 */
public class AudibleActionTest
{
    private AudibleAction action;

    @Before
    public void setup() {
        action = new AudibleAction(UnitSound.Attack);
        action.setItem(TestItems.newItem("audibleaction"));
    }

//    @Test
//    public void serializeTest() throws IOException {
//        SerializationVerifier.forClass(AudibleAction.class)
//            .withDeserializedForm(action)
//            .withSerializedResource("/action/common/audibleaction.json")
//            .verify();
//    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(AudibleAction.class)
            .withMockedTransientFields(Item.class)
            .excludeTransientFields()
            .verify();
    }
}