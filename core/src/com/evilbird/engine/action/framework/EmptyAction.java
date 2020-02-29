/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionResult;

/**
 * An {@link Action} that doesn't do anything; its empty.
 *
 * @author Blair Butterworth
 */
public class EmptyAction extends BasicAction
{
    @Override
    public ActionResult act(float delta) {
        return ActionResult.Complete;
    }
}
