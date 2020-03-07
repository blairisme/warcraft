/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.selection;

import com.evilbird.engine.action.Action;
import com.evilbird.test.testcase.ActionTestCase;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.object.common.capability.SelectableObject;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Instances of this unit test validate the {@link SelectInvert} class.
 *
 * @author Blair Butterworth
 */
public class SelectInvertTest extends ActionTestCase
{
    @Override
    protected Action newAction() {
        SelectEvents events = Mockito.mock(SelectEvents.class);
        WarcraftPreferences preferences = Mockito.mock(WarcraftPreferences.class);
        SelectInvert action = new SelectInvert(events, preferences);
        action.setIdentifier(SelectActions.SelectInvert);
        return action;
    }

    @Override
    protected Enum newIdentifier() {
        return SelectActions.SelectInvert;
    }

    @Test
    @Override
    public void runTest() {
        SelectableObject selectable = (SelectableObject)gameObject;

        assertFalse(selectable.getSelected());
        //assertFalse(action.act(1));
        assertTrue(action.run(1));
        assertTrue(selectable.getSelected());

        action.restart();

        assertTrue(action.run(1));
        assertFalse(selectable.getSelected());

        action.restart();

        assertFalse(selectable.getSelected());
        assertTrue(action.run(1));
        assertTrue(selectable.getSelected());
    }
}