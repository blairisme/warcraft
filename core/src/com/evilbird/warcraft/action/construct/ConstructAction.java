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
import com.evilbird.engine.action.common.ActionRecipient;
import com.evilbird.engine.action.framework.DelayedAction;
import com.evilbird.warcraft.item.unit.building.Building;

import static com.evilbird.engine.action.common.ActionRecipient.Subject;
import static com.evilbird.engine.action.common.ActionRecipient.Target;
import static com.evilbird.engine.action.common.ActionUtils.getRecipient;

/**
 * Represents an {@link Action} that indicates a building is under construction
 * and its progress towards construction.
 *
 * @author Blair Butterworth
 */
public class ConstructAction extends DelayedAction
{
    private ActionRecipient building;

    /**
     * Constructs a new instance of this class given a {@link ActionRecipient}
     * describing the subject of the action (the building being constructed)
     * and the time it will take for construction to complete.
     *
     * @param building  an {@link ActionRecipient} indicating the building
     *                  under construction. This parameter cannot be {@code null}.
     * @param start     the starting point of the construct action, if
     *                  construction is partially complete.
     * @param duration  the time it will take for construction to complete,
     *                  specified in seconds.
     */
    public ConstructAction(ActionRecipient building, float start, float duration) {
        super(start, duration);
        this.building = building;
    }

    public static ConstructAction construct(float duration){
        return new ConstructAction(Target, 0, duration);
    }

    public static ConstructAction construct(float start, float duration){
        return new ConstructAction(Target, start, duration);
    }

    public static ConstructAction stopConstructing() {
        return new ConstructAction(Subject, 0, 0);
    }

    @Override
    public boolean act(float delta) {
        super.act(delta);
        Building building = (Building)getRecipient(this, this.building);
        if (! isComplete()) {
            building.setConstructionProgress(getProgress());
            return false;
        }
        else {
            building.setConstructionProgress(1);
            return true;
        }
    }
}