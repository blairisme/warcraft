/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
