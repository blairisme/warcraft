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

/**
 * Instances of this unit test validate the {@link SelectToggle} class.
 *
 * @author Blair Butterworth
 */
public class SelectToggleTest extends ActionTestCase
{
    @Override
    protected Action newAction() {
        SelectToggle action = new SelectToggle();
        action.setIdentifier(SelectActions.SelectToggle);
        return action;
    }

    @Override
    protected Enum newActionId() {
        return SelectActions.SelectToggle;
    }
}