/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.ranged;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.CombatantFactory;
import com.evilbird.warcraft.object.unit.combatant.RangedCombatant;

/**
 * A reusable class for creating ranged units, loading the necessary assets and
 * defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public abstract class RangedUnitFactory extends CombatantFactory<RangedUnitAssets, RangedUnitBuilder, RangedCombatant>
{
    public RangedUnitFactory(AssetManager manager, UnitType type) {
        this(manager, type, type);
    }

    public RangedUnitFactory(AssetManager manager, UnitType assetType, UnitType buildType) {
        super(manager, assetType, buildType);
    }

    @Override
    protected RangedUnitAssets newAssets(AssetManager manager, UnitType type) {
        return new RangedUnitAssets(manager, type);
    }

    @Override
    protected RangedUnitBuilder newBuilder(RangedUnitAssets assets, UnitType type) {
        return new RangedUnitBuilder(assets, type);
    }
}