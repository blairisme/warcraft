package com.evilbird.warcraft.action.move;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.item.Item;
import com.evilbird.test.data.item.TestCombatants;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class MoveToLocationTest  extends GameTestCase
{
    private Combatant item;
    private UserInput input;
    private MoveToLocation action;

    @Before
    public void setup() {
        super.setup();

        item = TestCombatants.newTestCombatant(new TextIdentifier("footman"), UnitType.Footman);
        input = new UserInput(UserInputType.Action, Vector2.Zero, 1);

        action = new MoveToLocation();
        action.setItem(item);
        action.setCause(input);

        respondWithAction(action);
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(Action.class)
                .withDeserializedForm(action)
                .withSerializedResource("/warcraft/action/move/movetolocation.json")
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