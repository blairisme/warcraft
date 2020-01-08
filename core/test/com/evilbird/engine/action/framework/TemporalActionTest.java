/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.action.framework;

import com.badlogic.gdx.utils.Pool;
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
            .withMockedType(Pool.class)
            .excludeTransientFields()
            .verify();
    }
}