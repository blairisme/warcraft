/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.component;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.action.framework.duration.TimeDuration;
import com.evilbird.engine.common.function.Supplier;
import com.evilbird.engine.common.function.Suppliers;
import com.evilbird.warcraft.item.unit.building.Building;

/**
 * Instances of this action represent an action whose operation takes time,
 * which is communicated to the user via a progress bar.
 *
 * @author Blair Butterworth
 */
public class ProgressAction extends BasicAction
{
    private Building building;
    private Supplier<? extends Building> supplier;
    private TimeDuration duration;

    public ProgressAction(Building building, TimeDuration duration) {
        this(Suppliers.constantValue(building), duration);
    }

    public ProgressAction(Supplier<? extends Building> supplier, TimeDuration duration) {
        this.supplier = supplier;
        this.duration = duration;
    }

    //TODO: Update building before null check
    @Override
    public void restart() {
        super.restart();
        duration.restart();
        if (building != null) {
            building.setProgress(0f);
        }
    }

    @Override
    public boolean act(float delta) {
        if (! updateBuilding()){
            cancel();
            return true;
        }
        return updateProgress(delta);
    }

    private boolean updateBuilding() {
        if (building == null){
            building = supplier.get();
        }
        return building != null;
    }

    private boolean updateProgress(float delta) {
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
