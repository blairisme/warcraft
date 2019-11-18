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
import com.evilbird.engine.object.GameObject;
import com.evilbird.test.data.action.TestBasicAction;
import com.evilbird.test.verifier.EqualityVerifier;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Instances of this unit test validate the {@link DelegateAction} class.
 *
 * @author Blair Butterworth
 */
public class DelegateActionTest
{
    private Action underlyingAction;
    private DelegateAction delegateAction;

    @Before
    public void setup() {
        underlyingAction = new TestBasicAction();
        delegateAction = new DelegateAction(underlyingAction);
    }

//    @Test
//    public void serializeTest() throws IOException {
//        SerializationVerifier.forClass(DelegateAction.class)
//            .withDeserializedForm(delegateAction)
//            .withSerializedResource("/action/framework/delegateaction.json")
//            .verify();
//    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(DelegateAction.class)
            .withMockedTransientFields(GameObject.class)
            .excludeTransientFields()
            .verify();
    }

    @Test
    public void itemTest() {
        Assert.assertNull(delegateAction.getSubject());
        GameObject actor = Mockito.mock(GameObject.class);

        delegateAction.setSubject(actor);
        Assert.assertEquals(actor, underlyingAction.getSubject());
        Assert.assertEquals(actor, delegateAction.getSubject());
    }

    @Test
    public void targetTest() {
        Assert.assertNull(delegateAction.getTarget());
        GameObject target = Mockito.mock(GameObject.class);

        delegateAction.setTarget(target);
        Assert.assertEquals(target, underlyingAction.getTarget());
        Assert.assertEquals(target, delegateAction.getTarget());
    }
    
    @Test
    public void identifierTest() {
        Assert.assertEquals(GenericIdentifier.Unknown, delegateAction.getIdentifier());
        Identifier identifier = Mockito.mock(Identifier.class);

        delegateAction.setIdentifier(identifier);
        Assert.assertEquals(identifier, underlyingAction.getIdentifier());
        Assert.assertEquals(identifier, delegateAction.getIdentifier());
    }
    
    @Test
    public void errorTest() {
        Assert.assertNull(delegateAction.getError());
        Assert.assertFalse(delegateAction.hasError());

        ActionException error = new ActionException("foo");
        underlyingAction.setError(error);

        Assert.assertEquals(error, delegateAction.getError());
        Assert.assertTrue(delegateAction.hasError());
    }
}