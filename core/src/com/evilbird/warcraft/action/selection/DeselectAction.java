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
            events.selectionUpdated(selectable, false);
        }
        return true;
    }
}
