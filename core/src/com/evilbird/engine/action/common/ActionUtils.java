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
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.query.UnitOperations;

/**
 * Provides common functions for working with {@link Action Actions}.
 *
 * @author Blair Butterworth
 */
public class ActionUtils
{
    /**
     * Disable construction of static utility class.
     */
    private ActionUtils() {
    }

    /**
     * Returns the {@link Item} indicated by the the given
     * {@link ActionRecipient} from given {@link Action}.
     *
     * @param action    the {@code Action} whose subject, target, parent or
     *                  root will be returned.
     * @param recipient the type of item to return.
     *
     * @return an {@code Item}. This method may return {@code null}.
     */
    public static Item getRecipient(Action action, ActionRecipient recipient) {
        switch (recipient) {
            case Subject: return action.getItem();
            case Target: return action.getTarget();
            case Parent: return action.getItem().getParent();
            case Player: return UnitOperations.getPlayer(action.getItem());
            default: throw new UnsupportedOperationException();
        }
    }
}