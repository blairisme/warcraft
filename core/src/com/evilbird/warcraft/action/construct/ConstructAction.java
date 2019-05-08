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
import com.evilbird.engine.action.common.ActionRecipient;
import com.evilbird.engine.action.framework.DelayedAction;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.building.Building;

import java.util.Objects;

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
    private ActionRecipient builder;

    /**
     * Constructs a new instance of this class given a {@link ActionRecipient}
     * describing the subject of the action (the building being constructed)
     * and the time it will take for construction to complete.
     *
     * @param building  an {@link ActionRecipient} indicating the building
     *                  under construction. This parameter cannot be {@code null}.
     * @param builder   an {@link ActionRecipient} indicating the building
     *                  under construction. This parameter cannot be {@code null}.
     * @param duration  the time it will take for construction to complete,
     *                  specified in seconds.
     */
    public ConstructAction(ActionRecipient building, ActionRecipient builder, float duration) {
        super(duration);

        Objects.requireNonNull(builder);
        Objects.requireNonNull(building);

        this.builder = builder;
        this.building = building;
    }

    public static ConstructAction construct(ConstructActions action) {
        return construct(Target, Subject, action);
    }

    public static ConstructAction construct(ActionRecipient building, ActionRecipient builder, ConstructActions action){
        return new ConstructAction(building, builder, action.getDuration());
    }

    public static ConstructAction stopConstructing() {
        return stopConstructing(Subject, Target);
    }

    public static ConstructAction stopConstructing(ActionRecipient building, ActionRecipient builder) {
        return new ConstructAction(building, builder, 0);
    }

    @Override
    public boolean act(float delta) {
        super.act(delta);
        Building building = getBuilding();
        if (! isComplete()) {
            if (building.getConstructor() == null) {
                building.setConstructor(getBuilder());
            }
            building.setConstructionProgress(getProgress());
            return false;
        }
        else {
            building.setConstructor(null);
            building.setConstructionProgress(1);
            return true;
        }
    }

    private Building getBuilding() {
        return (Building)getRecipient(this, building);
    }

    private Item getBuilder() {
        return getRecipient(this, builder);
    }

//    @Override
//    public void restart() {
//        super.restart();
//        resetProgress();
//    }
//
//    @Override
//    public void reset() {
//        super.reset();
//        resetProgress();
//    }
//
//    private void resetProgress() {
//        Item recipient = getRecipient(this, building);
//        if (recipient instanceof Building) {
//            Building item = (Building)recipient;
//            item.setConstructionProgress(1);
//        }
//    }
}