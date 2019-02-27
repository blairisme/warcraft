/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.action.ActionException;
import com.evilbird.engine.item.Item;
import com.evilbird.test.data.action.TestBasicAction;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import com.evilbird.warcraft.action.attack.AttackActions;
import com.evilbird.warcraft.action.move.MoveActions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

/**
 * Instances of this unit test validate the {@link SequenceAction} class.
 *
 * @author Blair Butterworth
 */
public class SequenceActionTest
{
    private TestBasicAction childA;
    private TestBasicAction childB;
    private TestBasicAction childC;
    private SequenceAction sequence;

    @Before
    public void setup() {
        childA = new TestBasicAction();
        childA.setIdentifier(MoveActions.MoveToItem);

        childB = new TestBasicAction();
        childB.setIdentifier(MoveActions.MoveCancel);

        childC = new TestBasicAction();
        childC.setIdentifier(AttackActions.AttackMelee);

        sequence = new SequenceAction(childA, childB, childC);
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(SequenceAction.class)
            .withDeserializedForm(sequence)
            .withSerializedResource("/action/framework/sequenceaction.json")
            .verify();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(SequenceAction.class)
            .withMockedTransientFields(Item.class)
            .excludeTransientFields()
            .verify();
    }

    @Test
    public void actTest() {
        Assert.assertFalse(sequence.act(1));
        Assert.assertTrue(childA.getInvoked());
        Assert.assertFalse(childB.getInvoked());
        Assert.assertFalse(childC.getInvoked());

        Assert.assertFalse(sequence.act(1));
        Assert.assertTrue(childA.getInvoked());
        Assert.assertTrue(childB.getInvoked());
        Assert.assertFalse(childC.getInvoked());

        Assert.assertTrue(sequence.act(1));
        Assert.assertTrue(childA.getInvoked());
        Assert.assertTrue(childB.getInvoked());
        Assert.assertTrue(childC.getInvoked());
    }

    @Test
    public void itemTest() {
        Assert.assertNull(sequence.getItem());
        Assert.assertNull(childA.getItem());
        Assert.assertNull(childB.getItem());
        Assert.assertNull(childC.getItem());

        Item actor = Mockito.mock(Item.class);
        sequence.setItem(actor);

        Assert.assertEquals(actor, sequence.getItem());
        Assert.assertEquals(actor, childA.getItem());
        Assert.assertEquals(actor, childB.getItem());
        Assert.assertEquals(actor, childC.getItem());
    }

    @Test
    public void errorTest() {
        Assert.assertNull(sequence.getError());
        Assert.assertFalse(sequence.hasError());

        ActionException error = new ActionException("foo");
        childB.setError(error);

        Assert.assertEquals(error, sequence.getError());
        Assert.assertEquals(error, childB.getError());
        Assert.assertNull(childA.getError());
        Assert.assertNull(childC.getError());
    }
}