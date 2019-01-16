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
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.building.Building;

import static com.evilbird.engine.common.function.Suppliers.constantValue;

/**
 * Instances of this class toggle the state indicating if a building is under
 * construction.
 *
 * @author Blair Butterworth
 */
public class ConstructAction extends BasicAction
{
    private Supplier<? extends Building> buildingSupplier;
    private Supplier<? extends Item> builderSupplier;
    private boolean constructing;

    public ConstructAction(Building building, Item builder, boolean constructing) {
        this(constantValue(building), constantValue(builder), constructing);
    }

    public ConstructAction(
        Supplier<? extends Building> buildingSupplier,
        Supplier<? extends Item> builderSupplier,
        boolean constructing)
    {
        this.buildingSupplier = buildingSupplier;
        this.builderSupplier = builderSupplier;
        this.constructing = constructing;
    }

    @Override
    public boolean act(float delta) {
        Building building = buildingSupplier.get();
        building.setBuilder(builderSupplier.get());
        building.setConstructing(constructing);
        return true;
    }
}
