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
import com.evilbird.test.data.action.TestBasicAction;
import com.evilbird.test.data.lang.TestPredicate;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Instances of this unit test validate the {@link RepeatedAction} class.
 *
 * @author Blair Butterworth
 */
public class RepeatedActionTest
{
    private TestBasicAction delegate;
    private TestPredicate predicate;
    private RepeatedAction action;

    @Before
    public void setup() {
        delegate = new TestBasicAction();
        predicate = new TestPredicate();
        action = new RepeatedAction(delegate, predicate);
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(RepeatedAction.class)
                .withDeserializedForm(action)
                .withSerializedResource("/repeatedaction.json")
                .verify();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(RepeatedAction.class)
                .withMockedTransientFields(Item.class)
                .excludeTransientFields()
                .verify();
    }

    @Test
    public void repeatIncompleteTest() {
        delegate.setComplete(false);
        predicate.setValue(true);

        Assert.assertFalse(action.act(1));
        Assert.assertTrue(delegate.getInvoked());
        Assert.assertFalse(delegate.getRestarted());

        delegate.resetState();

        Assert.assertFalse(action.act(1));
        Assert.assertTrue(delegate.getInvoked());
        Assert.assertFalse(delegate.getRestarted());
    }

    @Test
    public void repeatCompleteTest() {
        delegate.setComplete(true);
        predicate.setValue(true);

        Assert.assertFalse(action.act(1));
        Assert.assertTrue(delegate.getInvoked());
        Assert.assertTrue(delegate.getRestarted());
    }

    @Test
    public void noRepeatIncompleteTest() {
        delegate.setComplete(true);
        predicate.setValue(false);

        Assert.assertTrue(action.act(1));
        Assert.assertTrue(delegate.getInvoked());
        Assert.assertFalse(delegate.getRestarted());
    }

    @Test
    public void noRepeatCompleteTest() {
        delegate.setComplete(false);
        predicate.setValue(false);

        Assert.assertFalse(action.act(1));
        Assert.assertTrue(delegate.getInvoked());
        Assert.assertFalse(delegate.getRestarted());
    }
}
