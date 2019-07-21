/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.critter;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.item.unit.UnitType;

/**
 * A reusable class for creating {@link Critter}s, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public abstract class CritterFactoryBase implements GameFactory<Critter>
{
    protected CritterAssets assets;
    protected CritterBuilder builder;

    public CritterFactoryBase(AssetManager manager, UnitType type) {
        this.assets = new CritterAssets(manager, type);
        this.builder = new CritterBuilder(assets);
    }

    @Override
    public void load(Identifier context) {
        assets.load();
    }

    @Override
    public void unload(Identifier context) {
        assets.unload();
    }
}