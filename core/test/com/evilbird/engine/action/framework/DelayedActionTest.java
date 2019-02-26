/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.item.Item;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class DelayedActionTest
{
    private DelayedAction action;

    @Before
    public void setup() {
        action = new DelayedAction(123);
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(DelayedAction.class)
                .withDeserializedForm(action)
                .withSerializedResource("/delayedaction.json")
                .verify();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(DelayedAction.class)
                .withMockedTransientFields(Item.class)
                .excludeTransientFields()
                .verify();
    }
}