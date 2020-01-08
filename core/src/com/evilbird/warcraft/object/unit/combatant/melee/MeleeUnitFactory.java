/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.melee;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import com.evilbird.warcraft.object.unit.combatant.CombatantFactory;

/**
 * A reusable class for creating melee units, loading the necessary assets and
 * defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public abstract class MeleeUnitFactory extends CombatantFactory<MeleeUnitAssets, MeleeUnitBuilder, Combatant>
{
    public MeleeUnitFactory(AssetManager manager, UnitType type) {
        this(manager, type, type);
    }

    public MeleeUnitFactory(AssetManager manager, UnitType assetType, UnitType buildType) {
        super(manager, assetType, buildType);
    }

    @Override
    protected MeleeUnitAssets newAssets(AssetManager manager, UnitType type) {
        return new MeleeUnitAssets(manager, type);
    }

    @Override
    protected MeleeUnitBuilder newBuilder(MeleeUnitAssets assets, UnitType type) {
        return new MeleeUnitBuilder(assets, type);
    }
}