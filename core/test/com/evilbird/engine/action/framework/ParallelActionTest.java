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

import static org.junit.Assert.assertTrue;

/**
 * Instances of this unit test validate the {@link ParallelAction} class.
 *
 * @author Blair Butterworth
 */
public class ParallelActionTest
{
    private TestBasicAction childA;
    private TestBasicAction childB;
    private TestBasicAction childC;
    private ParallelAction parallel;

    @Before
    public void setup() {
        childA = new TestBasicAction();
        childA.setIdentifier(MoveActions.MoveToItem);

        childB = new TestBasicAction();
        childB.setIdentifier(MoveActions.MoveCancel);

        childC = new TestBasicAction();
        childC.setIdentifier(AttackActions.Attack);

        parallel = new ParallelAction(childA, childB, childC);
    }

//    @Test
//    public void serializeTest() throws IOException {
//        SerializationVerifier.forClass(ParallelAction.class)
//                .withDeserializedForm(parallel)
//                .withSerializedResource("/action/framework/parallelaction.json")
//                .verify();
//    }
//
//    @Test
//    public void serializeAfterActTest() throws IOException {
//        parallel.act(1);
//        SerializationVerifier.forClass(ParallelAction.class)
//                .withDeserializedForm(parallel)
//                .withSerializedResource("/action/framework/parallelactioncomplete.json")
//                .verify();
//    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(ParallelAction.class)
            .withMockedTransientFields(GameObject.class)
            .excludeTransientFields()
            .withMockedType(Pool.class)
            .verify();
    }

    @Test
    public void runTest() {
        Assert.assertFalse(childA.getInvoked());
        Assert.assertFalse(childB.getInvoked());
        Assert.assertFalse(childC.getInvoked());

        assertTrue(parallel.run(1));

        Assert.assertTrue(childA.getInvoked());
        Assert.assertTrue(childB.getInvoked());
        Assert.assertTrue(childC.getInvoked());
    }

    @Test
    public void itemTest() {
        Assert.assertNull(parallel.getSubject());
        Assert.assertNull(childA.getSubject());
        Assert.assertNull(childB.getSubject());
        Assert.assertNull(childC.getSubject());

        GameObject actor = Mockito.mock(GameObject.class);
        parallel.setSubject(actor);

        Assert.assertEquals(actor, parallel.getSubject());
        Assert.assertEquals(actor, childA.getSubject());
        Assert.assertEquals(actor, childB.getSubject());
        Assert.assertEquals(actor, childC.getSubject());
    }
}
