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

import static org.junit.Assert.assertTrue;

/**
 * Instances of this unit test validate the {@link ParallelAction} class.
 *
 * @author Blair Butterworth
 */
public class ParallelActionTest
{
    @Test
    public void actTest() {
        Action childA = newMockAction();
        Action childB = newMockAction();
        Action childC = newMockAction();
        ParallelAction parallel = new ParallelAction(childA, childB, childC);

        assertTrue(parallel.act(1));

        Mockito.verify(childA, Mockito.times(1)).act(1);
        Mockito.verify(childB, Mockito.times(1)).act(1);
        Mockito.verify(childC, Mockito.times(1)).act(1);
    }

    @Test
    public void actorTest() {
        Action childA = new TestBasicAction();
        Action childB = new TestBasicAction();
        Action childC = new TestBasicAction();
        ParallelAction parallel = new ParallelAction(childA, childB, childC);

        Assert.assertNull(parallel.getItem());
        Assert.assertNull(childA.getItem());
        Assert.assertNull(childB.getItem());
        Assert.assertNull(childC.getItem());

        Item actor = Mockito.mock(Item.class);
        parallel.setItem(actor);

        Assert.assertEquals(actor, parallel.getItem());
        Assert.assertEquals(actor, childA.getItem());
        Assert.assertEquals(actor, childB.getItem());
        Assert.assertEquals(actor, childC.getItem());
    }

    @Test
    public void errorTest() {
        Action childA = new TestBasicAction();
        Action childB = new TestBasicAction();
        Action childC = new TestBasicAction();
        ParallelAction parallel = new ParallelAction(childA, childB, childC);

        Assert.assertNull(parallel.getError());
        Assert.assertFalse(parallel.hasError());

        ActionException error = new ActionException("foo");
        childB.setError(error);

        Assert.assertEquals(error, parallel.getError());
        Assert.assertEquals(error, childB.getError());
        Assert.assertNull(childA.getError());
        Assert.assertNull(childC.getError());
    }

    private Action newMockAction() {
        Action result = Mockito.mock(Action.class);
        Mockito.when(result.act(1)).thenReturn(true);
        return result;
    }
}
