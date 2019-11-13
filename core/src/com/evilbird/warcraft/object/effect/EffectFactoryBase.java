/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.effect;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;

/**
 * A base class for the creation of effect game objects.
 *
 * @author Blair Butterworth
 */
public abstract class EffectFactoryBase implements GameFactory<Effect>
{
    protected AssetManager manager;
    protected EffectAssets assets;
    protected EffectType type;
    protected EffectBuilder builder;

    public EffectFactoryBase(AssetManager manager, EffectType type) {
        this.manager = manager;
        this.type = type;
    }

    @Override
    public void load(GameContext context) {
        assets = new EffectAssets(manager);
        assets.load();
        builder = new EffectBuilder(assets, type);
    }

    @Override
    public void unload(GameContext context) {
        if (assets != null) {
            assets.unload();
        }
    }
}
