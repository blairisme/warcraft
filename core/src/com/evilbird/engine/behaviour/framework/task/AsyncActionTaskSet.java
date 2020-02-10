/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.behaviour.framework.task;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionFactory;

/**
 * @author Blair Butterworth
 */
public abstract class AsyncActionTaskSet<T> extends ActionTaskSet<T>
{
    public AsyncActionTaskSet(ActionFactory factory) {
        super(factory);
    }

    @Override
    protected Status getStatus(Action action) {
        return Status.SUCCEEDED;
    }
}
