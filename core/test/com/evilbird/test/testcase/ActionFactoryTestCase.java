/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.test.testcase;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.warcraft.action.ActionProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Instances of this test case provide common validation for action factories.
 *
 * @author Blair Butterworth
 */
public abstract class ActionFactoryTestCase extends GameTestCase
{
    private ActionProvider factory;

    @Before
    public void setup() {
        factory = newFactory();
    }

    protected abstract ActionProvider newFactory();

    @Test
    public void getTest() {
        for (ActionIdentifier identifier: getIdentifiers()) {
            Action action = factory.get(identifier);
            Assert.assertNotNull(action);
            //Mockito.verify(action).setIdentifier(identifier);
            //Assert.assertEquals(identifier, action.getIdentifier());
        }
    }

    protected abstract ActionIdentifier[] getIdentifiers();
}
