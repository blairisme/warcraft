/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.gatherer;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.item.unit.UnitType;

/**
 * A reusable class for creating {@link Gatherer}s, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public abstract class GathererFactoryBase implements GameFactory<Gatherer>
{
    protected UnitType type;
    protected AssetManager manager;
    protected GathererAssets assets;
    protected GathererBuilder builder;

    public GathererFactoryBase(AssetManager manager, UnitType type) {
        this.type = type;
        this.manager = manager;
    }

    @Override
    public void load(Identifier context) {
        this.assets = new GathererAssets(manager, type);
        this.builder = new GathererBuilder(assets);
        assets.load();
    }

    @Override
    public void unload(Identifier context) {
        assets.unload();
    }
}
