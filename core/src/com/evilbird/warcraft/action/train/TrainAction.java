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
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.building.Building;

/**
 * Instances of this Action alter an {@link Item}s state to indicate that its
 * in the process of producing something.
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

    public static TrainAction startProducing(TrainActions producible) {
        return startProducing(ActionTarget.Item, producible);
    }

    public static TrainAction startProducing(ActionTarget source, TrainActions producible) {
        return new TrainAction(source, producible.getDuration());
    }

    public static TrainAction stopProducing() {
        return new TrainAction(ActionTarget.Item, 0);
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
