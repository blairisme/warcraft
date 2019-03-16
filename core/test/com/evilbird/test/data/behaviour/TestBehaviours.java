/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.test.data.behaviour;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.TextIdentifier;

public class TestBehaviours
{
    private TestBehaviours() {
    }

    public static Behaviour newBehaviour(String id) {
        return newBehaviour(new TextIdentifier(id));
    }

    public static Behaviour newBehaviour(Identifier id) {
        TestBehaviour result = new TestBehaviour();
        result.setIdentifier(id);
        return result;
    }
}
