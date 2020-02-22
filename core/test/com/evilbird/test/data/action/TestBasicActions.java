/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.test.data.action;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.framework.AbstractAction;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.test.data.item.TestItems;
import com.evilbird.warcraft.action.move.MoveActions;

public class TestBasicActions
{
    private TestBasicActions() {
    }

    public static AbstractAction newBasicAction() {
        return newBasicAction(MoveActions.MoveToItem);
    }

    public static AbstractAction newBasicAction(Identifier identifier) {
        AbstractAction action = new TestBasicAction();
        action.setSubject(TestItems.newItem("1"));
        action.setTarget(TestItems.newItem("2"));
        action.setCause(new UserInput(UserInputType.Action, Vector2.Zero, 1));
        action.setIdentifier(identifier);
        return action;
    }
}
