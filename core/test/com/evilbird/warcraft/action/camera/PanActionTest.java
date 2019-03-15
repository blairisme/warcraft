/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.camera;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.item.Item;
import com.evilbird.test.data.item.TestItems;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class PanActionTest extends GameTestCase
{
    private Item item;
    private UserInput input;
    private PanAction action;

    @Before
    public void setup() {
        super.setup();

        item = TestItems.newItem("footman");
        input = new UserInput(UserInputType.Action, Vector2.Zero, 1);

        action = new PanAction();
        action.setItem(item);
        action.setCause(input);

        respondWithAction(action);
    }

    @Test
    @Ignore //TODO: Jacoco error
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(Action.class)
            .withDeserializedForm(action)
            .withSerializedResource("/warcraft/action/panaction.json")
            .verify();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(Action.class)
            .withMockedTransientFields(Item.class)
            .excludeTransientFields()
            .verify();
    }
}