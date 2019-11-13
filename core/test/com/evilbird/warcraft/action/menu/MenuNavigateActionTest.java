/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.menu;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.common.ActionRecipient;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.object.GameObject;
import com.evilbird.test.testcase.ActionTestCase;
import com.evilbird.warcraft.object.display.control.actions.ActionPane;
import org.mockito.Mockito;

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
        action.setSource(ActionRecipient.Subject);
        action.setIdentifier(MenuActions.BuildSimpleMenu);
        return action;
    }

    @Override
    protected Enum newIdentifier() {
        return MenuActions.BuildSimpleMenu;
    }

    @Override
    protected GameObject newItem() {
        ActionPane result = new ActionPane(Mockito.mock(Skin.class));
        result.setIdentifier(new TextIdentifier("item"));
        return result;
    }
}