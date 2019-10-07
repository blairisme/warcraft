/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.projectile;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;

/**
 * Creates {@link Projectile} instances of a given {@link ProjectileType type}.
 *
 * @author Blair Butterworth
 */
public abstract class ProjectileFactoryBase implements GameFactory<Projectile>
{
    protected AssetManager manager;
    protected ProjectileAssets assets;
    protected ProjectileBuilder builder;
    protected ProjectileType type;

    public ProjectileFactoryBase(AssetManager manager, ProjectileType type) {
        this.manager = manager;
        this.type = type;
    }

    @Override
    public void load(GameContext identifier) {
        assets = new ProjectileAssets(manager);
        builder = new ProjectileBuilder(assets, type);
        assets.load();
    }

    @Override
    public void unload(GameContext context) {
        if (assets != null) {
            assets.unload();
        }
    }
}
