/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.common.ActionTarget;
import com.evilbird.engine.action.framework.DelayedAction;
import com.evilbird.engine.common.serialization.SerializedConstructor;
import com.evilbird.warcraft.item.unit.building.Building;

/**
 * Represents an {@link Action} that indicates a building is under construction
 * and its progress towards construction.
 *
 * @author Blair Butterworth
 */
public class ConstructionAction extends DelayedAction
{
    private ActionTarget source;

    @SerializedConstructor
    private ConstructionAction() {
    }

    public ConstructionAction(float duration) {
        this(ActionTarget.Item, duration);
    }

    public ConstructionAction(ActionTarget source, float duration) {
        super(duration);
        this.source = source;
    }

    public static ConstructionAction construct(ConstructActions producible) {
        return construct(ActionTarget.Item, producible);
    }

    public static ConstructionAction construct(ActionTarget source, ConstructActions producible) {
        return new ConstructionAction(source, producible.getDuration());
    }

    @Override
    public boolean act(float delta) {
        super.act(delta);
        Building building = getBuilding();
        if (! isComplete()) {
            building.setConstructionProgress(getProgress());
            return false;
        }
        else {
            building.setConstructionProgress(1);
            return true;
        }
    }

    private Building getBuilding() {
        switch (source) {
            case Item: return (Building)getItem();
            case Target: return (Building)getTarget();
            default: throw new UnsupportedOperationException();
        }
    }

    @Override
    public void restart() {
        super.restart();
        resetProgress();
    }

    @Override
    public void reset() {
        super.reset();
        resetProgress();
    }

    private void resetProgress() {
        Building building = getBuilding();
        if (building != null) {
            building.setConstructionProgress(1);
        }
    }
}