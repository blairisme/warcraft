/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionIdentifier;

/**
 * Implementors of this interface provide a method that creates an action given
 * a type identifier and contextual information.
 *
 * @author Blair Butterworth
 */
public interface ActionProvider
{
    Action get(ActionIdentifier action);
}
