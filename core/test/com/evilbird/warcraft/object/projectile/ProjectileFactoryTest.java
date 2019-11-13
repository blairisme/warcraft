/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.projectile;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.game.GameContext;
import com.evilbird.test.testcase.GameFactoryTestCase;
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
import com.evilbird.warcraft.state.WarcraftContext;
import org.junit.Ignore;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftFaction.Orc;
import static com.evilbird.warcraft.common.WarcraftSeason.Summer;
import static com.evilbird.warcraft.common.WarcraftSeason.Swamp;
import static com.evilbird.warcraft.common.WarcraftSeason.Winter;

/**
 * Instances of this unit test validate logic in the {@link ProjectileFactory}
 * class.
 *
 * @author Blair Butterworth
 */
@Ignore
public class ProjectileFactoryTest extends GameFactoryTestCase<ProjectileFactory>
{
    @Override
    protected ProjectileFactory newFactory(DeviceDisplay display, AssetManager assets) {
        ArrowFactory arrowFactory = new ArrowFactory(device);
        AxeFactory axeFactory = new AxeFactory(device);
        BoltFactory boltFactory = new BoltFactory(device);
        CannonballFactory cannonballFactory = new CannonballFactory(device);
        DaemonFireFactory daemonFireFactory = new DaemonFireFactory(device);
        FireballFactory fireballFactory = new FireballFactory(device);
        FlamingCannonballFactory flamingCannonballFactory = new FlamingCannonballFactory(device);
        FlamingRockFactory flamingRockFactory = new FlamingRockFactory(device);
        GryphonHammerFactory gryphonHammerFactory = new GryphonHammerFactory(device);
        LightningFactory lightningFactory = new LightningFactory(device);
        TorpedoFactory torpedoFactory = new TorpedoFactory(device);
        TouchOfDeathFactory touchOfDeathFactory = new TouchOfDeathFactory(device);
        return new ProjectileFactory(
                arrowFactory, axeFactory, boltFactory, cannonballFactory, daemonFireFactory,
                fireballFactory, flamingCannonballFactory, flamingRockFactory, gryphonHammerFactory,
                lightningFactory, torpedoFactory, touchOfDeathFactory);
    }

    @Override
    protected Collection<GameContext> getLoadContexts() {
        return Arrays.asList(
                new WarcraftContext(Human, Summer),
                new WarcraftContext(Human, Swamp),
                new WarcraftContext(Human, Winter),
                new WarcraftContext(Orc, Summer),
                new WarcraftContext(Orc, Swamp),
                new WarcraftContext(Orc, Winter));
    }

    @Override
    protected Collection<Identifier> getProductIdentifiers() {
        return Arrays.asList(ProjectileType.values());
    }

    @Override
    protected Map<String, Object> getProductProperties() {
        return Collections.emptyMap();
    }
}