/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.common.ActionRecipient;
import com.evilbird.engine.action.common.ActionUtils;
import com.evilbird.engine.item.Item;

/**
 * Instances of this {@link Action} clear the actions assigned to a specified
 * item.
 *
 * @author Blair Butterworth
 */
public class ClearAction extends BasicAction
{
    private ActionRecipient recipient;

    public ClearAction(ActionRecipient recipient) {
        this.recipient = recipient;
    }

    public static ClearAction clear(ActionRecipient recipient) {
        return new ClearAction(recipient);
    }

    @Override
    public boolean act(float delta) {
        Item item = ActionUtils.getRecipient(this, recipient);
        item.clearActions();
        return true;
    }
}
