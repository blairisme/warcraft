/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.test.testcase;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.common.serialization.SerializedType;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.item.Item;
import com.evilbird.test.data.item.TestCombatants;
import com.evilbird.test.utils.TestUtils;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import com.evilbird.warcraft.item.unit.UnitType;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Instances of this test case provide common validation for {@link Action
 * Actions}.
 *
 * @author Blair Butterworth
 */
public abstract class ActionTestCase extends GameTestCase
{
    protected Item item;
    protected Item target;
    protected UserInput cause;
    protected Action action;

    @Before
    public void setup() {
        super.setup();

        item = newItem();
        target = newTarget();
        cause = newCause();

        action = newAction();
        action.setItem(item);
        action.setTarget(target);
        action.setCause(cause);

        respondWithAction(action);
    }

    protected Item newItem() {
        return TestCombatants.newTestCombatant(new TextIdentifier("item"), UnitType.Footman);
    }

    protected Item newTarget() {
        return TestCombatants.newTestCombatant(new TextIdentifier("target"), UnitType.Grunt);
    }

    protected UserInput newCause() {
        return new UserInput(UserInputType.Action, Vector2.Zero, 1);
    }

    protected abstract Action newAction();

    protected abstract Enum newActionId();

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(Action.class)
            .withMockedTransientFields(Item.class)
            .excludeTransientFields()
            .verify();
    }

    @Test
    public void serializeTest() throws IOException {
        Enum id = newActionId();
        String actionId = id.name();
        String actionType = getTypeName(id.getClass());

        String serialized = TestUtils.readResource("/test/actiontestcase.json");
        serialized = serialized.replace("${Action}", actionId);
        serialized = serialized.replace("${ActionType}", actionType);

        SerializationVerifier.forClass(Action.class)
            .withDeserializedForm(action)
            .withSerializedForm(serialized)
            .verify();
    }

    public static String getTypeName(Class<?> type) {
        SerializedType serializedType = type.getAnnotation(SerializedType.class);
        if (serializedType == null) {
            return type.getName();
        }
        return serializedType.value();
    }
}