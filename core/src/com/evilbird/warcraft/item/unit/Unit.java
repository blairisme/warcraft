/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.common.graphics.Animation;
import com.evilbird.engine.common.graphics.AnimationFrame;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.item.ItemReference;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.spatial.SpatialObject;
import com.evilbird.engine.item.specialized.Viewable;
import com.evilbird.engine.item.specialized.ViewableStyle;
import com.evilbird.warcraft.common.TeamColour;
import com.evilbird.warcraft.item.common.graphics.ColourMaskSprite;
import com.evilbird.warcraft.item.common.state.PerishableObject;
import com.evilbird.warcraft.item.common.state.SelectableObject;
import com.evilbird.warcraft.item.common.upgrade.UpgradableValue;
import com.evilbird.warcraft.item.common.upgrade.UpgradeRank;
import com.evilbird.warcraft.item.common.upgrade.UpgradeSeries;
import com.evilbird.warcraft.item.data.player.Player;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.evilbird.warcraft.item.common.upgrade.UpgradableValue.Zero;
import static com.evilbird.warcraft.item.common.upgrade.UpgradeSeries.None;

/**
 * Instances of this represent a game object that the user can control and
 * interact with.
 *
 * @author Blair Butterworth
 */
public class Unit extends Viewable implements PerishableObject, SelectableObject, SpatialObject
{
    private int sight;
    private float health;
    private float healthMaximum;
    private boolean selected;
    private boolean selectable;
    private UpgradableValue armour;
    private List<ItemReference> associations;
    private transient UnitStyle style;

    /**
     * Constructs a new instance of this class given a {@link Skin} containing
     * an {@link ViewableStyle}, specifying the visual and auditory
     * presentation of the new Unit.
     *
     * @param skin a {@code Skin} instance containing a {@code ViewableStyle}.
     *
     * @throws NullPointerException if the given skin is {@code null} or
     *                              doesn't contain a {@code ViewableStyle}.
     */
    @Inject
    public Unit(Skin skin) {
        super(skin);
        sight = 0;
        armour = Zero;
        health = 0;
        healthMaximum = 0;
        selected = false;
        selectable = true;
        associations = new ArrayList<>(1);
    }

    /**
     * Associates the given {@link Item} with the Unit.
     */
    public void addAssociatedItem(Item associate) {
        associations.add(new ItemReference(associate));
    }

    /**
     * Returns the {@link Item} associated with the Unit, if any. If multiple
     * {@code Items} are associated with the Unit, then the first Item to be
     * associated will be returned.
     */
    public Item getAssociatedItem() {
        if (!associations.isEmpty()) {
            ItemReference reference = associations.get(0);
            return reference.get();
        }
        return null;
    }

    /**
     * Returns the set of {@link Item Items} associated with the Unit, if any.
     */
    public Collection<Item> getAssociatedItems() {
        return CollectionUtils.convert(associations, ItemReference::get);
    }

    /**
     * Returns how much damage the units armor absorbs with each attack.
     */
    public int getArmour() {
        return (int)getUpgradeValue(armour);
    }

    /**
     * Returns how much damage the units armor absorbs with each attack.
     */
    public int getArmour(UpgradeRank rank) {
        return (int)armour.getValue(rank);
    }

    /**
     * Returns the current health of the unit.
     */
    public float getHealth() {
        return health;
    }

    /**
     * Returns the maximum health of the unit.
     */
    public float getHealthMaximum() {
        return healthMaximum;
    }

    /**
     * Returns the distance that the unit can detect other units, specified in
     * world units.
     */
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

    /**
     * Returns whether or not the given {@link Item} is associated with the
     * Unit.
     */
    public boolean hasAssociatedItem(Item associate) {
        return associations.contains(new ItemReference(associate));
    }

    /**
     * Returns whether or not the any {@link Item Items} have been associated
     * with the Unit.
     */
    public boolean hasAssociatedItems() {
        return !associations.isEmpty();
    }

    /**
     * Removes the association between the unit and the given {@link Item}.
     */
    public void removeAssociatedItem(Item associate) {
        associations.remove(new ItemReference(associate));
    }

    public void setAssociatedItem(Item associate) {
        associations.clear();
        if (associate != null) {
            addAssociatedItem(associate);
        }
    }

    /**
     * Sets the amount of damage the Unit can absorb with each attack.
     */
    public void setArmour(UpgradableValue armour) {
        this.armour = armour;
    }

    /**
     * Sets the amount of damage the Unit can absorb with each attack.
     */
    public void setArmour(int armour) {
        this.armour = new UpgradableValue(None, armour);
    }

    /**
     * Sets the current health of the unit.
     */
    public void setHealth(float health) {
        this.health = health;
    }

    /**
     * Sets the maximum health of the unit.
     */
    public void setHealthMaximum(float healthMaximum) {
        this.healthMaximum = healthMaximum;
    }

    /**
     * Sets a colour used to visually identify the unit as belonging to a
     * specific team. The colour is used to change the colour of certain
     */
    public void setTeamColour(TeamColour colour) {
        if (colour != TeamColour.None) {
            setColour(colour.getGdxColour());
        }
    }

    /**
     * Sets the distance that the unit can detect other units, specified in
     * world units.
     */
    public void setSight(int pixels) {
        this.sight = pixels;
    }

    /**
     * Sets whether the Unit has been selected by the user: a process that aids
     * the user by allowing them to issue commands to multiple Items at the
     * same time.
     */
    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * Sets whether or not the user can select the Unit, a process that aids
     * the user by allowing them to issue commands to multiple items at the
     * same time.
     */
    @Override
    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    /**
     * Sets the spatial dimensions of the Item.
     */
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
        for (ItemReference association: associations) {
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
            .append(associations, unit.associations)
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
            .append(associations)
            .toHashCode();
    }

    protected float getUpgradeValue(UpgradableValue value) {
        UpgradeSeries series = value.getSeries();
        if (series != None) {
            ItemGroup parent = getParent();
            if (parent instanceof Player) {
                Player player = (Player) parent;
                UpgradeRank rank = player.getUpgradeRank(series);
                return value.getValue(rank);
            }
        }
        return value.getValue(UpgradeRank.None);
    }

    private void setColour(Color colour) {
        for (Animation animation : style.animations.values()) {
            for (AnimationFrame frame : animation.getFrames()) {
                setColour(frame, colour);
            }
        }
    }

    private void setColour(AnimationFrame frame, Color colour) {
        Drawable drawable = frame.getDrawable();

        if (drawable instanceof ColourMaskSprite) {
            ColourMaskSprite colouredDrawable = (ColourMaskSprite)drawable;
            colouredDrawable.setColour(colour);
        }
        if (drawable instanceof TextureRegionDrawable) {
            TextureRegionDrawable regionDrawable = (TextureRegionDrawable)drawable;
            setMask(frame, regionDrawable.getRegion(), colour);
        }
    }

    private void setMask(AnimationFrame frame, TextureRegion region, Color colour) {
        Texture texture = region.getTexture();
        Texture mask = style.masks.get(texture);

        if (mask != null) {
            ColourMaskSprite drawable = new ColourMaskSprite(region, mask, colour);
            frame.setDrawable(drawable);
        }
    }
}
