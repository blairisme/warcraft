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
import com.evilbird.engine.common.lang.GenericIdentifier;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.test.data.action.TestBasicAction;
import com.evilbird.test.data.action.TestDelegateAction;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class DelegateActionTest
{
    @Test
    public void itemTest() {
        com.evilbird.engine.action.Action underlyingAction = new TestBasicAction();
        DelegateAction delegateAction = new TestDelegateAction(underlyingAction);

        Assert.assertNull(delegateAction.getItem());
        Item actor = Mockito.mock(Item.class);

        delegateAction.setItem(actor);
        Assert.assertEquals(actor, underlyingAction.getItem());
        Assert.assertEquals(actor, delegateAction.getItem());
    }

    @Test
    public void targetTest() {
        com.evilbird.engine.action.Action underlyingAction = new TestBasicAction();
        DelegateAction delegateAction = new TestDelegateAction(underlyingAction);

        Assert.assertNull(delegateAction.getTarget());
        Item target = Mockito.mock(Item.class);

        delegateAction.setTarget(target);
        Assert.assertEquals(target, underlyingAction.getTarget());
        Assert.assertEquals(target, delegateAction.getTarget());
    }
    
    @Test
    public void identifierTest() {
        com.evilbird.engine.action.Action underlyingAction = new TestBasicAction();
        DelegateAction delegateAction = new TestDelegateAction(underlyingAction);

        Assert.assertEquals(GenericIdentifier.Unknown, delegateAction.getIdentifier());
        Identifier identifier = Mockito.mock(Identifier.class);

        delegateAction.setIdentifier(identifier);
        Assert.assertEquals(identifier, underlyingAction.getIdentifier());
        Assert.assertNotEquals(identifier, delegateAction.getIdentifier());
    }
    
    @Test
    public void errorTest() {
        Action underlyingAction = new TestBasicAction();
        DelegateAction delegateAction = new TestDelegateAction(underlyingAction);

        Assert.assertNull(delegateAction.getError());
        Assert.assertFalse(delegateAction.hasError());

        ActionException error = new ActionException("foo");
        underlyingAction.setError(error);

        Assert.assertEquals(error, delegateAction.getError());
        Assert.assertTrue(delegateAction.hasError());
    }
}