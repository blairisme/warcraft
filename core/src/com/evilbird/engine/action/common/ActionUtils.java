/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.action.common;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.query.UnitOperations;

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
     * Returns the {@link GameObject} indicated by the the given
     * {@link ActionRecipient} from given {@link Action}.
     *
     * @param action    the {@code Action} whose subject, target, parent or
     *                  root will be returned.
     * @param recipient the type of item to return.
     *
     * @return an {@code Item}. This method may return {@code null}.
     */
    public static GameObject getRecipient(Action action, ActionRecipient recipient) {
        switch (recipient) {
            case Subject: return action.getSubject();
            case Target: return action.getTarget();
            case Parent: return action.getSubject().getParent();
            case Player: return UnitOperations.getPlayer(action.getSubject());
            default: throw new UnsupportedOperationException();
        }
    }
}