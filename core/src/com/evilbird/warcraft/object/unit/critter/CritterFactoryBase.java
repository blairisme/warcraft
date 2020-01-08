/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.critter;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.object.unit.UnitType;

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
    public void load(GameContext context) {
        assets.load();
    }

    @Override
    public void unload(GameContext context) {
        assets.unload();
    }
}