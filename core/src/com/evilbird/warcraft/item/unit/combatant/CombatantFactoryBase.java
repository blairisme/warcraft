/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.item.unit.UnitType;

/**
 * A reusable class for creating {@link Combatant}s, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public abstract class CombatantFactoryBase implements GameFactory<Combatant>
{
    protected UnitType type;
    protected AssetManager manager;
    protected CombatantAssets assets;
    protected CombatantBuilder builder;

    public CombatantFactoryBase(AssetManager manager, UnitType type) {
        this.manager = manager;
        this.type = type;
    }

    @Override
    public void load(Identifier context) {
        assets = new CombatantAssets(manager, type);
        builder = new CombatantBuilder(assets);
        assets.load();
    }

    @Override
    public void unload(Identifier context) {
        assets.unload();
    }
}