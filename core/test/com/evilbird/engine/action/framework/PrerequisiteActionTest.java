/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.action.ActionException;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.item.Item;
import com.evilbird.test.data.action.TestBasicAction;
import com.evilbird.test.data.lang.TestPredicate;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Instances of this unit test validate the {@link PrerequisiteAction} class.
 *
 * @author Blair Butterworth
 */
@SuppressWarnings("unchecked")
public class PrerequisiteActionTest
{
    private TestBasicAction primary;
    private TestBasicAction prerequisite;
    private TestPredicate predicate;
    private PrerequisiteAction action;

    @Before
    public void setup() {
        primary = new TestBasicAction();
        primary.setIdentifier(new TextIdentifier("primary"));

        prerequisite = new TestBasicAction();
        prerequisite.setIdentifier(new TextIdentifier("prerequisite"));

        predicate = new TestPredicate();

        action = new PrerequisiteAction(primary, prerequisite, predicate);
        action.setIdentifier(new TextIdentifier("PrerequisiteActionTest"));
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(PrerequisiteAction.class)
                .withDeserializedForm(action)
                .withSerializedResource("/prerequisiteaction.json")
                .verify();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(PrerequisiteAction.class)
                .withMockedTransientFields(Item.class)
                .excludeTransientFields()
                .verify();
    }

    @Test
    public void actTest() {
        predicate.setValue(true);
        Assert.assertTrue(action.act(1));
        Assert.assertTrue(primary.getInvoked());
        Assert.assertFalse(prerequisite.getInvoked());
    }

    @Test
    public void actPredicateFailTest() {
        predicate.setValue(false);
        Assert.assertFalse(action.act(1));
        Assert.assertFalse(primary.getInvoked());
        Assert.assertTrue(prerequisite.getInvoked());

        primary.resetState();
        prerequisite.resetState();

        predicate.setValue(true);
        Assert.assertTrue(action.act(1));
        Assert.assertTrue(primary.getInvoked());
        Assert.assertFalse(prerequisite.getInvoked());
    }

    @Test
    public void actPrerequisiteErrorTest() {
        ActionException error = new ActionException("An error occurred");
        prerequisite.setError(error);
        predicate.setValue(false);

        Assert.assertFalse(action.act(1));
        Assert.assertFalse(primary.getInvoked());
        Assert.assertTrue(prerequisite.getInvoked());

        //Assert.assertEquals(error, action.getError()); //todo
    }

    @Test
    public void actorTest() {
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
        Assert.assertNull(action.getError());
        Assert.assertFalse(action.hasError());

        ActionException error = new ActionException("foo");
        prerequisite.setError(error);

        Assert.assertEquals(error, action.getError());
        Assert.assertEquals(error, prerequisite.getError());
        Assert.assertNull(primary.getError());
    }
}