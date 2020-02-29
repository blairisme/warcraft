/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.action.framework.TransitionAction;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.utility.GameObjectOperations;
import com.evilbird.warcraft.action.move.MoveToItemAction;
import com.evilbird.warcraft.action.selection.DeselectAction;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.unit.building.Building;

import javax.inject.Inject;

/**
 * Instances of this class construct a building.
 *
 * @author Blair Butterworth
 */
public class ConstructSequence extends TransitionAction
{
    private transient Action reposition;
    private transient Action initialize;
    private transient Action construct;

    @Inject
    public ConstructSequence(
        ConstructAction construct,
        ConstructBuilding initialize,
        DeselectAction deselect,
        MoveToItemAction move)
    {
        initialize.setRecipient(this::setTarget);
        this.reposition = add(new ParallelAction(deselect, move));
        this.initialize = add(initialize);
        this.construct = add(construct);
    }

    @Override
    protected Action nextAction(Action previous) {
        return nextAction(getSubject(), getTarget());
    }

    private Action nextAction(GameObject builder, GameObject building) {
        if (UnitOperations.isSelector(building)) {
            return initialize;
        }
        if (!GameObjectOperations.isNear(builder, builder.getWidth(), building)) {
            return reposition;
        }
        if (isConstructing(building)) {
            return construct;
        }
        return null;
    }

    private boolean isConstructing(GameObject object) {
        if (object instanceof Building) {
            Building builder = (Building)object;
            return builder.isConstructing();
        }
        return false;
    }
}
