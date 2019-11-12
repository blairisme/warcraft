/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.object.GameObject;
import com.evilbird.test.verifier.EqualityVerifier;
import org.junit.Before;
import org.junit.Test;

public class TemporalActionTest
{
    private TemporalAction action;

    @Before
    public void setup() {
        action = new TemporalAction(123);
    }

//    @Test
//    public void serializeTest() throws IOException {
//        SerializationVerifier.forClass(DelayedAction.class)
//                .withDeserializedForm(action)
//                .withSerializedResource("/action/framework/delayedaction.json")
//                .verify();
//    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(TemporalAction.class)
                .withMockedTransientFields(GameObject.class)
                .excludeTransientFields()
                .verify();
    }
}