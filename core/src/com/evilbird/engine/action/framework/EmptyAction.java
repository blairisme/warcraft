/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.action.Action;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;

/**
 * An {@link Action} that doesn't do anything; its empty.
 *
 * @author Blair Butterworth
 */
public class EmptyAction extends AbstractAction
{
    @Override
    public boolean act(float delta) {
        return ActionComplete;
    }
}
