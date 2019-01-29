/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.evilbird.engine.common.function.Predicate;
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
        BasicAction primary = newAction();
        BasicAction prerequisite = newAction();
        Predicate predicate = newPredicate();
        PrerequisiteAction action = new PrerequisiteAction(primary, prerequisite, predicate);

        assertTrue(action.act(1));
        verify(primary, times(1)).act(1);
        verify(prerequisite, times(0)).act(1);
    }

    @Test
    public void actPredicateFailTest() {
        BasicAction primary = newAction();
        BasicAction prerequisite = newAction();
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
        BasicAction primary = newAction();
        BasicAction prerequisite = newAction(error);
        Predicate predicate = newPredicate(false);
        PrerequisiteAction action = new PrerequisiteAction(primary, prerequisite, predicate);

        assertTrue(action.act(1));
        verify(primary, never()).act(1);
        verify(prerequisite, times(1)).act(1);
        assertEquals(error, action.getError());
    }

    @Test
    public void actorTest() {
        BasicAction primary = new MockBasicAction();
        BasicAction prerequisite = new MockBasicAction();
        Predicate predicate = newPredicate();
        PrerequisiteAction action = new PrerequisiteAction(primary, prerequisite, predicate);

        Assert.assertNull(primary.getActor());
        Assert.assertNull(prerequisite.getActor());
        Assert.assertNull(action.getActor());

        Actor actor = Mockito.mock(Actor.class);
        action.setActor(actor);

        Assert.assertEquals(actor, primary.getActor());
        Assert.assertEquals(actor, prerequisite.getActor());
        Assert.assertEquals(actor, action.getActor());
    }

    @Test
    public void errorTest() {
        BasicAction primary = new MockBasicAction();
        BasicAction prerequisite = new MockBasicAction();
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

    private BasicAction newAction() {
        return newAction(null);
    }

    private BasicAction newAction(Throwable error) {
        BasicAction result = mock(BasicAction.class);
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