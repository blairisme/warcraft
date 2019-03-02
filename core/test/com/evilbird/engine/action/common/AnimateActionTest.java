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
import com.evilbird.warcraft.item.unit.UnitAnimation;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Instances of this unit test validate the {@link AnimateAction} class.
 *
 * @author Blair Butterworth
 */
public class AnimateActionTest
{
    private AnimateAction action;

    @Before
    public void setup() {
        action = new AnimateAction(UnitAnimation.Attack);
        action.setItem(TestItems.newItem("animateactiontest"));
    }

//    @Test
//    public void serializeTest() throws IOException {
//        SerializationVerifier.forClass(AnimateAction.class)
//                .withDeserializedForm(action)
//                .withSerializedResource("/action/common/animateaction.json")
//                .verify();
//    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(AnimateAction.class)
                .withMockedTransientFields(Item.class)
                .excludeTransientFields()
                .verify();
    }
}