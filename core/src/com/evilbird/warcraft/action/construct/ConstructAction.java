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
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.building.Building;

import java.util.Objects;

/**
 * Represents an {@link Action} that indicates a building is under construction
 * and its progress towards construction.
 *
 * @author Blair Butterworth
 */
public class ConstructAction extends DelayedAction
{
    private ActionTarget source;

    /**
     * Constructs a new instance of this class given a {@link ActionTarget}
     * describing the subject of the action (the building being constructed)
     * and the time it will take for construction to complete.
     *
     * @param source    the subject of the action. This parameter cannot be
     *                  {@code null}.
     * @param duration  the time it will take for construction to complete,
     *                  specified in seconds.
     */
    public ConstructAction(ActionTarget source, float duration) {
        super(duration);
        Objects.requireNonNull(source);
        this.source = source;
    }

    public static ConstructAction construct(ConstructActions producible) {
        return construct(ActionTarget.Item, producible);
    }

    public static ConstructAction construct(ActionTarget source, ConstructActions producible) {
        return new ConstructAction(source, producible.getDuration());
    }

    public static ConstructAction stopConstructing() {
        return new ConstructAction(ActionTarget.Item, 0);
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
        Item source = getSource();
        if (source instanceof Building) {
            return (Building)source;
        }
        throw new UnsupportedOperationException();
    }

    private Item getSource() {
        switch (source) {
            case Item: return getItem();
            case Target: return getTarget();
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
        Item source = getSource();
        if (source instanceof Building) {
            Building building = (Building)source;
            building.setConstructionProgress(1);
        }
    }
}