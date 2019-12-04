/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.common.graphics.ColourMaskSprite;
import com.evilbird.engine.common.graphics.animation.Animation;
import com.evilbird.engine.common.graphics.animation.AnimationFrame;
import com.evilbird.engine.common.graphics.renderable.Renderable;
import com.evilbird.engine.object.AnimatedObject;
import com.evilbird.engine.object.AnimatedObjectStyle;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.GameObjectReference;
import com.evilbird.engine.object.spatial.SpatialObject;
import com.evilbird.warcraft.common.TeamColour;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import com.evilbird.warcraft.object.common.capability.SelectableObject;
import com.evilbird.warcraft.object.common.capability.TerrainType;
import com.evilbird.warcraft.object.common.value.FixedValue;
import com.evilbird.warcraft.object.common.value.Value;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.selector.Selector;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.evilbird.warcraft.object.common.value.FixedValue.Zero;

/**
 * Instances of this represent a game object that the user can control and
 * interact with.
 *
 * @author Blair Butterworth
 */
public class Unit extends AnimatedObject implements PerishableObject, SelectableObject, SpatialObject
{
    private Value armour;
    private float health;
    private float healthMaximum;
    private boolean highlighted;
    private Value sight;
    private boolean selected;
    private boolean selectable;
    private GameObjectReference selector;
    private GameObjectReference associate;
    private GameObjectReference effect;

    private transient UnitStyle style;
    private transient Renderable highlight;
    private transient Renderable selection;

    /**
     * Constructs a new instance of this class given a {@link Skin} containing
     * an {@link AnimatedObjectStyle}, specifying the visual and auditory
     * presentation of the new Unit.
     *
     * @param skin  a {@code Skin} instance containing a
     *              {@code AnimatedObjectStyle}.
     *
     * @throws NullPointerException if the given skin is {@code null} or
     *                              doesn't contain a {@code ViewableStyle}.
     */
    public Unit(Skin skin) {
        super(skin);
        sight = Zero;
        armour = Zero;
        health = 0;
        healthMaximum = 0;
        selected = false;
        selectable = true;
    }

    /**
     * Returns how much damage the units armor absorbs with each attack.
     */
    @Override
    public int getArmour() {
        return (int)armour.getValue(this);
    }

    /**
     * Returns how much damage the units armor absorbs with each attack,
     * excluding upgrades.
     */
    public Value getArmourValue() {
        return armour;
    }

    /**
     * Returns the set of {@link GameObject Items} associated with the Unit, if any.
     */
    public Collection<GameObject> getAssociatedObjects() {
        List<GameObject> result = new ArrayList<>(2);

        if (selector != null) {
            result.add(selector.get());
        }
        if (associate != null) {
            result.add(associate.get());
        }
        if (effect != null) {
            result.add(effect.get());
        }
        return result;
    }

    /**
     * Returns an effect associated with the unit, if any.
     */
    public GameObject getEffect() {
        return effect != null ? effect.get() : null;
    }

    /**
     * Returns the current health of the unit.
     */
    @Override
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
     * Returns whether the unit should be highlighted to the user, usually to
     * indicate the unit can be a recipient of a subsequent user action.
     */
    public boolean getHighlighted() {
        return highlighted;
    }

    /**
     * Returns the distance that the unit can detect other units, specified in
     * world units.
     */
    public int getSight() {
        return (int)sight.getValue(this);
    }

    /**
     * Returns the distance that the unit can detect other units, specified in
     * world units, excluding upgrades.
     */
    public Value getSightValue() {
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
     * Returns the units {@link Selector}: a visual guide used by the user to
     * select game objects.
     */
    public Selector getSelector() {
        return selector != null ? (Selector)selector.get() : null;
    }

    /**
     * Returns the team number of the {@code Unit Units} owner.
     */
    @Override
    public int getTeam() {
        Player player = (Player)getParent();
        return player != null ? player.getTeam() : -1;
    }

    /**
     * Returns the type of terrain the {@code Unit} resides in.
     */
    @Override
    public TerrainType getTerrainType() {
        return TerrainType.Land;
    }

    /**
     * Returns whether the {@code Unit} is visible to potential attackers.
     */
    @Override
    public boolean isAttackable() {
        return true;
    }

    /**
     * Sets the amount of damage the Unit can absorb with each attack.
     */
    public void setArmour(int armour) {
        this.armour = new FixedValue(armour);
    }

    /**
     * Sets the amount of damage the Unit can absorb with each attack.
     */
    public void setArmour(Value armour) {
        this.armour = armour;
    }

    /**
     * Sets an effect shown for the unit.
     */
    public void setEffect(GameObject effect) {
        this.effect = effect != null ? new GameObjectReference<>(effect) : null;
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
     * Sets whether the unit should be highlighted to the user, usually to
     * indicate the unit can be a recipient of a subsequent user action.
     */
    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
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
     * Sets the units {@link Selector}: a visual guide used by the user to
     * select game objects.
     */
    public void setSelector(Selector selector) {
        this.selector = selector != null ? new GameObjectReference<>(selector) : null;
    }

    /**
     * Sets the distance that the unit can detect other units, specified in
     * world units.
     */
    public void setSight(int sight) {
        this.sight = new FixedValue(sight);
    }

    /**
     * Sets the distance that the unit can detect other units, specified in
     * world units.
     */
    public void setSight(Value sight) {
        this.sight = sight;
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
        setStyle(skin.get(name, UnitStyle.class));
    }

    public void setStyle(UnitStyle style) {
        this.style = style;
        this.selection = style.selection;
        this.highlight = style.highlight;
    }

    @Override
    public void setRoot(GameObjectContainer root) {
        super.setRoot(root);

        if (selector != null) {
            selector.setParent(root);
        }
        if (associate != null) {
            associate.setParent(root);
        }
    }

    @Override
    public void draw(Batch batch, float alpha) {
        if (getSelected()) {
            selection.draw(batch, position, size);
        }
        if (getHighlighted()) {
            highlight.draw(batch, position, size);
        }
        super.draw(batch, alpha);
    }

    @Override
    public void update(float time) {
        updateRenderables(time);
        super.update(time);
    }

    private void updateRenderables(float time) {
        highlight.update(time);
        selection.update(time);
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
            .append(highlighted, unit.highlighted)
            .append(selected, unit.selected)
            .append(selectable, unit.selectable)
            .append(selector, unit.selector)
            .append(associate, unit.associate)
            .append(effect, unit.effect)
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
            .append(highlighted)
            .append(selected)
            .append(selectable)
            .append(selector)
            .append(associate)
            .append(effect)
            .toHashCode();
    }

    protected GameObject getAssociatedItem() {
        return associate != null ? associate.get() : null;
    }

    protected void setAssociatedItem(GameObject associate) {
       this.associate = associate != null ? new GameObjectReference<>(associate) : null;
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
