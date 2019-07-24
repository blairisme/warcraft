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
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.game.GameFactory;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * Creates {@link Projectile} instances of a given {@link ProjectileType type}.
 *
 * @author Blair Butterworth
 */
public class ProjectileFactory implements GameFactory<Projectile>
{
    private AssetManager manager;
    private InjectedPool<Projectile> pool;

    private ProjectileAssets assets;
    private ProjectileBuilder builder;

    @Inject
    public ProjectileFactory(Device device, InjectedPool<Projectile> pool) {
        this(device.getAssetStorage(), pool);
    }

    public ProjectileFactory(AssetManager manager, InjectedPool<Projectile> pool) {
        this.manager = manager;
        this.pool = pool;
    }

    @Override
    public void load(Identifier identifier) {
        assets = new ProjectileAssets(manager);
        builder = new ProjectileBuilder(assets, pool);
        assets.load();
    }

    @Override
    public void unload(Identifier context) {
        assets.unload();
    }

    @Override
    public Projectile get(Identifier identifier) {
        Validate.isInstanceOf(ProjectileType.class, identifier);
        return builder.build((ProjectileType)identifier);
    }
}
