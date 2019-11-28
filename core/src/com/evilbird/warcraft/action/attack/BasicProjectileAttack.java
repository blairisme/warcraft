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
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.object.common.capability.MovableObject;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import com.evilbird.warcraft.object.common.capability.RangedOffensiveObject;
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
public class BasicProjectileAttack extends BasicAction
{
    protected transient GameObjectFactory factory;
    protected transient WarcraftPreferences preferences;
    protected transient RangedOffensiveObject combatant;
    protected transient Projectile projectile;
    protected transient PerishableObject target;
    protected transient Vector2 destination;

    private transient float flightTime;
    private transient float reloadTime;

    @Inject
    public BasicProjectileAttack(GameObjectFactory objectFactory, WarcraftPreferences preferences) {
        this.factory = objectFactory;
        this.preferences = preferences;
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
            launchProjectile();
            return ActionIncomplete;
        }
        if (! projectileReachedTarget()) {
            repositionProjectile(time);
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

    protected void initialize() {
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

    protected void launchProjectile() {
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

    protected void repositionProjectile(float time) {
        Vector2 position = projectile.getPosition(Center);
        Vector2 remaining = destination.cpy().sub(position);
        repositionProjectile(position, remaining, time);
    }

    protected void repositionProjectile(Vector2 position, Vector2 remaining, float time) {
        float distance = remaining.len();
        float increment = time * projectile.getMovementSpeed();

        Vector2 newPosition = destination;
        if (distance > increment) {
            Vector2 direction = remaining.nor();
            Vector2 delta = direction.scl(increment);
            newPosition = position.cpy().add(delta);
        }
        projectile.setPosition(newPosition, Center);
        flightTime += time;
    }

    protected boolean hitWithProjectile() {
        resetAttacker();
        resetProjectile();
        damageTarget();
        return !target.isAlive();
    }

    protected void resetAttacker() {
        reloadTime = Math.max(combatant.getAttackSpeed() - flightTime, 0);
    }

    protected void resetProjectile() {
        projectile.setVisible(false);
    }

    private void damageTarget() {
        projectile.setSound(UnitSound.Hit, preferences.getEffectsVolume());
        target.setHealth(getDamagedHealth(combatant, target));
    }

    private void reorientTowardsTarget() {
        if (combatant instanceof MovableObject) {
            reorient((MovableObject)combatant, target, isShip(combatant));
        }
    }
}
