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
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.item.Item;
import com.evilbird.test.data.action.TestBasicAction;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SuppressWarnings("unchecked")
public class PrerequisiteActionTest
{
    @Test
    public void actTest() {
        Action primary = newAction();
        Action prerequisite = newAction();
        Predicate predicate = newPredicate();
        PrerequisiteAction action = new PrerequisiteAction(primary, prerequisite, predicate);

        assertTrue(action.act(1));
        verify(primary, times(1)).act(1);
        verify(prerequisite, times(0)).act(1);
    }

    @Test
    public void actPredicateFailTest() {
        Action primary = newAction();
        Action prerequisite = newAction();
        Predicate predicate = newPredicate(false);
        PrerequisiteAction action = new PrerequisiteAction(primary, prerequisite, predicate);

        assertFalse(action.act(1));
        verify(primary, never()).act(1);
        verify(prerequisite, times(1)).act(1);

        Mockito.when(predicate.test(Mockito.any())).thenReturn(true);

        assertTrue(action.act(1));
        verify(primary, times(1)).act(1);
        verify(prerequisite, times(1)).act(1);
    }

    @Test
    public void actPrerequisiteErrorTest() {
        ActionException error = new ActionException("An error occurred");
        Action primary = newAction();
        Action prerequisite = newAction(error);
        Predicate predicate = newPredicate(false);
        PrerequisiteAction action = new PrerequisiteAction(primary, prerequisite, predicate);

        assertTrue(action.act(1));
        verify(primary, never()).act(1);
        verify(prerequisite, times(1)).act(1);
        assertEquals(error, action.getError());
    }

    @Test
    public void actorTest() {
        Action primary = new TestBasicAction();
        Action prerequisite = new TestBasicAction();
        Predicate predicate = newPredicate();
        PrerequisiteAction action = new PrerequisiteAction(primary, prerequisite, predicate);

        Assert.assertNull(primary.getItem());
        Assert.assertNull(prerequisite.getItem());
        Assert.assertNull(action.getItem());

        Item actor = Mockito.mock(Item.class);
        action.setItem(actor);

        Assert.assertEquals(actor, primary.getItem());
        Assert.assertEquals(actor, prerequisite.getItem());
        Assert.assertEquals(actor, action.getItem());
    }

    @Test
    public void errorTest() {
        Action primary = new TestBasicAction();
        Action prerequisite = new TestBasicAction();
        Predicate predicate = newPredicate();
        PrerequisiteAction action = new PrerequisiteAction(primary, prerequisite, predicate);

        Assert.assertNull(action.getError());
        Assert.assertFalse(action.hasError());

        ActionException error = new ActionException("foo");
        prerequisite.setError(error);

        Assert.assertEquals(error, action.getError());
        Assert.assertEquals(error, prerequisite.getError());
        Assert.assertNull(primary.getError());
    }

    private Action newAction() {
        return newAction(null);
    }

    private Action newAction(ActionException error) {
        Action result = mock(Action.class);
        Mockito.when(result.act(1)).thenReturn(true);
        Mockito.when(result.getError()).thenReturn(error);
        Mockito.when(result.hasError()).thenReturn(error != null);
        return result;
    }

    private Predicate<Action> newPredicate() {
        return newPredicate(true);
    }

    private Predicate<Action> newPredicate(boolean success) {
        Predicate predicate = Mockito.mock(Predicate.class);
        Mockito.when(predicate.test(Mockito.any())).thenReturn(success);
        return predicate;
    }
}