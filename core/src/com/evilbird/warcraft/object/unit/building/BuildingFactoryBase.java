/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.building;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.object.common.production.ProductionTimes;
import com.evilbird.warcraft.object.unit.UnitType;
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
    protected ProductionTimes times;

    public BuildingFactoryBase(AssetManager manager, UnitType type) {
        this.type = type;
        this.manager = manager;
        this.times = new ProductionTimes();
    }

    @Override
    public void load(GameContext context) {
        assets = new BuildingAssets(manager, type, (WarcraftContext)context);
        builder = new BuildingBuilder(assets, times, type);
        assets.load();
    }

    @Override
    public void unload(GameContext context) {
        if (assets != null) {
            assets.unload();
        }
    }
}