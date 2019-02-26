/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.production;

import com.evilbird.engine.action.framework.DelayedAction;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;

/**
 * Instances of this action represent an action whose operation takes time,
 * which is communicated to the user via a progress bar.
 *
 * @author Blair Butterworth
 */
public class ProgressAction extends DelayedAction
{
    @Inject
    public ProgressAction() {
    }

    @Override
    public boolean act(float delta) {
        super.act(delta);

        Building building = (Building)getItem();
        if (! isComplete()) {
            building.setProgress(getProgress());
            return false;
        }
        else {
            building.setProgress(0f);
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
        Building building = (Building)getItem();
        if (building != null) {
            building.setProgress(0f);
        }
    }
}
