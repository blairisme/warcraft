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
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.engine.object.GameObjectGroup;
import com.evilbird.warcraft.action.common.remove.RemoveAction;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.object.common.capability.MovableObject;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import com.evilbird.warcraft.object.common.capability.RangedOffensiveObject;
import com.evilbird.warcraft.object.effect.Effect;
import com.evilbird.warcraft.object.projectile.Projectile;
import com.evilbird.warcraft.object.unit.UnitAnimation;
import com.evilbird.warcraft.object.unit.UnitSound;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;
import static com.evilbird.engine.common.lang.Alignment.Center;
import static com.evilbird.warcraft.action.attack.AttackDamage.getDamagedHealth;
import static com.evilbird.warcraft.object.common.query.UnitOperations.isShip;
import static com.evilbird.warcraft.object.common.query.UnitOperations.reorient;

/**
 * Modifies the state of a {@link RangedOffensiveObject} to attack a given item
 * using a projectile fired from distance.
 *
 * @author Blair Butterworth
 */
public class ProjectileAttack extends BasicAction
{
    private transient GameObjectFactory factory;
    private transient WarcraftPreferences preferences;
    private transient RangedOffensiveObject combatant;
    private transient Projectile projectile;
    private transient PerishableObject target;
    private transient Vector2 destination;
    private transient RemoveAction remove;

    private transient float flightTime;
    private transient float reloadTime;

    @Inject
    public ProjectileAttack(GameObjectFactory factory, RemoveAction remove, WarcraftPreferences preferences) {
        this.factory = factory;
        this.preferences = preferences;
        this.remove = remove;
    }

    @Override
    public boolean act(float time) {
        if (! initialized()) {
            initialize();
        }
        if (! readyToFire()) {
            reduceTimeToFire(time);
            return ActionIncomplete;
        }
        if (! projectileLaunched()) {
            fireProjectile();
            return ActionIncomplete;
        }
        if (! projectileReachedTarget()) {
            moveProjectile(time);
            return ActionIncomplete;
        }
        else {
            return hitWithProjectile();
        }
    }

    @Override
    public void reset() {
        super.reset();
        if (initialized()) {
            projectile.setVisible(false);
            combatant = null;
            projectile = null;
            target = null;
        }
    }

    @Override
    public void restart() {
        super.restart();
        if (initialized()) {
            projectile.setVisible(false);
            flightTime = 0;
            reloadTime = 0;
        }
    }

    private boolean initialized() {
        return combatant != null && projectile != null && target != null;
    }

    private void initialize() {
        flightTime = 0;
        reloadTime = 0;

        target = (PerishableObject)getTarget();
        destination = target.getPosition(Center);

        combatant = (RangedOffensiveObject) getSubject();
        reorientTowardsTarget();

        projectile = getProjectile(combatant);
        projectile.setVisible(false);
        projectile.setPosition(combatant.getPosition(Center));
    }

    private Projectile getProjectile(RangedOffensiveObject combatant) {
        Projectile result = combatant.getProjectile();
        if (result == null) {
            result = (Projectile)factory.get(combatant.getProjectileType());
            combatant.setProjectile(result);

            GameObjectGroup parent = combatant.getParent();
            parent.addObject(result);
        }
        return result;
    }

    protected boolean readyToFire() {
        return reloadTime == 0;
    }

    protected void reduceTimeToFire(float time) {
        reloadTime = Math.max(reloadTime - time, 0);
    }

    protected boolean projectileLaunched() {
        return projectile.getVisible();
    }

    protected void fireProjectile() {
        flightTime = 0;
        destination = target.getPosition(Center);

        projectile.setVisible(true);
        projectile.setPosition(combatant.getPosition(Center));

        combatant.setAnimation(UnitAnimation.Attack);
        combatant.setSound(UnitSound.Attack, preferences.getEffectsVolume());

        reorientTowardsTarget();
        reorient(projectile, target, false);
    }

    protected boolean projectileReachedTarget() {
        Vector2 position = projectile.getPosition(Center);
        return position.equals(destination);
    }

    protected void moveProjectile(float time) {
        Vector2 currentPosition = projectile.getPosition(Center);
        Vector2 updatedPosition = getNextPosition(currentPosition, destination, time);
        projectile.setPosition(updatedPosition, Center);
        flightTime += time;
    }

    private Vector2 getNextPosition(Vector2 position, Vector2 destination, float time) {
        Vector2 remaining = destination.cpy().sub(position);
        float remainingDistance = remaining.len();
        float incrementDistance = time * projectile.getMovementSpeed();

        if (remainingDistance > incrementDistance) {
            Vector2 direction = remaining.nor();
            Vector2 increment = direction.scl(incrementDistance);
            return position.cpy().add(increment);
        }
        return destination;
    }

    protected boolean hitWithProjectile() {
        attackerHit();
        projectileHit();
        return targetHit();
    }

    protected void attackerHit() {
        combatant.setSound(UnitSound.Hit, preferences.getEffectsVolume());
        reloadTime = Math.max(combatant.getAttackSpeed() - flightTime, 0);
    }

    protected void projectileHit() {
        projectile.setVisible(false);

        if (projectile.isExplodingProjectile()) {
            Effect explosion = (Effect)factory.get(projectile.getExplosionEffect());
            explosion.setVisible(true);
            explosion.setPosition(projectile.getPosition());

            remove.setSubject(explosion);
            explosion.addAction(remove, explosion.getDuration());

            GameObjectGroup container = projectile.getParent();
            container.addObject(explosion);
        }
    }

    protected boolean targetHit() {
        float newHealth = getDamagedHealth(combatant, target);
        target.setHealth(newHealth);
        return newHealth == 0;
    }

    private void reorientTowardsTarget() {
        if (combatant instanceof MovableObject) {
            reorient((MovableObject)combatant, target, isShip(combatant));
        }
    }
}
