/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.object;

import com.evilbird.engine.action.Action;
import com.evilbird.test.data.action.TestBasicAction;
import com.evilbird.test.data.item.TestCombatants;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import com.evilbird.warcraft.action.attack.AttackActions;
import com.evilbird.warcraft.action.move.MoveActions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

/**
 * Instances of this unit test validate the {@link GameObject} class.
 *
 * @author Blair Butterworth
 */
public class GameObjectTest extends GameTestCase
{
    private GameObject gameObject;
    private Action actionA;
    private Action actionB;

    @Before
    public void setup() {
        super.setup();
        actionA = new TestBasicAction();
        actionA.setIdentifier(MoveActions.MoveToLocation);
        Mockito.when(actionFactory.get(MoveActions.MoveToLocation)).thenReturn(actionA);

        actionB = new TestBasicAction();
        actionB.setIdentifier(AttackActions.Attack);
        Mockito.when(actionFactory.get(AttackActions.Attack)).thenReturn(actionB);

        gameObject = TestCombatants.newTestCombatant("footman");
        gameObject.addAction(actionA);
        gameObject.addAction(actionB);
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(GameObject.class)
            .withDeserializedForm(gameObject)
            .withSerializedResource("/item/item.json")
            .verify();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(GameObject.class)
            .withMockedTransientFields()
            .excludeTransientFields()
            .verify();
    }
}