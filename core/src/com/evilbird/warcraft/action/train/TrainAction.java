/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.train;

import com.evilbird.engine.action.common.ActionRecipient;
import com.evilbird.engine.action.framework.DelayedAction;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.building.Building;

import static com.evilbird.engine.action.common.ActionUtils.getRecipient;

/**
 * Instances of this Action alter an {@link Item}s state to indicate that its
 * in the process of producing something.
 *
 * @author Blair Butterworth
 */
public class TrainAction extends DelayedAction
{
    private ActionRecipient recipient;

    public TrainAction(ActionRecipient recipient, float duration) {
        super(duration);
        this.recipient = recipient;
    }

    public static TrainAction startProducing(TrainActions producible) {
        return startProducing(ActionRecipient.Subject, producible);
    }

    public static TrainAction startProducing(ActionRecipient source, TrainActions producible) {
        return new TrainAction(source, producible.getDuration());
    }

    public static TrainAction stopProducing() {
        return new TrainAction(ActionRecipient.Subject, 0);
    }

    @Override
    public boolean act(float delta) {
        super.act(delta);
        Building building = (Building)getRecipient(this, recipient);
        if (! isComplete()) {
            building.setProductionProgress(getProgress());
            return false;
        }
        else {
            building.setProductionProgress(1);
            return true;
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
        Building building = (Building)getRecipient(this, recipient);
        if (building != null) {
            building.setProductionProgress(1);
        }
    }
}
