/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.action.Action;

import java.util.List;

/**
 * Instances of this {@link Action Action} provide common implementation
 * for those actions which represent a choice between actions, depending on the current game state.
 *
 * @author Blair Butterworth
 */
public abstract class BranchAction extends CompositeAction
{
    protected transient Action delegate;

    public BranchAction(Action ... actions) {
        super(actions);
        this.delegate = null;
    }

    @Override
    public boolean run(float time) {
        if (delegate == null) {
            delegate = getBranch(actions);
        }
        return delegate.run(time);
    }

    @Override
    public void reset() {
        super.reset();
        delegate = null;
    }

    protected abstract Action getBranch(List<Action> actions);
}
