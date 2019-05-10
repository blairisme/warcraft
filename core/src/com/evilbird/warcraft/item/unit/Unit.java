/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.lang.Destroyable;
import com.evilbird.engine.common.lang.Selectable;
import com.evilbird.engine.item.spatial.ItemGraphOccupant;
import com.evilbird.engine.item.specialized.AnimatedItem;
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
public class Unit extends AnimatedItem implements Destroyable, Selectable, ItemGraphOccupant
{
    private String name;
    private int sight;
    private int defence;
    private float health;
    private float healthMaximum;
    private boolean selected;
    private boolean selectable;
    private transient UnitStyle style;

    @Inject
    public Unit(Skin skin) {
        super(skin);
        name = "Unknown";
        sight = 0;
        defence = 0;
        health = 0;
        healthMaximum = 0;
        selected = false;
        selectable = true;
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
        return style.icon;
    }

    public String getName() {
        return name;
    }

    public int getSight() {
        return sight;
    }

    /**
     * Returns whether the Unit has been selected by the user: a process that aids
     * the user by allowing them to issue commands to multiple Items at the
     * same time.
     *
     * @return {@code true} if the Unit has been selected.
     */
    @Override
    public boolean getSelected() {
        return selected;
    }

    /**
     * Returns whether or not the user can select the Unit, a process that aids
     * the user by allowing them to issue commands to multiple items at the
     * same time.
     *
     * @return {@code true} if the Unit can been selected.
     */
    @Override
    public boolean getSelectable() {
        return selectable;
    }

    public void setDefence(int defence) {
        this.defence = defence;
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
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    @Override
    public void setStyle(String name) {
        super.setStyle(name);
        Skin skin = getSkin();
        style = skin.get(name, UnitStyle.class);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        if (getSelected()) {
            batch.draw(style.selection, getX(), getY(), getWidth(), getHeight());
        }
        super.draw(batch, alpha);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper("animated")
            .append("name", name)
            .append("sight", sight)
            .append("defence", defence)
            .append("health", health)
            .append("healthMaximum", healthMaximum)
            .append("selected", selected)
            .append("selectable", selectable)
            .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        Unit unit = (Unit)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(sight, unit.sight)
            .append(defence, unit.defence)
            .append(health, unit.health)
            .append(healthMaximum, unit.healthMaximum)
            .append(name, unit.name)
            .append(selected, unit.selected)
            .append(selectable, unit.selectable)
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
            .append(selected)
            .append(selectable)
            .toHashCode();
    }
}
