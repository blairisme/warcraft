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
    private Drawable icon;
    private int sight;
    private int defence;
    private float health;
    private float healthMaximum;

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
}
