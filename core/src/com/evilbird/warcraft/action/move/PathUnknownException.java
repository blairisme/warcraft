/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.move;

import com.evilbird.engine.action.ActionException;
import com.evilbird.engine.object.GameObject;

/**
 * Instances of this error are produced when a {@link MoveAction} is unable
 * find a path to a given destination.
 *
 * @author Blair Butterworth
 */
public class PathUnknownException extends ActionException
{
    public PathUnknownException(GameObject gameObject) {
        super("Unable to determine path to " + gameObject.getIdentifier());
    }
}
