/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.naval;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.CombatantAssets;
import com.evilbird.warcraft.object.unit.combatant.CombatantFactory;
import com.evilbird.warcraft.object.unit.combatant.RangedCombatant;

/**
 * A reusable class for creating naval units, loading the necessary assets and
 * defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public abstract class NavalUnitFactory extends CombatantFactory<CombatantAssets, NavalUnitBuilder, RangedCombatant>
{
    public NavalUnitFactory(AssetManager manager, UnitType type) {
        this(manager, type, type);
    }

    public NavalUnitFactory(AssetManager manager, UnitType assetType, UnitType buildType) {
        super(manager, assetType, buildType);
    }

    @Override
    protected CombatantAssets newAssets(AssetManager manager, UnitType type) {
        return new CombatantAssets(manager, type);
    }

    @Override
    protected NavalUnitBuilder newBuilder(CombatantAssets assets, UnitType type) {
        return new NavalUnitBuilder(assets, type);
    }
}
