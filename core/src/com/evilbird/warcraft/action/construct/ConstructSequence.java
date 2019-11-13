/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.action.framework.StateTransitionAction;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.utility.GameObjectOperations;
import com.evilbird.warcraft.action.move.MoveToItemAction;
import com.evilbird.warcraft.action.selection.DeselectAction;
import com.evilbird.warcraft.object.common.query.UnitOperations;

import javax.inject.Inject;

/**
 * Instances of this class construct a building.
 *
 * @author Blair Butterworth
 */
public class ConstructSequence extends StateTransitionAction
{
    private Action reposition;
    private Action initialize;
    private Action construct;

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
        if (UnitOperations.isConstructing(building)) {
            return construct;
        }
        return null;
    }
}
