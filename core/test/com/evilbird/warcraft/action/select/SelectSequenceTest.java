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
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Instances of this unit test validate the {@link SelectSequence} class.
 *
 * @author Blair Butterworth
 */
public class SelectSequenceTest extends ActionTestCase
{
    @Override
    protected Action newAction() {
        SelectSequence action = new SelectSequence(Mockito.mock(SelectReporter.class));
        action.setIdentifier(SelectActions.SelectToggle);
        return action;
    }

    @Override
    protected Enum newIdentifier() {
        return SelectActions.SelectToggle;
    }

    @Test
    public void actTest() {
        assertFalse(item.getSelected());
        assertTrue(action.act(1));
        assertTrue(item.getSelected());

        assertTrue(action.act(1));
        assertTrue(item.getSelected());

        action.restart();

        assertTrue(item.getSelected());
        assertTrue(action.act(1));
        assertFalse(item.getSelected());
    }
}