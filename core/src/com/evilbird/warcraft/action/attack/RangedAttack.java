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
import com.evilbird.engine.common.lang.Destroyable;
import com.evilbird.engine.common.math.RandomGenerator;
import com.evilbird.engine.game.GameService;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.warcraft.item.projectile.Projectile;
import com.evilbird.warcraft.item.projectile.ProjectileType;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.combatant.RangedCombatant;

/**
 * Modifies the state of a {@link RangedCombatant} to attack a given item using
 * a projectile fired from distance.
 *
 * @author Blair Butterworth
 */
public class RangedAttack extends BasicAction
{
    private static final transient float PROJECTILE_SPEED = 500;
    private static final transient float PROJECTILE_RELOAD = 1.5f;

    private transient RangedCombatant combatant;
    private transient Projectile projectile;
    private transient Destroyable target;
    private transient Vector2 destination;

    private transient ItemFactory factory;
    private transient RandomGenerator random;

    private transient float flightTime;
    private transient float reloadTime;

    public RangedAttack() {
        this(GameService.getInstance().getItemFactory());
    }

    public RangedAttack(ItemFactory factory) {
        this.factory = factory;
        this.random = new RandomGenerator();
    }

    public static RangedAttack rangedAttack() {
        return new RangedAttack();
    }

    @Override
    public boolean act(float time) {
        if (! initialized()) {
            initialize();
        }
        if (! readyToFire()) {
            reduceTimeToFire(time);
            return false;
        }
        if (! projectileLaunched()) {
            fireProjectile();
            return false;
        }
        if (! projectileReachedTarget()) {
            moveProjectile(time);
            return false;
        }
        else {
            return hitWithProjectile();
        }
    }

    @Override
    public void reset() {
        super.reset();
        clear();
    }

    @Override
    public void restart() {
        super.restart();
        clear();
    }

    private void clear() {
        combatant = null;
        projectile = null;
        target = null;
        flightTime = 0;
        reloadTime = 0;
    }

    private boolean initialized() {
        return combatant != null && projectile != null && target != null;
    }

    private void initialize() {
        target = (Destroyable)getTarget();
        combatant = (RangedCombatant)getItem();
        projectile = (Projectile)combatant.getAssociatedItem();
        destination = target.getPosition();

        if (projectile == null) {
            projectile = newProjectile(combatant);
            combatant.setAssociatedItem(projectile);

            ItemGroup parent = combatant.getParent();
            parent.addItem(projectile);
        }
    }

    private Projectile newProjectile(RangedCombatant combatant) {
        ProjectileType type = combatant.getProjectileType();
        Projectile projectile = (Projectile)factory.newItem(type);
        projectile.setVisible(false);
        projectile.setPosition(combatant.getPosition());
        return projectile;
    }

    private boolean readyToFire() {
        return reloadTime == 0;
    }

    private void reduceTimeToFire(float time) {
        reloadTime = Math.max(reloadTime - time, 0);
    }

    private boolean projectileLaunched() {
        return projectile.getVisible();
    }

    private void fireProjectile() {
        flightTime = 0;
        destination = target.getPosition();
        projectile.setPosition(combatant.getPosition());
        projectile.setVisible(true);
        combatant.setAnimation(UnitAnimation.Attack);
        combatant.setSound(UnitSound.Attack);
    }

    private boolean projectileReachedTarget() {
        Vector2 position = projectile.getPosition();
        return position.equals(destination);
    }

    private void moveProjectile(float time) {
        Vector2 currentPosition = projectile.getPosition();
        Vector2 updatedPosition = getNextPosition(currentPosition, destination, time);
        projectile.setPosition(updatedPosition);
        flightTime += time;
    }

    private Vector2 getNextPosition(Vector2 position, Vector2 destination, float time) {
        Vector2 remaining = destination.cpy().sub(position);
        float remainingDistance = remaining.len();
        float incrementDistance = time * PROJECTILE_SPEED;

        if (remainingDistance > incrementDistance) {
            Vector2 direction = remaining.nor();
            Vector2 increment = direction.scl(incrementDistance);
            return position.cpy().add(increment);
        }
        return destination;
    }

    private boolean hitWithProjectile() {
        float newHealth = getNewHealth();
        target.setHealth(newHealth);
        combatant.setSound(UnitSound.Hit);
        projectile.setVisible(false);
        reloadTime = Math.max(PROJECTILE_RELOAD - flightTime, 0);
        return newHealth == 0;
    }

    private float getNewHealth() {
        int attackMin = combatant.getDamageMinimum();
        int attackMax = combatant.getDamageMaximum();
        int attack = random.nextInt(attackMin, attackMax);
        int defence = target.getDefence();
        int damage = Math.max(0, attack - defence);
        float health = target.getHealth();
        return Math.max(0, health - damage);
    }
}
