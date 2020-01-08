/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.projectile;

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
