/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.item.specialized.animated.AnimatedItem;
import com.evilbird.warcraft.item.common.capability.Destroyable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.inject.Inject;

/**
 * Instances of this represent a game object that the user can control and
 * interact with.
 *
 * @author Blair Butterworth
 */
public class Unit extends AnimatedItem implements Destroyable
{
    private String name;
    private int sight;
    private int defence;
    private float health;
    private float healthMaximum;
    private transient Drawable icon;

    @Inject
    public Unit() {
        name = "Unknown";
        icon = null;
        sight = 0;
        defence = 0;
        health = 0;
        healthMaximum = 0;
    }

    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public int getDefence() {
        return defence;
    }

    @Override
    public float getHealth() {
        return health;
    }

    public float getHealthMaximum() {
        return healthMaximum;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public int getSight() {
        return sight;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public void setHealthMaximum(float healthMaximum) {
        this.healthMaximum = healthMaximum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSight(int sight) {
        this.sight = sight;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("name", name)
            .append("sight", sight)
            .append("defence", defence)
            .append("health", health)
            .append("healthMaximum", healthMaximum)
            .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (obj.getClass() != getClass()) return false;

        Unit unit = (Unit)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(sight, unit.sight)
            .append(defence, unit.defence)
            .append(health, unit.health)
            .append(healthMaximum, unit.healthMaximum)
            .append(name, unit.name)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(name)
            .append(sight)
            .append(defence)
            .append(health)
            .append(healthMaximum)
            .toHashCode();
    }
}
