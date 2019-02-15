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
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.test.MockBasicAction;
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
        com.evilbird.engine.action.Action primary = newAction();
        com.evilbird.engine.action.Action prerequisite = newAction();
        Predicate predicate = newPredicate();
        PrerequisiteAction action = new PrerequisiteAction(primary, prerequisite, predicate);

        assertTrue(action.act(1));
        verify(primary, times(1)).act(1);
        verify(prerequisite, times(0)).act(1);
    }

    @Test
    public void actPredicateFailTest() {
        com.evilbird.engine.action.Action primary = newAction();
        com.evilbird.engine.action.Action prerequisite = newAction();
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
        Throwable error = new UnknownError();
        com.evilbird.engine.action.Action primary = newAction();
        com.evilbird.engine.action.Action prerequisite = newAction(error);
        Predicate predicate = newPredicate(false);
        PrerequisiteAction action = new PrerequisiteAction(primary, prerequisite, predicate);

        assertTrue(action.act(1));
        verify(primary, never()).act(1);
        verify(prerequisite, times(1)).act(1);
        assertEquals(error, action.getError());
    }

    @Test
    public void actorTest() {
        com.evilbird.engine.action.Action primary = new MockBasicAction();
        com.evilbird.engine.action.Action prerequisite = new MockBasicAction();
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
        com.evilbird.engine.action.Action primary = new MockBasicAction();
        com.evilbird.engine.action.Action prerequisite = new MockBasicAction();
        Predicate predicate = newPredicate();
        PrerequisiteAction action = new PrerequisiteAction(primary, prerequisite, predicate);

        Assert.assertNull(action.getError());
        Assert.assertFalse(action.hasError());

        Throwable error = new UnknownError();
        prerequisite.setError(error);

        Assert.assertEquals(error, action.getError());
        Assert.assertEquals(error, prerequisite.getError());
        Assert.assertNull(primary.getError());
    }

    private com.evilbird.engine.action.Action newAction() {
        return newAction(null);
    }

    private com.evilbird.engine.action.Action newAction(Throwable error) {
        com.evilbird.engine.action.Action result = mock(com.evilbird.engine.action.Action.class);
        Mockito.when(result.act(1)).thenReturn(true);
        Mockito.when(result.getError()).thenReturn(error);
        Mockito.when(result.hasError()).thenReturn(error != null);
        return result;
    }

    private Predicate<com.evilbird.engine.action.Action> newPredicate() {
        return newPredicate(true);
    }

    private Predicate<Action> newPredicate(boolean success) {
        Predicate predicate = Mockito.mock(Predicate.class);
        Mockito.when(predicate.test(Mockito.any())).thenReturn(success);
        return predicate;
    }
}