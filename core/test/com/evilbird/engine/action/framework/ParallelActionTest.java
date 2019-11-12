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
import com.evilbird.engine.object.GameObject;
import com.evilbird.test.data.action.TestBasicAction;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.warcraft.action.attack.AttackActions;
import com.evilbird.warcraft.action.move.MoveActions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;

/**
 * Instances of this unit test validate the {@link ParallelAction} class.
 *
 * @author Blair Butterworth
 */
public class ParallelActionTest
{
    private TestBasicAction childA;
    private TestBasicAction childB;
    private TestBasicAction childC;
    private ParallelAction parallel;

    @Before
    public void setup() {
        childA = new TestBasicAction();
        childA.setIdentifier(MoveActions.MoveToItem);

        childB = new TestBasicAction();
        childB.setIdentifier(MoveActions.MoveCancel);

        childC = new TestBasicAction();
        childC.setIdentifier(AttackActions.Attack);

        parallel = new ParallelAction(childA, childB, childC);
    }

//    @Test
//    public void serializeTest() throws IOException {
//        SerializationVerifier.forClass(ParallelAction.class)
//                .withDeserializedForm(parallel)
//                .withSerializedResource("/action/framework/parallelaction.json")
//                .verify();
//    }
//
//    @Test
//    public void serializeAfterActTest() throws IOException {
//        parallel.act(1);
//        SerializationVerifier.forClass(ParallelAction.class)
//                .withDeserializedForm(parallel)
//                .withSerializedResource("/action/framework/parallelactioncomplete.json")
//                .verify();
//    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(ParallelAction.class)
                .withMockedTransientFields(GameObject.class)
                .excludeTransientFields()
                .verify();
    }

    @Test
    public void actTest() {
        Assert.assertFalse(childA.getInvoked());
        Assert.assertFalse(childB.getInvoked());
        Assert.assertFalse(childC.getInvoked());

        assertTrue(parallel.act(1));

        Assert.assertTrue(childA.getInvoked());
        Assert.assertTrue(childB.getInvoked());
        Assert.assertTrue(childC.getInvoked());
    }

    @Test
    public void itemTest() {
        Assert.assertNull(parallel.getSubject());
        Assert.assertNull(childA.getSubject());
        Assert.assertNull(childB.getSubject());
        Assert.assertNull(childC.getSubject());

        GameObject actor = Mockito.mock(GameObject.class);
        parallel.setItem(actor);

        Assert.assertEquals(actor, parallel.getSubject());
        Assert.assertEquals(actor, childA.getSubject());
        Assert.assertEquals(actor, childB.getSubject());
        Assert.assertEquals(actor, childC.getSubject());
    }

    @Test
    public void errorTest() {
        Assert.assertNull(parallel.getError());
        Assert.assertFalse(parallel.hasError());

        ActionException error = new ActionException("foo");
        childB.setError(error);

        Assert.assertEquals(error, parallel.getError());
        Assert.assertEquals(error, childB.getError());
        Assert.assertNull(childA.getError());
        Assert.assertNull(childC.getError());
    }
}
