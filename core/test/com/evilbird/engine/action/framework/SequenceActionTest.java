/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.action.framework;

import com.badlogic.gdx.utils.Pool;
import com.evilbird.engine.object.GameObject;
import com.evilbird.test.data.action.TestBasicAction;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.warcraft.action.attack.AttackActions;
import com.evilbird.warcraft.action.move.MoveActions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Instances of this unit test validate the {@link SequenceAction} class.
 *
 * @author Blair Butterworth
 */
public class SequenceActionTest
{
    private TestBasicAction childA;
    private TestBasicAction childB;
    private TestBasicAction childC;
    private SequenceAction sequence;

    @Before
    public void setup() {
        childA = new TestBasicAction();
        childA.setIdentifier(MoveActions.MoveToItem);

        childB = new TestBasicAction();
        childB.setIdentifier(MoveActions.MoveCancel);

        childC = new TestBasicAction();
        childC.setIdentifier(AttackActions.Attack);

        sequence = new SequenceAction(childA, childB, childC);
    }

//    @Test
//    public void serializeTest() throws IOException {
//        SerializationVerifier.forClass(SequenceAction.class)
//            .withDeserializedForm(sequence)
//            .withSerializedResource("/action/framework/sequenceaction.json")
//            .verify();
//    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(SequenceAction.class)
            .withMockedTransientFields(GameObject.class)
            .withMockedType(Pool.class)
            .excludeTransientFields()
            .verify();
    }

    @Test
    public void actTest() {
        Assert.assertFalse(sequence.run(1));
        Assert.assertTrue(childA.getInvoked());
        Assert.assertFalse(childB.getInvoked());
        Assert.assertFalse(childC.getInvoked());

        Assert.assertFalse(sequence.run(1));
        Assert.assertTrue(childA.getInvoked());
        Assert.assertTrue(childB.getInvoked());
        Assert.assertFalse(childC.getInvoked());

        Assert.assertTrue(sequence.run(1));
        Assert.assertTrue(childA.getInvoked());
        Assert.assertTrue(childB.getInvoked());
        Assert.assertTrue(childC.getInvoked());
    }

    @Test
    public void itemTest() {
        Assert.assertNull(sequence.getSubject());
        Assert.assertNull(childA.getSubject());
        Assert.assertNull(childB.getSubject());
        Assert.assertNull(childC.getSubject());

        GameObject actor = Mockito.mock(GameObject.class);
        sequence.setSubject(actor);

        Assert.assertEquals(actor, sequence.getSubject());
        Assert.assertEquals(actor, childA.getSubject());
        Assert.assertEquals(actor, childB.getSubject());
        Assert.assertEquals(actor, childC.getSubject());
    }
}