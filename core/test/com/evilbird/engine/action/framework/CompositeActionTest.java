/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.item.Item;
import com.evilbird.engine.test.MockBasicAction;
import com.evilbird.engine.test.MockCompositeAction;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class CompositeActionTest
{
    @Test
    public void actorTest() {
        Action childA = new MockBasicAction();
        Action childB = new MockBasicAction();
        Action childC = new MockBasicAction();
        MockCompositeAction composite = new MockCompositeAction(childA, childB, childC);

        Assert.assertNull(composite.getItem());
        Assert.assertNull(childA.getItem());
        Assert.assertNull(childB.getItem());
        Assert.assertNull(childC.getItem());

        Item actor = Mockito.mock(Item.class);
        composite.setItem(actor);

        Assert.assertEquals(actor, composite.getItem());
        Assert.assertEquals(actor, childA.getItem());
        Assert.assertEquals(actor, childB.getItem());
        Assert.assertEquals(actor, childC.getItem());
    }

    @Test
    public void errorTest() {
        Action childA = new MockBasicAction();
        Action childB = new MockBasicAction();
        Action childC = new MockBasicAction();
        MockCompositeAction composite = new MockCompositeAction(childA, childB, childC);

        Assert.assertNull(composite.getError());
        Assert.assertFalse(composite.hasError());

        Throwable error = new UnknownError();
        childB.setError(error);

        Assert.assertEquals(error, composite.getError());
        Assert.assertEquals(error, childB.getError());
        Assert.assertNull(childA.getError());
        Assert.assertNull(childC.getError());
    }
}