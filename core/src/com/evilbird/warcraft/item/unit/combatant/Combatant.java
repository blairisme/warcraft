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
public class Combatant extends Unit implements Movable
{
    private int level;
    private int damageMinimum;
    private int damageMaximum;
    private float speed;
    private float range;
    private float movementSpeed;
    private Collection<Identifier> movementCapability;

    public Combatant() {
        super();
    }

    public int getDamageMinimum() {
        return damageMinimum;
    }

    public int getDamageMaximum() {
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

    public float getRange() {
        return range;
    }

    public void setDamageMinimum(int damageMinimum) {
        this.damageMinimum = damageMinimum;
    }

    public void setDamageMaximum(int damageMaximum) {
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

    public void setRange(float range) {
        this.range = range;
    }
}

