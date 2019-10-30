/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.conjured;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.item.unit.UnitType;

/**
 * @author Blair Butterworth
 */
public abstract class ConjuredFactoryBase implements GameFactory<ConjuredObject>
{
    protected UnitType type;
    protected AssetManager manager;
    protected ConjuredAssets assets;
    protected ConjuredBuilder builder;

    public ConjuredFactoryBase(AssetManager manager, UnitType type) {
        this.type = type;
        this.manager = manager;
    }

    @Override
    public void load(GameContext context) {
        assets = new ConjuredAssets(manager);
        builder = new ConjuredBuilder(assets, type);
        assets.load();
    }

    @Override
    public void unload(GameContext context) {
        if (assets != null) {
            assets.unload();
        }
    }
}