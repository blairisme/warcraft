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
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.lang.Destroyable;
import com.evilbird.engine.common.lang.Selectable;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemReference;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.spatial.ItemGraphOccupant;
import com.evilbird.engine.item.specialized.Viewable;
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
public class Unit extends Viewable implements Destroyable, Selectable, ItemGraphOccupant
{
    private int sight;
    private int armour;
    private float health;
    private float healthMaximum;
    private boolean selected;
    private boolean selectable;
    private ItemReference association;
    private transient UnitStyle style;

    @Inject
    public Unit(Skin skin) {
        super(skin);
        sight = 0;
        armour = 0;
        health = 0;
        healthMaximum = 0;
        selected = false;
        selectable = true;
    }

    public Item getAssociatedItem() {
        return association != null ? association.get() : null;
    }

    @Override
    public int getArmour() {
        return armour;
    }

    @Override
    public float getHealth() {
        return health;
    }

    public float getHealthMaximum() {
        return healthMaximum;
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

    public void setAssociatedItem(Item associate) {
        this.association = associate != null ? new ItemReference(associate) : null;
    }

    public void setArmour(int armour) {
        this.armour = armour;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public void setHealthMaximum(float healthMaximum) {
        this.healthMaximum = healthMaximum;
    }

    public void setSight(int pixels) {
        this.sight = pixels;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    public void setSize(GridPoint2 size) {
        setSize(size.x, size.y);
    }

    @Override
    public void setStyle(String name) {
        super.setStyle(name);
        Skin skin = getSkin();
        style = skin.get(name, UnitStyle.class);
    }

    @Override
    public void setRoot(ItemRoot root) {
        super.setRoot(root);
        if (association != null) {
            association.setParent(root);
        }
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
            .append(getIdentifier())
            .append(getType())
            .append("health", health)
            .append("selected", selected)
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
            .append(armour, unit.armour)
            .append(health, unit.health)
            .append(healthMaximum, unit.healthMaximum)
            .append(selected, unit.selected)
            .append(selectable, unit.selectable)
            .append(association, unit.association)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(sight)
            .append(armour)
            .append(health)
            .append(healthMaximum)
            .append(selected)
            .append(selectable)
            .append(association)
            .toHashCode();
    }
}
