/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.siege;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.CombatantFactory;
import com.evilbird.warcraft.object.unit.combatant.RangedCombatant;

/**
 * A reusable class for creating siege units, loading the necessary assets and
 * defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public abstract class SiegeUnitFactory extends CombatantFactory<SiegeUnitAssets, SiegeUnitBuilder, RangedCombatant>
{
    public SiegeUnitFactory(AssetManager manager, UnitType type) {
        this(manager, type, type);
    }

    public SiegeUnitFactory(AssetManager manager, UnitType assetType, UnitType buildType) {
        super(manager, assetType, buildType);
    }

    @Override
    protected SiegeUnitAssets newAssets(AssetManager manager, UnitType type) {
        return new SiegeUnitAssets(manager, type);
    }

    @Override
    protected SiegeUnitBuilder newBuilder(SiegeUnitAssets assets, UnitType type) {
        return new SiegeUnitBuilder(assets, type);
    }
}