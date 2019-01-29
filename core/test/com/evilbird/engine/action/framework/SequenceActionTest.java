/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.evilbird.engine.test.MockBasicAction;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SequenceActionTest
{
    @Test
    public void actTest() {
        BasicAction childA = newMockAction();
        BasicAction childB = newMockAction();
        BasicAction childC = newMockAction();
        SequenceAction sequence = new SequenceAction(childA, childB, childC);

        assertFalse(sequence.act(1));
        assertFalse(sequence.act(1));
        assertTrue(sequence.act(1));

        Mockito.verify(childA, Mockito.times(1)).act(1);
        Mockito.verify(childB, Mockito.times(1)).act(1);
        Mockito.verify(childC, Mockito.times(1)).act(1);
    }

    @Test
    public void actorTest() {
        BasicAction childA = new MockBasicAction();
        BasicAction childB = new MockBasicAction();
        BasicAction childC = new MockBasicAction();
        SequenceAction sequence = new SequenceAction(childA, childB, childC);

        Assert.assertNull(sequence.getActor());
        Assert.assertNull(childA.getActor());
        Assert.assertNull(childB.getActor());
        Assert.assertNull(childC.getActor());

        Actor actor = Mockito.mock(Actor.class);
        sequence.setActor(actor);

        Assert.assertEquals(actor, sequence.getActor());
        Assert.assertEquals(actor, childA.getActor());
        Assert.assertEquals(actor, childB.getActor());
        Assert.assertEquals(actor, childC.getActor());
    }

    @Test
    public void errorTest() {
        BasicAction childA = new MockBasicAction();
        BasicAction childB = new MockBasicAction();
        BasicAction childC = new MockBasicAction();
        SequenceAction sequence = new SequenceAction(childA, childB, childC);

        Assert.assertNull(sequence.getError());
        Assert.assertFalse(sequence.hasError());

        Throwable error = new UnknownError();
        childB.setError(error);

        Assert.assertEquals(error, sequence.getError());
        Assert.assertEquals(error, childB.getError());
        Assert.assertNull(childA.getError());
        Assert.assertNull(childC.getError());
    }

    private BasicAction newMockAction() {
        BasicAction result = Mockito.mock(BasicAction.class);
        Mockito.when(result.act(1)).thenReturn(true);
        return result;
    }
}