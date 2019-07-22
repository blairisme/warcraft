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
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.assets.AssetBundle;

import static com.evilbird.warcraft.item.projectile.ProjectileType.Arrow;
import static com.evilbird.warcraft.item.projectile.ProjectileType.Axe;
import static com.evilbird.warcraft.item.projectile.ProjectileType.Cannon;

/**
 * Provides access to the assets that are required to display a
 * {@link Projectile}, as well as any sound effects used by it.
 *
 * @author Blair Butterworth
 */
public class ProjectileAssets extends AssetBundle
{
    public ProjectileAssets(AssetManager assetManager) {
        super(assetManager);
        register(Arrow, "data/textures/common/projectile/arrow.png");
        register(Axe, "data/textures/common/projectile/axe.png");
        register(Cannon, "data/textures/common/projectile/cannon.png");
    }

    public Texture getTexture(ProjectileType type) {
        return super.getTexture(type);
    }
}
