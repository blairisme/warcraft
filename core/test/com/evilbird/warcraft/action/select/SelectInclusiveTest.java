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
import com.evilbird.engine.common.lang.Selectable;
import com.evilbird.test.testcase.ActionTestCase;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Instances of this unit test validate the {@link SelectInclusive} class.
 *
 * @author Blair Butterworth
 */
public class SelectInclusiveTest extends ActionTestCase
{
    @Override
    protected Action newAction() {
        SelectInclusive action = new SelectInclusive(Mockito.mock(SelectReporter.class));
        action.setIdentifier(SelectActions.SelectInclusive);
        return action;
    }

    @Override
    protected Enum newIdentifier() {
        return SelectActions.SelectInclusive;
    }

    @Test
    public void actTest() {
        Selectable selectable = (Selectable)item;

        assertFalse(selectable.getSelected());
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