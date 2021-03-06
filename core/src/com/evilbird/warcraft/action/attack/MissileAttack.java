/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.attack;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.ActionResult;
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.engine.object.GameObjectGroup;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import com.evilbird.warcraft.object.effect.Effect;
import com.evilbird.warcraft.object.projectile.ExplosivePattern;
import com.evilbird.warcraft.object.projectile.ExplosiveProjectile;
import com.evilbird.warcraft.object.unit.UnitSound;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.Collection;

import static com.badlogic.gdx.math.Vector2.Zero;
import static com.evilbird.engine.action.ActionResult.Complete;
import static com.evilbird.engine.action.ActionResult.Incomplete;

/**
 * A {@link ProjectileAttack} specialization that supplements the default
 * projectile attack behaviour, displaying explosions as projectiles stroke
 * their target.
 *
 * @author Blair Butterworth
 */
public class MissileAttack extends BasicProjectileAttack
{
    private transient Provider<MissileRemoval> removeProvider;
    private transient ExplosiveProjectile missile;
    private transient GameTimer interval;
    private transient Vector2 direction;
    private transient Vector2 offset;
    private transient int explosions;
    private transient int spacing;

    @Inject
    public MissileAttack(
        AttackEvents events,
        AttackDamage damage,
        GameObjectFactory objects,
        WarcraftPreferences preferences,
        Provider<MissileRemoval> removeProvider)
    {
        super(events, damage, objects, preferences);
        this.removeProvider = removeProvider;
        this.interval = new GameTimer(0);
        this.direction = new Vector2();
        this.offset = new Vector2();
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.missile = (ExplosiveProjectile)projectile;
        this.interval = new GameTimer(missile.getExplosiveInterval(), missile.getExplosiveInterval());
        this.spacing = missile.getExplosivePattern() == ExplosivePattern.LinearSequence
            ? missile.getExplosiveRange() / missile.getExplosiveCount()
            : 0;
    }

    @Override
    public ActionResult act(float time) {
        interval.advance(time);
        return super.act(time);
    }

    @Override
    protected ActionResult launchProjectile() {
        this.explosions = 0;
        return super.launchProjectile();
    }

    @Override
    protected void repositionProjectile(Vector2 position, Vector2 remaining, float time) {
        super.repositionProjectile(position, remaining, time);
        this.direction.set(remaining);
        this.direction.nor();
    }

    @Override
    protected ActionResult hitWithProjectile() {
        int explosionTotal = missile.getExplosiveCount();
        if (explosions == explosionTotal) {
            resetAttacker();
            resetProjectile();
        }
        else if (interval.complete()) {
            interval.reset();
            createExplosion();
            explosions++;
        }
        return target.isDead() ? Complete : Incomplete;
    }

    private void createExplosion() {
        offset.set(direction);
        offset.scl(explosions * spacing);

        Vector2 location = missile.getPosition();
        location.add(offset);

        createExplosion(location);
        damageOccupants(location);
    }

    private void createExplosion(Vector2 location) {
        Effect explosion = (Effect)objects.get(missile.getExplosiveEffect());
        explosion.setPosition(location);

        GameObjectGroup container = missile.getParent();
        container.addObject(explosion);

        MissileRemoval removal = removeProvider.get();
        removal.setSubject(explosion);
        explosion.addAction(removal, explosion.getDuration());

        projectile.setSound(UnitSound.Hit);
    }

    private void damageOccupants(Vector2 location) {
        GameObjectContainer container = missile.getRoot();
        GameObjectGraph graph = container.getSpatialGraph();
        for (GameObjectNode node: graph.getNodes(location, Zero, missile.getExplosiveRadius())) {
            damageOccupants(node.getOccupants());
        }
    }

    private void damageOccupants(Collection<GameObject> targets) {
        for (GameObject target: targets) {
            damageOccupant(target);
        }
    }

    private void damageOccupant(GameObject target) {
        if (target instanceof PerishableObject) {
            damage.apply(attacker, (PerishableObject)target);
            events.attack(attacker, target);
        }
    }
}
