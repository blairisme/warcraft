/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.evilbird.engine.test.MockBasicAction;
import com.evilbird.engine.test.MockCompositeAction;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class CompositeActionTest
{
    @Test
    public void actorTest() {
        BasicAction childA = new MockBasicAction();
        BasicAction childB = new MockBasicAction();
        BasicAction childC = new MockBasicAction();
        MockCompositeAction composite = new MockCompositeAction(childA, childB, childC);

        Assert.assertNull(composite.getActor());
        Assert.assertNull(childA.getActor());
        Assert.assertNull(childB.getActor());
        Assert.assertNull(childC.getActor());

        Actor actor = Mockito.mock(Actor.class);
        composite.setActor(actor);

        Assert.assertEquals(actor, composite.getActor());
        Assert.assertEquals(actor, childA.getActor());
        Assert.assertEquals(actor, childB.getActor());
        Assert.assertEquals(actor, childC.getActor());
    }

    @Test
    public void errorTest() {
        BasicAction childA = new MockBasicAction();
        BasicAction childB = new MockBasicAction();
        BasicAction childC = new MockBasicAction();
        MockCompositeAction composite = new MockCompositeAction(childA, childB, childC);

        Assert.assertNull(composite.getError());
        Assert.assertFalse(composite.hasError());

        Throwable error = new UnknownError();
        childB.setError(error);

        Assert.assertEquals(error, composite.getError());
        Assert.assertEquals(error, childB.getError());
        Assert.assertNull(childA.getError());
        Assert.assertNull(childC.getError());
    }
}