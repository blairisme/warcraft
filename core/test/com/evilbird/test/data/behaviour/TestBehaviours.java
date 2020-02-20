/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.test.data.behaviour;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.behaviour.BehaviourIdentifier;

public class TestBehaviours
{
    private TestBehaviours() {
    }

    public static Behaviour newBehaviour(String id) {
        return newBehaviour(new TestBehaviourIdentifier(id));
    }

    public static Behaviour newBehaviour(BehaviourIdentifier id) {
        TestBehaviour result = new TestBehaviour();
        result.setIdentifier(id);
        return result;
    }
}
