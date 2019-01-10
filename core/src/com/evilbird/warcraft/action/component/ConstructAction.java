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
import com.evilbird.engine.common.function.Supplier;
import com.evilbird.engine.common.function.Suppliers;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.building.Building;

/**
 * Instances of this class toggle the state indicating if a building is under
 * construction.
 *
 * @author Blair Butterworth
 */
public class ConstructAction extends BasicAction
{
    private Supplier<Item> supplier;
    private boolean constructing;

    public ConstructAction(Building building, boolean constructing) {
        this(Suppliers.constantValue(building), constructing);
    }

    public ConstructAction(Supplier<Item> supplier, boolean constructing) {
        this.supplier = supplier;
        this.constructing = constructing;
    }

    @Override
    public boolean act(float delta) {
        Building building = (Building)supplier.get();
        building.setConstructing(constructing);
        return true;
    }
}
