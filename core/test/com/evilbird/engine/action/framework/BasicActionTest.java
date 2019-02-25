/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.item.Item;
import com.evilbird.test.mock.action.MockBasicAction;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.SerializationVerifier;
import com.evilbird.warcraft.action.move.MoveActions;
import com.evilbird.warcraft.item.unit.UnitType;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class BasicActionTest extends GameTestCase
{
    private Action action;

    @Before
    public void setup() {
        super.setup();
        action = newAction();
    }

    private Item newItem(String id) {
        Item item = new Item();
        item.setId(new TextIdentifier(id));
        item.setType(UnitType.Footman);
        item.setPosition(12, 34);
        item.setSize(56, 78);
        return item;
    }

    private UserInput newUserInput() {
        return new UserInput(UserInputType.Action, Vector2.Zero, 1);
    }

    private Action newAction() {
        Action action = new MockBasicAction();
        action.setItem(newItem("1"));
        action.setTarget(newItem("2"));
        action.setCause(newUserInput());
        action.setError(new IOException());
        action.setIdentifier(MoveActions.MoveToItem);
        return action;
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(MockBasicAction.class)
            .withDeserializedForm(action)
            .withSerializedResource("/basicaction.json")
            .verify();
    }
}