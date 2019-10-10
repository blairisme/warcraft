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
import com.evilbird.engine.events.Events;
import com.evilbird.warcraft.item.common.state.SelectableObject;

import javax.inject.Inject;

/**
 * Instances of this {@link Action} deselect the subject of the action.
 *
 * @author Blair Butterworth
 */
public class DeselectAction extends BasicAction
{
    private Events events;

    @Inject
    public DeselectAction(Events events) {
        this.events = events;
    }

    @Override
    public boolean act(float delta) {
        SelectableObject selectable = (SelectableObject)getItem();
        if (selectable.getSelected()) {
            selectable.setSelected(false);
            events.add(new SelectEvent(selectable, false));
        }
        return true;
    }
}
