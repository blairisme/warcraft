/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.select;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.lang.Selectable;

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

    public static SelectFlash selectionFlash() {
        return new SelectFlash();
    }

    @Override
    public boolean act(float delta) {
        time = Math.max(time - delta, 0);
        if (time == 0) {
            Selectable selectable = (Selectable)getItem();
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
