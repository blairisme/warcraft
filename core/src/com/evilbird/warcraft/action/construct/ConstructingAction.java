/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.warcraft.item.unit.building.Building;

/**
 * Instances of this class toggle the state indicating if a building is under
 * construction.
 *
 * @author Blair Butterworth
 */
public class ConstructingAction extends BasicAction
{
    private boolean constructing;

    public ConstructingAction(boolean constructing) {
        this.constructing = constructing;
    }

    @Override
    public boolean act(float delta) {
        Building building = (Building)getItem();
        //building.setBuilder(getTarget());
        building.setConstructing(constructing);
        return true;
    }

}
