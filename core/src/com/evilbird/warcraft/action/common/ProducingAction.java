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
import com.evilbird.engine.common.function.Supplier;
import com.evilbird.engine.common.function.Suppliers;
import com.evilbird.warcraft.item.unit.building.Building;

/**
 * Instances of this class toggle the state indicating if a building is
 * producing something, or not.
 *
 * @author Blair Butterworth
 */
public class ProducingAction extends BasicAction
{
    private Supplier<Building> supplier;
    private boolean producing;

    public ProducingAction(Building building, boolean producing) {
        this(Suppliers.constantValue(building), producing);
    }

    public ProducingAction(Supplier<Building> supplier, boolean producing) {
        this.supplier = supplier;
        this.producing = producing;
    }

    @Override
    public boolean act(float delta) {
        Building building = supplier.get();
        building.setProducing(producing);
        return true;
    }
}
