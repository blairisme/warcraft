/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionException;
import com.evilbird.engine.item.Item;
import com.evilbird.test.data.action.TestBasicAction;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SequenceActionTest
{
    @Test
    public void actTest() {
        com.evilbird.engine.action.Action childA = newMockAction();
        com.evilbird.engine.action.Action childB = newMockAction();
        com.evilbird.engine.action.Action childC = newMockAction();
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
        com.evilbird.engine.action.Action childA = new TestBasicAction();
        com.evilbird.engine.action.Action childB = new TestBasicAction();
        com.evilbird.engine.action.Action childC = new TestBasicAction();
        SequenceAction sequence = new SequenceAction(childA, childB, childC);

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
        com.evilbird.engine.action.Action childA = new TestBasicAction();
        com.evilbird.engine.action.Action childB = new TestBasicAction();
        com.evilbird.engine.action.Action childC = new TestBasicAction();
        SequenceAction sequence = new SequenceAction(childA, childB, childC);

        Assert.assertNull(sequence.getError());
        Assert.assertFalse(sequence.hasError());

        ActionException error = new ActionException("foo");
        childB.setError(error);

        Assert.assertEquals(error, sequence.getError());
        Assert.assertEquals(error, childB.getError());
        Assert.assertNull(childA.getError());
        Assert.assertNull(childC.getError());
    }

    private com.evilbird.engine.action.Action newMockAction() {
        com.evilbird.engine.action.Action result = Mockito.mock(Action.class);
        Mockito.when(result.act(1)).thenReturn(true);
        return result;
    }
}