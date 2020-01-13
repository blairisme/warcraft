/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.building;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;
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

    public BuildingFactoryBase(AssetManager manager, UnitType type) {
        this.type = type;
        this.manager = manager;
    }

    @Override
    public void load(GameContext context) {
        assets = new BuildingAssets(manager, type, (WarcraftContext)context);
        builder = new BuildingBuilder(assets, type);
        assets.load();
    }

    @Override
    public void unload(GameContext context) {
        if (assets != null) {
            assets.unload();
        }
    }
}