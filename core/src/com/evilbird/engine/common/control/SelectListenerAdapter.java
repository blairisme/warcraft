/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.control;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Instances of this adapter wrap a {@link SelectListener}, decorating it as
 * an {@link ChangeListener}.
 *
 * @author Blair Butterworth
 */
public class SelectListenerAdapter extends ChangeListener
{
    private SelectListener listener;

    public SelectListenerAdapter(SelectListener listener) {
        this.listener = listener;
    }

    @Override
    public void changed(ChangeEvent event, Actor actor) {
        listener.onSelect();
    }
}
