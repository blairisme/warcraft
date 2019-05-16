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
import com.evilbird.test.testcase.ActionTestCase;
import org.mockito.Mockito;

import static com.evilbird.engine.action.common.ActionRecipient.Subject;

/**
 * Instances of this unit test validate the {@link SelectAction} class.
 *
 * @author Blair Butterworth
 */
public class SelectActionTest extends ActionTestCase
{
    @Override
    protected Action newAction() {
        SelectAction action = new SelectAction(Subject, true, Mockito.mock(SelectReporter.class));
        action.setIdentifier(SelectActions.SelectToggle);
        return action;
    }

    @Override
    protected Enum newIdentifier() {
        return SelectActions.SelectToggle;
    }
}