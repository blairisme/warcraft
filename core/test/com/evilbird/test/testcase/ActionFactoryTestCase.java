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
import org.mockito.Mockito;

/**
 * Instances of this test case provide common validation for action factories.
 *
 * @author Blair Butterworth
 */
public abstract class ActionFactoryTestCase extends GameTestCase
{
    protected ActionProvider factory;
    protected boolean verifyIdentifiers;

    @Before
    public void setup() {
        factory = newFactory();
        verifyIdentifiers = true;
    }

    protected abstract ActionProvider newFactory();

    @Test
    public void getTest() {
        for (ActionIdentifier identifier: getIdentifiers()) {
            Action action = factory.get(identifier);
            Assert.assertNotNull(action);

            if (verifyIdentifiers) {
                Mockito.verify(action).setIdentifier(identifier);
            }
        }
    }

    protected abstract ActionIdentifier[] getIdentifiers();
}
