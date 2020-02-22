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
import com.evilbird.test.data.action.TestBasicActions;
import com.evilbird.test.verifier.EqualityVerifier;
import org.junit.Before;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link AbstractAction} class.
 *
 * @author Blair Butterworth
 */
public class AbstractActionTest
{
    private AbstractAction action;

    @Before
    public void setup() {
        action = TestBasicActions.newBasicAction();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(TestBasicAction.class)
            .withMockedTransientFields(GameObject.class)
            .withMockedType(Pool.class)
            .excludeTransientFields()
            .verify();
    }
}