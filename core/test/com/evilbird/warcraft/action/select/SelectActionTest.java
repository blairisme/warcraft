/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.select;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.test.testcase.ActionTestCase;
import com.evilbird.warcraft.item.unit.Unit;
import org.junit.Test;
import org.mockito.Mockito;

import static com.evilbird.engine.action.common.ActionRecipient.Subject;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Instances of this unit test validate the {@link SelectAction} class.
 *
 * @author Blair Butterworth
 */
public class SelectActionTest extends ActionTestCase
{
    @Override
    protected Action newAction() {
        SelectAction action = new SelectAction(Subject, true, Mockito.mock(EventQueue.class));
        action.setIdentifier(SelectActions.SelectInvert);
        return action;
    }

    @Override
    protected Enum newIdentifier() {
        return SelectActions.SelectInvert;
    }

    @Test
    public void actTest() {
        Unit unit = (Unit)item;
        assertFalse(unit.getSelected());
        assertTrue(action.act(1));
        assertTrue(unit.getSelected());
    }
}