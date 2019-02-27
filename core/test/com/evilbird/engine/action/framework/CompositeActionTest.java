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
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import com.evilbird.warcraft.action.attack.AttackActions;
import com.evilbird.warcraft.action.move.MoveActions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

/**
 * Instances of this unit test validate the {@link CompositeAction} class.
 *
 * @author Blair Butterworth
 */
public class CompositeActionTest
{
    private Action childA;
    private Action childB;
    private Action childC;
    private CompositeAction composite;

    @Before
    public void setup() {
        childA = new TestBasicAction();
        childA.setIdentifier(MoveActions.MoveToItem);

        childB = new TestBasicAction();
        childB.setIdentifier(MoveActions.MoveCancel);

        childC = new TestBasicAction();
        childC.setIdentifier(AttackActions.AttackCancel);

        composite = new TestCompositeAction(childA, childB, childC);
        composite.setIdentifier(AttackActions.AttackMelee);
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(TestCompositeAction.class)
            .withDeserializedForm(composite)
            .withSerializedResource("/action/framework/compositeaction.json")
            .verify();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(TestCompositeAction.class)
            .withMockedTransientFields(Item.class)
            .excludeTransientFields()
            .verify();
    }

    @Test
    public void itemTest() {
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