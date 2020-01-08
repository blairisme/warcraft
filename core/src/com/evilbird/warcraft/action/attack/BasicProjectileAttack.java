/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.attack;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.ActionException;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.engine.object.GameObjectGroup;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import com.evilbird.warcraft.object.common.capability.RangedOffensiveObject;
import com.evilbird.warcraft.object.projectile.Projectile;
import com.evilbird.warcraft.object.unit.UnitAnimation;
import com.evilbird.warcraft.object.unit.UnitSound;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;
import static com.evilbird.engine.common.lang.Alignment.Center;
import static com.evilbird.warcraft.object.common.query.UnitOperations.inRange;
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
    protected transient AttackDamage damage;
    protected transient AttackEvents events;
    protected transient GameObjectFactory objects;
    protected transient WarcraftPreferences preferences;

    protected transient RangedOffensiveObject attacker;
    protected transient Projectile projectile;
    protected transient PerishableObject target;
    protected transient Vector2 destination;

    @Inject
    public BasicProjectileAttack(
        AttackEvents events,
        AttackDamage damage,
        GameObjectFactory objects,
        WarcraftPreferences preferences)
    {
        this.damage = damage;
        this.events = events;
        this.objects = objects;
        this.preferences = preferences;
    }

    @Override
    public boolean act(float time) {
        if (! initialized()) {
            initialize();
        }
        if (! operationValid()) {
            return operationFailed();
        }
        if (! readyToFire()) {
            return reduceTimeToFire(time);
        }
        if (! projectileLaunched()) {
            return launchProjectile();
        }
        if (! projectileReachedTarget()) {
            return repositionProjectile(time);
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
            attacker = null;
            projectile = null;
            target = null;
        }
    }

    @Override
    public void restart() {
        super.restart();
        if (initialized()) {
            projectile.setVisible(false);
        }
    }

    private boolean initialized() {
        return attacker != null && projectile != null && target != null;
    }

    protected void initialize() {
        target = (PerishableObject)getTarget();
        destination = target.getPosition(Center);

        attacker = (RangedOffensiveObject)getSubject();
        reorient(attacker, target, isShip(attacker));

        projectile = getProjectile(attacker);
        projectile.setVisible(false);
        projectile.setPosition(attacker.getPosition(Center));

        events.attackStarted(attacker, target);
    }

    private Projectile getProjectile(RangedOffensiveObject combatant) {
        Projectile result = combatant.getProjectile();
        if (result == null) {
            result = (Projectile) objects.get(combatant.getProjectileType());
            combatant.setProjectile(result);

            GameObjectGroup parent = combatant.getParent();
            parent.addObject(result);
        }
        return result;
    }

    private boolean operationValid() {
        return attacker.isAlive() && target.isAlive() && inRange(attacker, target);
    }

    private boolean operationFailed() {
        events.attackFailed(attacker, target);
        setError(new ActionException("Attack Failed"));
        return ActionComplete;
    }

    protected boolean readyToFire() {
        return attacker.getAttackTime() == 0;
    }

    protected boolean reduceTimeToFire(float time) {
        attacker.setAttackTime(Math.max(attacker.getAttackTime() - time, 0));
        return ActionIncomplete;
    }

    protected boolean projectileLaunched() {
        return projectile.getVisible();
    }

    protected boolean launchProjectile() {
        destination = target.getPosition(Center);

        projectile.setVisible(true);
        projectile.setPosition(attacker.getPosition(Center));

        attacker.setAnimation(UnitAnimation.Attack);
        attacker.setSound(UnitSound.Attack);

        reorient(attacker, target, isShip(attacker));
        reorient(projectile, target, false);

        return ActionIncomplete;
    }

    protected boolean projectileReachedTarget() {
        Vector2 position = projectile.getPosition(Center);
        return position.equals(destination);
    }

    protected boolean repositionProjectile(float time) {
        Vector2 position = projectile.getPosition(Center);
        Vector2 remaining = destination.cpy().sub(position);
        repositionProjectile(position, remaining, time);
        return ActionIncomplete;
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
    }

    protected boolean hitWithProjectile() {
        resetAttacker();
        resetProjectile();
        return damageTarget();
    }

    protected void resetAttacker() {
        attacker.setAttackTime(Math.max(attacker.getAttackSpeed(), 0));
    }

    protected void resetProjectile() {
        projectile.setVisible(false);
    }

    private boolean damageTarget() {
        damage.apply(attacker, target);
        return target.isDead();
    }
}
