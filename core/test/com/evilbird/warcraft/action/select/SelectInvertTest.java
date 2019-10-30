/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.select;

import com.evilbird.engine.action.Action;
import com.evilbird.test.testcase.ActionTestCase;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.item.common.capability.SelectableObject;
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
    @Ignore
    public void actTest() {
        SelectableObject selectable = (SelectableObject)item;

        assertFalse(selectable.getSelected());
        assertFalse(action.act(1));
        assertTrue(action.act(1));
        assertTrue(selectable.getSelected());

        assertTrue(action.act(1));
        assertTrue(selectable.getSelected());

        action.restart();

        assertTrue(selectable.getSelected());
        assertTrue(action.act(1));
        assertFalse(selectable.getSelected());
    }
}