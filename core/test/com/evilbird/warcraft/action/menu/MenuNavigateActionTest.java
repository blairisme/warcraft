/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.menu;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.common.ActionTarget;
import com.evilbird.test.testcase.ActionTestCase;

/**
 * Instances of this unit test validate the {@link MenuNavigateAction} class.
 *
 * @author Blair Butterworth
 */
public class MenuNavigateActionTest extends ActionTestCase
{
    @Override
    protected Action newAction() {
        MenuNavigateAction action = new MenuNavigateAction();
        action.setSource(ActionTarget.Parent);
        action.setIdentifier(MenuActions.BuildSimpleMenu);
        return action;
    }

    @Override
    protected Enum newIdentifier() {
        return MenuActions.BuildSimpleMenu;
    }
}