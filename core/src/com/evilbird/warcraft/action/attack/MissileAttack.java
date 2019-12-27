/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.framework.RemoveAction;
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

/**
 * A {@link ProjectileAttack} specialization that supplements the default
 * projectile attack behaviour, displaying explosions as projectiles stroke
 * their target.
 *
 * @author Blair Butterworth
 */
public class MissileAttack extends BasicProjectileAttack
{
    private transient Provider<RemoveAction> removeProvider;
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
        Provider<RemoveAction> removeProvider)
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
    public boolean act(float time) {
        interval.advance(time);
        return super.act(time);
    }

    @Override
    protected boolean launchProjectile() {
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
    protected boolean hitWithProjectile() {
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
        return target.isDead();
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

        RemoveAction removal = removeProvider.get();
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
