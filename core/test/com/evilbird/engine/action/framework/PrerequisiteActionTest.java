/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.action.framework;

import com.badlogic.gdx.utils.Pool;
import com.evilbird.engine.action.ActionException;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.object.GameObject;
import com.evilbird.test.data.action.TestBasicAction;
import com.evilbird.test.data.lang.TestPredicate;
import com.evilbird.test.verifier.EqualityVerifier;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

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

//    @Test
//    public void serializeTest() throws IOException {
//        SerializationVerifier.forClass(PrerequisiteAction.class)
//                .withDeserializedForm(action)
//                .withSerializedResource("/action/framework/prerequisiteaction.json")
//                .verify();
//    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(PrerequisiteAction.class)
            .withMockedTransientFields(GameObject.class)
            .withMockedType(Pool.class)
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

//    @Test
//    @Ignore
//    public void actPrerequisiteErrorTest() {
//        ActionException error = new ActionException("An error occurred");
//        prerequisite.setError(error);
//        predicate.setValue(false);
//
//        Assert.assertFalse(action.act(1));
//        Assert.assertFalse(primary.getInvoked());
//        Assert.assertTrue(prerequisite.getInvoked());
//
//        Assert.assertEquals(error, action.getError());
//    }

    @Test
    public void actorTest() {
        Assert.assertNull(primary.getSubject());
        Assert.assertNull(prerequisite.getSubject());
        Assert.assertNull(action.getSubject());

        GameObject actor = Mockito.mock(GameObject.class);
        action.setSubject(actor);

        Assert.assertEquals(actor, primary.getSubject());
        Assert.assertEquals(actor, prerequisite.getSubject());
        Assert.assertEquals(actor, action.getSubject());
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