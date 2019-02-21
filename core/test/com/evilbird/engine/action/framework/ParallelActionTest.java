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
import com.evilbird.engine.item.Item;
import com.evilbird.test.mock.action.MockBasicAction;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;

public class ParallelActionTest
{
    @Test
    public void actTest() {
        com.evilbird.engine.action.Action childA = newMockAction();
        com.evilbird.engine.action.Action childB = newMockAction();
        com.evilbird.engine.action.Action childC = newMockAction();
        ParallelAction parallel = new ParallelAction(childA, childB, childC);

        assertTrue(parallel.act(1));

        Mockito.verify(childA, Mockito.times(1)).act(1);
        Mockito.verify(childB, Mockito.times(1)).act(1);
        Mockito.verify(childC, Mockito.times(1)).act(1);
    }

    @Test
    public void actorTest() {
        com.evilbird.engine.action.Action childA = new MockBasicAction();
        com.evilbird.engine.action.Action childB = new MockBasicAction();
        com.evilbird.engine.action.Action childC = new MockBasicAction();
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
        com.evilbird.engine.action.Action childA = new MockBasicAction();
        com.evilbird.engine.action.Action childB = new MockBasicAction();
        com.evilbird.engine.action.Action childC = new MockBasicAction();
        ParallelAction parallel = new ParallelAction(childA, childB, childC);

        Assert.assertNull(parallel.getError());
        Assert.assertFalse(parallel.hasError());

        Throwable error = new UnknownError();
        childB.setError(error);

        Assert.assertEquals(error, parallel.getError());
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
