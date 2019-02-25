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
import com.evilbird.engine.action.ActionException;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.item.Item;
import com.evilbird.test.data.action.TestBasicAction;
import com.evilbird.test.data.item.TestItems;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import com.evilbird.warcraft.action.move.MoveActions;
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

    private UserInput newUserInput() {
        return new UserInput(UserInputType.Action, Vector2.Zero, 1);
    }

    private Action newAction() {
        Action action = new TestBasicAction();
        action.setItem(TestItems.newItem("1"));
        action.setTarget(TestItems.newItem("2"));
        action.setCause(newUserInput());
        action.setError(new ActionException("An error has occurred"));
        action.setIdentifier(MoveActions.MoveToItem);
        return action;
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(TestBasicAction.class)
            .withDeserializedForm(action)
            .withSerializedResource("/basicaction.json")
            .verify();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(TestBasicAction.class)
            .withMockedTransientFields(Item.class)
            .excludeTransientFields()
            .verify();
    }
}