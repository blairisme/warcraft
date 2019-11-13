/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.object.unit.UnitType;

/**
 * A reusable class for creating {@link Combatant}s, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public abstract class CombatantFactoryBase implements GameFactory<Combatant>
{
    protected AssetManager manager;
    protected CombatantAssets assets;
    protected CombatantBuilder builder;
    protected UnitType assetType;
    protected UnitType buildType;

    public CombatantFactoryBase(AssetManager manager, UnitType type) {
        this(manager, type, type);
    }

    public CombatantFactoryBase(AssetManager manager, UnitType assetType, UnitType buildType) {
        this.manager = manager;
        this.assetType = assetType;
        this.buildType = buildType;
    }

    @Override
    public void load(GameContext context) {
        assets = new CombatantAssets(manager, assetType);
        builder = new CombatantBuilder(assets, buildType);
        assets.load();
    }

    @Override
    public void unload(GameContext context) {
        if (assets != null) {
            assets.unload();
        }
    }
}