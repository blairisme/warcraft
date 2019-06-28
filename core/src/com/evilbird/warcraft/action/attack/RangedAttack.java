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
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.common.lang.Destroyable;
import com.evilbird.engine.game.GameService;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.data.player.PlayerUpgrade;
import com.evilbird.warcraft.item.projectile.Projectile;
import com.evilbird.warcraft.item.projectile.ProjectileType;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.combatant.RangedCombatant;

import static com.evilbird.warcraft.action.attack.AttackDamage.getDamagedHealth;
import static com.evilbird.warcraft.item.common.query.UnitOperations.getPlayer;
import static com.evilbird.warcraft.item.data.player.PlayerUpgrade.AdvancedArrowDamage;
import static com.evilbird.warcraft.item.data.player.PlayerUpgrade.AdvancedAxeDamage;
import static com.evilbird.warcraft.item.data.player.PlayerUpgrade.AdvancedCannonDamage;
import static com.evilbird.warcraft.item.data.player.PlayerUpgrade.ArrowDamage;
import static com.evilbird.warcraft.item.data.player.PlayerUpgrade.AxeDamage;
import static com.evilbird.warcraft.item.data.player.PlayerUpgrade.CannonDamage;

/**
 * Modifies the state of a {@link RangedCombatant} to attack a given item using
 * a projectile fired from distance.
 *
 * @author Blair Butterworth
 */
public class RangedAttack extends BasicAction
{
    private transient Player player;
    private transient RangedCombatant combatant;
    private transient Projectile projectile;
    private transient Destroyable target;
    private transient Vector2 destination;
    private transient ItemFactory factory;

    private transient float flightTime;
    private transient float reloadTime;

    public RangedAttack() {
        this(GameService.getInstance().getItemFactory());
    }

    public RangedAttack(ItemFactory factory) {
        this.factory = factory;
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
        if (initialized()) {
            ItemGroup parent = combatant.getParent();
            parent.removeItem(projectile);
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

        combatant = (RangedCombatant)getItem();
        projectile = (Projectile)combatant.getAssociatedItem();
        player = getPlayer(combatant);

        target = (Destroyable)getTarget();
        destination = target.getPosition(Alignment.Center);

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
        projectile.setPosition(combatant.getPosition(Alignment.Center));
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
        destination = target.getPosition(Alignment.Center);

        projectile.setPosition(combatant.getPosition(Alignment.Center));
        projectile.setVisible(true);
        projectile.setAnimation(UnitAnimation.Idle);

        combatant.resetAnimation();
        combatant.setAnimation(UnitAnimation.Attack);
        combatant.setSound(UnitSound.Attack);
    }

    private boolean projectileReachedTarget() {
        Vector2 position = projectile.getPosition(Alignment.Center);
        return position.equals(destination);
    }

    private void moveProjectile(float time) {
        Vector2 currentPosition = projectile.getPosition(Alignment.Center);
        Vector2 updatedPosition = getNextPosition(currentPosition, destination, time);
        projectile.setPosition(updatedPosition, Alignment.Center);
        flightTime += time;
    }

    private Vector2 getNextPosition(Vector2 position, Vector2 destination, float time) {
        Vector2 remaining = destination.cpy().sub(position);
        float remainingDistance = remaining.len();
        float incrementDistance = time * projectile.getSpeed();

        if (remainingDistance > incrementDistance) {
            Vector2 direction = remaining.nor();
            Vector2 increment = direction.scl(incrementDistance);
            return position.cpy().add(increment);
        }
        return destination;
    }

    private boolean hitWithProjectile() {
        float newHealth = getDamagedHealth(combatant, target, getAttackUpgrade());
        target.setHealth(newHealth);
        combatant.setSound(UnitSound.Hit);
        projectile.setVisible(false);
        reloadTime = Math.max(combatant.getAttackSpeed() - flightTime, 0);
        return newHealth == 0;
    }

    private int getAttackUpgrade() {
        switch ((ProjectileType)projectile.getType()) {
            case Arrow: return getAttackUpgrade(ArrowDamage, AdvancedArrowDamage);
            case Axe: return getAttackUpgrade(AxeDamage, AdvancedAxeDamage);
            case Cannon: return getAttackUpgrade(CannonDamage, AdvancedCannonDamage);
            default: return 0;
        }
    }

    private int getAttackUpgrade(PlayerUpgrade basicUpgrade, PlayerUpgrade advancedUpgrade) {
        int upgrade = 0;
        upgrade += player.hasUpgrade(basicUpgrade) ? 2 : 0;
        upgrade += player.hasUpgrade(advancedUpgrade) ? 2 : 0;
        return upgrade;
    }
}
