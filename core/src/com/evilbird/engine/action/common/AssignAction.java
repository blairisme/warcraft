/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.common;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.item.Item;

/**
 * Instances of this {@link Action} assign an action to a specified item.
 *
 * @author Blair Butterworth
 */
public class AssignAction extends BasicAction
{
    private Action action;
    private ActionRecipient recipient;

    public AssignAction(Action action, ActionRecipient recipient) {
        this.action = action;
        this.recipient = recipient;
    }

    public static Action assign(Action action, ActionRecipient recipient) {
        return new AssignAction(action, recipient);
    }

    @Override
    public boolean act(float delta) {
        Item item = ActionUtils.getRecipient(this, recipient);
        item.addAction(action);
        return true;
    }
}
