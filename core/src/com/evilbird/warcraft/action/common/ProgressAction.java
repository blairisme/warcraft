/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.action.framework.duration.TimeDuration;
import com.evilbird.engine.common.function.Supplier;
import com.evilbird.engine.common.function.Suppliers;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;

/**
 * Instances of this action represent an action whose operation takes time,
 * which is communicated to the user via a progress bar.
 *
 * @author Blair Butterworth
 */
public class ProgressAction extends BasicAction
{
    private TimeDuration duration;

    @Inject
    public ProgressAction() {
    }

    @Deprecated
    public ProgressAction(Building building, TimeDuration duration) {
        setItem(building);
        this.duration = duration;
    }

    public void setDuration(TimeDuration duration) {
        this.duration = duration;
    }

    @Override
    public void restart() {
        super.restart();
        if (duration != null) {
            duration.restart();
        }
        Building building = (Building)getItem();
        if (building != null) {
            building.setProgress(0f);
        }
    }

    @Override
    public boolean act(float delta) {
        Building building = (Building)getItem();
        if (! duration.isComplete(delta)) {
            building.setProgress(duration.getProgress());
            return false;
        }
        else {
            building.setProgress(0f);
            return true;
        }
    }
}
