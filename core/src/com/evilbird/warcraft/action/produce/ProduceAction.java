/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.produce;

import com.evilbird.engine.action.common.ActionRecipient;
import com.evilbird.engine.action.framework.TemporalAction;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.unit.building.Building;

import static com.evilbird.engine.action.common.ActionRecipient.Subject;
import static com.evilbird.engine.action.common.ActionUtils.getRecipient;

/**
 * Instances of this Action alter an {@link GameObject}s state to indicate that its
 * in the process of producing something.
 *
 * @author Blair Butterworth
 */
public class ProduceAction extends TemporalAction
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
