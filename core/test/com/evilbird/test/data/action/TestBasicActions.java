/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.test.data.action;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionException;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.test.data.item.TestItems;
import com.evilbird.warcraft.action.move.MoveActions;

public class TestBasicActions
{
    public static Action newBasicAction() {
        Action action = new TestBasicAction();
        action.setItem(TestItems.newItem("1"));
        action.setTarget(TestItems.newItem("2"));
        action.setCause(new UserInput(UserInputType.Action, Vector2.Zero, 1));
        action.setError(new ActionException("An error has occurred"));
        action.setIdentifier(MoveActions.MoveToItem);
        return action;
    }
}
