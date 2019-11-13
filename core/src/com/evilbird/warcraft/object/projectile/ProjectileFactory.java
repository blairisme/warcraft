/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.projectile;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.warcraft.object.projectile.projectiles.ArrowFactory;
import com.evilbird.warcraft.object.projectile.projectiles.AxeFactory;
import com.evilbird.warcraft.object.projectile.projectiles.BoltFactory;
import com.evilbird.warcraft.object.projectile.projectiles.CannonballFactory;
import com.evilbird.warcraft.object.projectile.projectiles.DaemonFireFactory;
import com.evilbird.warcraft.object.projectile.projectiles.FireballFactory;
import com.evilbird.warcraft.object.projectile.projectiles.FlamingCannonballFactory;
import com.evilbird.warcraft.object.projectile.projectiles.FlamingRockFactory;
import com.evilbird.warcraft.object.projectile.projectiles.GryphonHammerFactory;
import com.evilbird.warcraft.object.projectile.projectiles.LightningFactory;
import com.evilbird.warcraft.object.projectile.projectiles.TorpedoFactory;
import com.evilbird.warcraft.object.projectile.projectiles.TouchOfDeathFactory;

import javax.inject.Inject;

import static com.evilbird.warcraft.object.projectile.ProjectileType.Arrow;
import static com.evilbird.warcraft.object.projectile.ProjectileType.Axe;
import static com.evilbird.warcraft.object.projectile.ProjectileType.Bolt;
import static com.evilbird.warcraft.object.projectile.ProjectileType.Cannonball;
import static com.evilbird.warcraft.object.projectile.ProjectileType.DaemonFire;
import static com.evilbird.warcraft.object.projectile.ProjectileType.Fireball;
import static com.evilbird.warcraft.object.projectile.ProjectileType.FlamingCannonball;
import static com.evilbird.warcraft.object.projectile.ProjectileType.FlamingRock;
import static com.evilbird.warcraft.object.projectile.ProjectileType.GryphonHammer;
import static com.evilbird.warcraft.object.projectile.ProjectileType.Lightning;
import static com.evilbird.warcraft.object.projectile.ProjectileType.Torpedo;
import static com.evilbird.warcraft.object.projectile.ProjectileType.TouchOfDeath;

/**
 * A factory for the creation of {@link Projectile Projectiles}, loading the
 * necessary assets and generating new game objects.
 *
 * @author Blair Butterworth
 */
public class ProjectileFactory extends GameFactorySet<Projectile>
{
    @Inject
    public ProjectileFactory(
        ArrowFactory arrowFactory,
        AxeFactory axeFactory,
        BoltFactory boltFactory,
        CannonballFactory cannonballFactory,
        DaemonFireFactory daemonFireFactory,
        FireballFactory fireballFactory,
        FlamingCannonballFactory flamingCannonballFactory,
        FlamingRockFactory flamingRockFactory,
        GryphonHammerFactory gryphonHammerFactory,
        LightningFactory lightningFactory,
        TorpedoFactory torpedoFactory,
        TouchOfDeathFactory touchOfDeathFactory)
    {
        addProvider(Arrow, arrowFactory);
        addProvider(Axe, axeFactory);
        addProvider(Bolt, boltFactory);
        addProvider(Cannonball, cannonballFactory);
        addProvider(DaemonFire, daemonFireFactory);
        addProvider(Fireball, fireballFactory);
        addProvider(FlamingCannonball, flamingCannonballFactory);
        addProvider(FlamingRock, flamingRockFactory);
        addProvider(GryphonHammer, gryphonHammerFactory);
        addProvider(Lightning, lightningFactory);
        addProvider(Torpedo, torpedoFactory);
        addProvider(TouchOfDeath, touchOfDeathFactory);
    }
}
