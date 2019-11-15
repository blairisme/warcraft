/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.flying;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.CombatantFactory;

/**
 * A reusable class for creating {@link FlyingUnit FlyingUnits}, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public abstract class FlyingUnitFactory extends CombatantFactory<FlyingUnitAssets, FlyingUnitBuilder, FlyingUnit>
{
    public FlyingUnitFactory(AssetManager manager, UnitType type) {
        this(manager, type, type);
    }

    public FlyingUnitFactory(AssetManager manager, UnitType assetType, UnitType buildType) {
        super(manager, assetType, buildType);
    }

    @Override
    protected FlyingUnitAssets newAssets(AssetManager manager, UnitType type) {
        return new FlyingUnitAssets(manager, type);
    }

    @Override
    protected FlyingUnitBuilder newBuilder(FlyingUnitAssets assets, UnitType type) {
        return new FlyingUnitBuilder(assets, type);
    }
}