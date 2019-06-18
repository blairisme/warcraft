/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.produce;

import com.evilbird.engine.action.common.ActionRecipient;
import com.evilbird.engine.action.framework.DelayedAction;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.building.Building;

import static com.evilbird.engine.action.common.ActionRecipient.Subject;
import static com.evilbird.engine.action.common.ActionUtils.getRecipient;

/**
 * Instances of this Action alter an {@link Item}s state to indicate that its
 * in the process of producing something.
 *
 * @author Blair Butterworth
 */
public class ProduceAction extends DelayedAction
{
    private ActionRecipient recipient;

    public ProduceAction(ActionRecipient recipient, float start, float duration) {
        super(start, duration);
        this.recipient = recipient;
    }

    public static ProduceAction startProducing(float duration) {
        return new ProduceAction(Subject, 0, duration);
    }

    public static ProduceAction startProducing(float start, float duration) {
        return new ProduceAction(Subject, start, duration);
    }

    public static ProduceAction stopProducing() {
        return new ProduceAction(Subject, 0, 0);
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
}
