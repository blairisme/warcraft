/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.naval;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.CombatantFactory;
import com.evilbird.warcraft.object.unit.combatant.RangedCombatant;

/**
 * A reusable class for creating naval units, loading the necessary assets and
 * defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public abstract class NavalUnitFactory extends CombatantFactory<NavalUnitAssets, NavalUnitBuilder, RangedCombatant>
{
    public NavalUnitFactory(AssetManager manager, UnitType type) {
        this(manager, type, type);
    }

    public NavalUnitFactory(AssetManager manager, UnitType assetType, UnitType buildType) {
        super(manager, assetType, buildType);
    }

    @Override
    protected NavalUnitAssets newAssets(AssetManager manager, UnitType type) {
        return new NavalUnitAssets(manager, type);
    }

    @Override
    protected NavalUnitBuilder newBuilder(NavalUnitAssets assets, UnitType type) {
        return new NavalUnitBuilder(assets, type);
    }
}
