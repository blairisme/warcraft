/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.specialized;

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
