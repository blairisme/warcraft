/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.spellcaster;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.CombatantAssets;
import com.evilbird.warcraft.object.unit.combatant.CombatantFactory;

/**
 * A reusable class for creating spell casters, loading the necessary assets and
 * defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public abstract class SpellCasterFactory extends CombatantFactory<CombatantAssets, SpellCasterBuilder, SpellCaster>
{
    public SpellCasterFactory(AssetManager manager, UnitType type) {
        this (manager, type, type);
    }

    public SpellCasterFactory(AssetManager manager, UnitType assetType, UnitType buildType) {
        super(manager, assetType, buildType);
    }

    @Override
    protected CombatantAssets newAssets(AssetManager manager, UnitType type) {
        return new CombatantAssets(manager, type);
    }

    @Override
    protected SpellCasterBuilder newBuilder(CombatantAssets assets, UnitType type) {
        return new SpellCasterBuilder(assets, type);
    }
}