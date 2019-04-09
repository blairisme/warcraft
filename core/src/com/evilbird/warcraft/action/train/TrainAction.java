/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.train;

import com.evilbird.engine.action.common.ActionTarget;
import com.evilbird.engine.action.framework.DelayedAction;
import com.evilbird.engine.common.serialization.SerializedConstructor;
import com.evilbird.warcraft.item.unit.building.Building;

/**
 * Represents an action whose operation takes time, which is communicated to
 * the user via a progress bar.
 *
 * @author Blair Butterworth
 */
public class TrainAction extends DelayedAction
{
    private ActionTarget source;

    @SerializedConstructor
    private TrainAction() {
    }

    public TrainAction(float duration) {
        this(ActionTarget.Item, duration);
    }

    public TrainAction(ActionTarget source, float duration) {
        super(duration);
        this.source = source;
    }

    public static TrainAction produce(TrainActions producible) {
        return produce(ActionTarget.Item, producible);
    }

    public static TrainAction produce(ActionTarget source, TrainActions producible) {
        return new TrainAction(source, producible.getDuration());
    }

    @Override
    public boolean act(float delta) {
        super.act(delta);
        Building building = getBuilding();
        if (! isComplete()) {
            building.setProductionProgress(getProgress());
            return false;
        }
        else {
            building.setProductionProgress(1);
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
            building.setProductionProgress(1);
        }
    }
}
