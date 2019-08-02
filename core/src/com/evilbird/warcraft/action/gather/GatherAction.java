/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.common.ActionRecipient;
import com.evilbird.engine.action.framework.DelayedAction;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;

import static com.evilbird.engine.action.common.ActionRecipient.Subject;
import static com.evilbird.engine.action.common.ActionUtils.getRecipient;

/**
 * Represents an {@link Action} that indicates that a gatherer is gathering
 * resources and the time it will take for the gathering process to complete.
 *
 * @author Blair Butterworth
 */
public class GatherAction extends DelayedAction
{
    private ActionRecipient recipient;

    /**
     * Constructs a new instance of this class given a {@link ActionRecipient}
     * describing the subject of the action (the gatherer gathering resources)
     * and the time it will take for gathering process to complete.
     *
     * @param recipient an {@link ActionRecipient} indicating the gatherer
     *                  gathering resources. This parameter cannot be {@code null}.
     * @param start     the starting point of the gather action, if
     *                  gathering is partially complete.
     * @param duration  the time it will take for gathering to complete,
     *                  specified in seconds.
     */
    public GatherAction(ActionRecipient recipient, float start, float duration) {
        super(start, duration);
        this.recipient = recipient;
    }

    public static GatherAction gather(float duration){
        return new GatherAction(Subject, 0, duration);
    }

    public static GatherAction gather(float start, float duration){
        return new GatherAction(Subject, start, duration);
    }

    public static GatherAction stopGathering() {
        return new GatherAction(Subject, 0, 0);
    }

    @Override
    public boolean act(float delta) {
        super.act(delta);
        Gatherer gatherer = (Gatherer)getRecipient(this, recipient);
        if (! isComplete()) {
            gatherer.setGathererProgress(getProgress());
            return false;
        }
        else {
            gatherer.setGathererProgress(1);
            return true;
        }
    }
}