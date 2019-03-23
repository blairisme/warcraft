/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.control;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

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
