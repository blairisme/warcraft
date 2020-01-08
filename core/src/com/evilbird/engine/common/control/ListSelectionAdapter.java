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
 * An adapter that converts a {@link ListSelectionListener} into a
 * {@link ChangeListener}.
 *
 * @author Blair Butterworth
 */
class ListSelectionAdapter<T> extends ChangeListener
{
    private ListSelectionListener<T> listener;

    public ListSelectionAdapter(ListSelectionListener<T> listener) {
        this.listener = listener;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void changed(ChangeEvent event, Actor actor) {
        if (actor instanceof ListPane) {
            ListPane<T> list = (ListPane<T>)actor;
            listener.onSelected(list.getSelected());
        }
    }
}
