/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.warcraft.item.common.capability.Movable;
import com.evilbird.warcraft.item.unit.Unit;

import java.util.Arrays;
import java.util.Collection;

/**
 * Instances of this class define a combatant: a {@link Unit} specialization
 * that can move and attack other Units.
 *
 * @author Blair Butterworth
 */
//TODO: Add ability to define movement speed
public class Combatant extends Unit implements Movable
{
    private float armour;
    private float damageMinimum;
    private float damageMaximum;
    private int level;
    private float speed;
    private float sight;
    private float range;
    private float movementSpeed;
    private Collection<Identifier> movementCapability;

    public float getArmour() {
        return armour;
    }

    public float getDamageMinimum() {
        return damageMinimum;
    }

    public float getDamageMaximum() {
        return damageMaximum;
    }

    public int getLevel() {
        return level;
    }

    public float getSpeed() {
        return speed;
    }

    @Override
    public float getMovementSpeed() {
        return movementSpeed;
    }

    @Override
    public Vector2 getMovementDisplacement() {
        return getSize();
    }

    public Collection<Identifier> getMovementCapability() {
        return movementCapability;
    }

    public float getSight() {
        return sight;
    }

    public float getRange() {
        return range;
    }

    public void setArmour(float armour) {
        this.armour = armour;
    }

    public void setDamageMinimum(float damageMinimum) {
        this.damageMinimum = damageMinimum;
    }

    public void setDamageMaximum(float damageMaximum) {
        this.damageMaximum = damageMaximum;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public void setMovementCapability(Identifier movementCapability) {
        this.movementCapability = Arrays.asList(movementCapability);
    }

    public void setMovementCapability(Collection<Identifier> movementCapability) {
        this.movementCapability = movementCapability;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setSight(float sight) {
        this.sight = sight;
    }

    public void setRange(float range) {
        this.range = range;
    }
}

