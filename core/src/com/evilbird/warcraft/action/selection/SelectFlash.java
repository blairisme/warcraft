/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.selection;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.warcraft.object.common.capability.SelectableObject;

import javax.inject.Inject;

/**
 * Instances of this {@link Action} repeatedly swap the selection of a given
 * item for a set number of cycles. This action is commonly used to draw the
 * users attention to an item when its state has meaningfully changed but not
 * as the result of direct user interaction.
 *
 * @author Blair Butterworth
 */
public class SelectFlash extends BasicAction
{
    private static final transient float DELAY = 0.3f;
    private static final transient float REPETITIONS = 10;

    private transient float time;
    private transient float count;

    @Inject
    public SelectFlash() {
    }

    @Override
    public boolean act(float delta) {
        time = Math.max(time - delta, 0);
        if (time == 0) {
            SelectableObject selectable = (SelectableObject) getSubject();
            selectable.setSelected(!selectable.getSelected());

            count += 1;
            time = DELAY;
        }
        return count >= REPETITIONS;
    }

    @Override
    public void reset() {
        super.reset();
        time = 0;
        count = 0;
    }

    @Override
    public void restart() {
        super.restart();
        time = 0;
        count = 0;
    }
}
