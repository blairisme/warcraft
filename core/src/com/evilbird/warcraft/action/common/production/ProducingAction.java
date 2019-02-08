/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.production;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.warcraft.item.unit.building.Building;

/**
 * Instances of this class toggle the state indicating if a building is
 * producing something, or not.
 *
 * @author Blair Butterworth
 */
public class ProducingAction extends BasicAction
{
    private boolean producing;

    public ProducingAction(boolean producing) {
        this.producing = producing;
    }

    @Override
    public boolean act(float delta) {
        Building building = (Building)getItem();
        building.setProducing(producing);
        return true;
    }
}
