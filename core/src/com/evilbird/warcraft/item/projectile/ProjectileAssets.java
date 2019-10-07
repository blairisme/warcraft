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
import static com.evilbird.warcraft.item.projectile.ProjectileType.Bolt;
import static com.evilbird.warcraft.item.projectile.ProjectileType.Cannonball;
import static com.evilbird.warcraft.item.projectile.ProjectileType.DaemonFire;
import static com.evilbird.warcraft.item.projectile.ProjectileType.Fireball;
import static com.evilbird.warcraft.item.projectile.ProjectileType.FlamingCannonball;
import static com.evilbird.warcraft.item.projectile.ProjectileType.FlamingRock;
import static com.evilbird.warcraft.item.projectile.ProjectileType.GryphonHammer;
import static com.evilbird.warcraft.item.projectile.ProjectileType.Lightning;
import static com.evilbird.warcraft.item.projectile.ProjectileType.Torpedo;
import static com.evilbird.warcraft.item.projectile.ProjectileType.TouchOfDeath;

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
        register(Bolt, "data/textures/common/projectile/bolt.png");
        register(Cannonball, "data/textures/common/projectile/cannonball.png");
        register(DaemonFire, "data/textures/common/projectile/daemon_fire.png");
        register(Fireball, "data/textures/common/projectile/fireball.png");
        register(FlamingCannonball, "data/textures/common/projectile/flaming_cannonball.png");
        register(FlamingRock, "data/textures/common/projectile/flaming_rock.png");
        register(GryphonHammer, "data/textures/common/projectile/gryphon_hammer.png");
        register(Lightning, "data/textures/common/projectile/lightning.png");
        register(Torpedo, "data/textures/common/projectile/torpedo.png");
        register(TouchOfDeath, "data/textures/common/projectile/touch_of_death.png");
    }

    public Texture getTexture(ProjectileType type) {
        return super.getTexture(type);
    }
}
