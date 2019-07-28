/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.state.WarcraftContext;

/**
 * A reusable class for creating {@link Building}s, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public abstract class BuildingFactoryBase implements GameFactory<Building>
{
    protected UnitType type;
    protected AssetManager manager;
    protected BuildingAssets assets;
    protected BuildingBuilder builder;

    public BuildingFactoryBase(AssetManager manager, UnitType type) {
        this.type = type;
        this.manager = manager;
    }

    @Override
    public void load(Identifier context) {
        assets = new BuildingAssets(manager, type, (WarcraftContext)context);
        builder = new BuildingBuilder(assets);
        assets.load();
    }

    @Override
    public void unload(Identifier context) {
        assets.unload();
    }
}