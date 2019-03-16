/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.item.Item;
import com.evilbird.test.data.item.TestItems;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import com.evilbird.warcraft.action.move.MoveAction;
import com.evilbird.warcraft.action.move.MoveFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AttackActionTest extends GameTestCase
{
    private Item item;
    private Item target;
    private AttackAction action;

    @Before
    public void setup() {
        super.setup();

        item = TestItems.newItem("footman");
        target = TestItems.newItem("grunt");

        action = new AttackAction(getMoveFactory());
        action.setItem(item);
        action.setTarget(target);

        respondWithAction(action);
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(Action.class)
            .withDeserializedForm(action)
            .withSerializedResource("/warcraft/action/attackaction.json")
            .verify();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(Action.class)
            .withMockedTransientFields(Item.class)
            .excludeTransientFields()
            .verify();
    }

    private MoveFactory getMoveFactory() {
        MoveAction moveAction = mock(MoveAction.class);
        MoveFactory moveFactory = mock(MoveFactory.class);
        when(moveFactory.get(any())).thenReturn(moveAction);
        return moveFactory;
    }
}