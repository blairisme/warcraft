/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.ranged;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.CombatantAssets;
import com.evilbird.warcraft.object.unit.combatant.CombatantFactory;
import com.evilbird.warcraft.object.unit.combatant.RangedCombatant;

/**
 * A reusable class for creating ranged units, loading the necessary assets and
 * defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public abstract class RangedUnitFactory extends CombatantFactory<CombatantAssets, RangedUnitBuilder, RangedCombatant>
{
    public RangedUnitFactory(AssetManager manager, UnitType type) {
        this(manager, type, type);
    }

    public RangedUnitFactory(AssetManager manager, UnitType assetType, UnitType buildType) {
        super(manager, assetType, buildType);
    }

    @Override
    protected CombatantAssets newAssets(AssetManager manager, UnitType type) {
        return new CombatantAssets(manager, type);
    }

    @Override
    protected RangedUnitBuilder newBuilder(CombatantAssets assets, UnitType type) {
        return new RangedUnitBuilder(assets, type);
    }
}