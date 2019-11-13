/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.selection;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.warcraft.object.common.capability.SelectableObject;

import javax.inject.Inject;

/**
 * Instances of this {@link Action} deselect the subject of the action.
 *
 * @author Blair Butterworth
 */
public class DeselectAction extends BasicAction
{
    private SelectEvents events;

    @Inject
    public DeselectAction(SelectEvents events) {
        this.events = events;
    }

    @Override
    public boolean act(float delta) {
        SelectableObject selectable = (SelectableObject) getSubject();
        if (selectable.getSelected()) {
            selectable.setSelected(false);
            events.notifySelected(selectable, false);
        }
        return true;
    }
}
