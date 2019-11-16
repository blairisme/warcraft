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
import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.common.graphics.ColourMaskSprite;
import com.evilbird.engine.common.graphics.animation.Animation;
import com.evilbird.engine.common.graphics.animation.AnimationFrame;
import com.evilbird.engine.common.graphics.renderable.Renderable;
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.engine.object.AnimatedObject;
import com.evilbird.engine.object.AnimatedObjectStyle;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.GameObjectReference;
import com.evilbird.engine.object.spatial.SpatialObject;
import com.evilbird.warcraft.common.TeamColour;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import com.evilbird.warcraft.object.common.capability.SelectableObject;
import com.evilbird.warcraft.object.common.value.FixedValue;
import com.evilbird.warcraft.object.common.value.Value;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
    private List<GameObjectReference> associatedObjects;
    private Map<Action, GameTimer> pendingActions;

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
        pendingActions = new HashMap<>();
        associatedObjects = new ArrayList<>(1);
    }

    /**
     * Assigns an {@link Action} to the Item to be executed after the given
     * delay.
     */
    public void addAction(Action action, float delay) {
        pendingActions.put(action, new GameTimer(delay));
    }

    /**
     * Assigns an {@link Action} to the Item to be executed after the given
     * delay.
     */
    public void addAction(Action action, GameTimer delay) {
        pendingActions.put(action, delay);
    }

    /**
     * Associates the given {@link GameObject} with the Unit.
     */
    public void addAssociatedItem(GameObject associate) {
        associatedObjects.add(new GameObjectReference(associate));
    }

    public void clearEffect() {
        //TODO
    }

    /**
     * Returns the {@link GameObject} associated with the Unit, if any. If multiple
     * {@code Items} are associated with the Unit, then the first Item to be
     * associated will be returned.
     */
    public GameObject getAssociatedItem() {
        if (!associatedObjects.isEmpty()) {
            GameObjectReference reference = associatedObjects.get(0);
            return reference.get();
        }
        return null;
    }

    /**
     * Returns the set of {@link GameObject Items} associated with the Unit, if any.
     */
    public Collection<GameObject> getAssociatedObjects() {
        return CollectionUtils.convert(associatedObjects, GameObjectReference::get);
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
     * Returns whether the {@code PerishableObject} is visible to potential
     * attackers.
     */
    @Override
    public boolean isAttackable() {
        return true;
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
     * Returns whether or not the given {@link GameObject} is associated with the
     * Unit.
     */
    public boolean hasAssociatedItem(GameObject associate) {
        return associatedObjects.contains(new GameObjectReference(associate));
    }

    /**
     * Returns whether or not the any {@link GameObject Items} have been associated
     * with the Unit.
     */
    public boolean hasAssociatedItems() {
        return !associatedObjects.isEmpty();
    }

    /**
     * Removes the association between the unit and the given {@link GameObject}.
     */
    public void removeAssociatedItem(GameObject associate) {
        associatedObjects.remove(new GameObjectReference(associate));
    }

    /**
     * Sets an association between the unit and the given {@link GameObject}. Any
     * existing associations will be removed.
     */
    public void setAssociatedItem(GameObject associate) {
        associatedObjects.clear();
        if (associate != null) {
            addAssociatedItem(associate);
        }
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

    public void setEffect(Object object) {
        //TODO
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
        for (GameObjectReference association: associatedObjects) {
            association.setParent(root);
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
        //effect.draw(batch,  position, size);
    }

    @Override
    public void update(float time) {
        updateRenderables(time);
        schedulePendingActions(time);
        super.update(time);
    }

    private void updateRenderables(float time) {
        //effect.update(time);
        highlight.update(time);
        selection.update(time);
    }

    private void schedulePendingActions(float time) {
        Set<Entry<Action, GameTimer>> pending = pendingActions.entrySet();
        pending.removeIf(entry -> schedulePendingAction(entry.getKey(), entry.getValue(), time));
    }

    private boolean schedulePendingAction(Action action, GameTimer delay, float time) {
        if (delay.advance(time)) {
            addAction(action);
        }
        return delay.complete();
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
            .append(associatedObjects, unit.associatedObjects)
            .append(pendingActions, unit.pendingActions)
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
            .append(associatedObjects)
            .append(pendingActions)
            .toHashCode();
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
