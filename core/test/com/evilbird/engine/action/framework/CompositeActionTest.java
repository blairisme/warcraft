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
import com.evilbird.test.data.action.TestCompositeAction;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class CompositeActionTest
{
    @Test
    public void itemTest() {
        Action childA = new TestBasicAction();
        Action childB = new TestBasicAction();
        Action childC = new TestBasicAction();
        TestCompositeAction composite = new TestCompositeAction(childA, childB, childC);

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
        Action childA = new TestBasicAction();
        Action childB = new TestBasicAction();
        Action childC = new TestBasicAction();
        TestCompositeAction composite = new TestCompositeAction(childA, childB, childC);

        Assert.assertNull(composite.getError());
        Assert.assertFalse(composite.hasError());

        ActionException error = new ActionException("foo");
        childB.setError(error);

        Assert.assertEquals(error, composite.getError());
        Assert.assertEquals(error, childB.getError());
        Assert.assertNull(childA.getError());
        Assert.assertNull(childC.getError());
    }
}