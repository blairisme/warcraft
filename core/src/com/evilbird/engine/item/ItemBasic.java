/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.serialization.SerializedInitializer;
import com.evilbird.engine.item.interop.ActorDecorator;
import com.google.gson.annotations.JsonAdapter;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.*;

import static com.badlogic.gdx.scenes.scene2d.Touchable.enabled;
import static com.evilbird.engine.common.lang.GenericIdentifier.Unknown;

/**
 * Instances of this class represent the basic entity in the game.
 *
 * @author Blair Butterworth
 */
@JsonAdapter(ItemBasicAdapter.class)
public class ItemBasic implements Item
{
    transient Actor delegate;
    transient ItemRoot root;
    transient ItemGroup parent;
    private Identifier id;
    private Identifier type;
    private boolean selected;
    private boolean selectable;
    private boolean visible;
    private Touchable touchable;
    private Vector2 size;
    private Vector2 position;
    private List<Action> actions;
    private transient ListIterator<Action> iterator;

    public ItemBasic() {
        id = Unknown;
        type = Unknown;
        selected = false;
        selectable = true;
        touchable = enabled;
        size = new Vector2(1, 1);
        position = new Vector2(0, 0);
        actions = new ArrayList<>();
        delegate = getDelegate();
    }

    /**
     * Assigns an {@link Action} to the Item, a "bundle" of behaviour that
     * modifies the Item is a meaningful manner.
     *
     * @param action an Action instance.
     */
    @Override
    public void addAction(Action action) {
        Validate.notNull(action);
        action.setItem(this);
        if (iterator != null) {
            iterator.add(action);
        } else {
            actions.add(action);
        }
    }

    /**
     * Removes the {@link Action Actions} assigned to the Item.
     */
    @Override
    public void clearActions() {
        if (iterator != null) {
            iterator = Collections.emptyListIterator();
        }
        actions.clear();
    }

    /**
     * Returns the {@link Action Actions} assigned to the Item.
     *
     * @return  a {@link Collection} of <code>Actions</code>. Will not return
     *          <code>null</code>.
     */
    @Override
    public Collection<Action> getActions() {
        return Collections.unmodifiableList(actions);
    }

    /**
     * Returns the unique {@link Identifier} of the Item.
     *
     * @return an <code>Identifier</code>. Will not return <code>null</code>.
     */
    @Override
    public Identifier getIdentifier() {
        return id;
    }

    /**
     * Returns the type of the Item.
     *
     * @return a type {@link Identifier}. Will not be <code>null</code>.
     */
    @Override
    public Identifier getType() {
        return type;
    }

    /**
     * Returns the parent of the Item: the direct ancestor of the Item in the
     * Item hierarchy.
     *
     * @return an {@link ItemGroup} instance. Will not be <code>null</code>.
     */
    @Override
    public ItemGroup getParent() {
        return parent;
    }

    /**
     * Returns the root of the Item: the most distant ancestor of the Item in
     * the Item hierarchy.
     *
     * @return an {@link ItemRoot} instance. Will not be <code>null</code>.
     */
    @Override
    public ItemRoot getRoot() {
        return root;
    }

    /**
     * Returns whether the Item has been selected by the user: a process that aids
     * the user by allowing them to issue commands to multiple Items at the
     * same time.
     *
     * @return <code>true</code> if the Item has been selected.
     */
    @Override
    public boolean getSelected() {
        return selected;
    }

    /**
     * Returns whether or not the user can select the Item, a process that aids
     * the user by allowing them to issue commands to multiple items at the
     * same time.
     *
     * @return <code>true</code> if the Item can been selected.
     */
    @Override
    public boolean getSelectable() {
        return selectable;
    }

    /**
     * Returns whether the Item can be interacted with by the user or not; should
     * it generate user input events, or not.
     *
     * @return <code>true</code> if the Item can be interacted with.
     */
    @Override
    public boolean getTouchable() {
        return touchable == Touchable.enabled;
    }

    /**
     * Returns whether the Item is drawn or not.
     *
     * @return <code>true</code> if the Item should be drawn.
     */
    @Override
    public boolean getVisible() {
        return visible;
    }

    /**
     * Returns the bounds of the Item, specified in pixels. Updating the
     * returned value will not effect the Item.
     *
     * @return  a {@link Rectangle} describing the Items bounds. Will not
     *          return <code>null</code>.
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(position.x, position.y, size.x, size.y);
    }

    /**
     * Returns a {@link Vector2} describing size of the Item in pixels.
     * Updating the returned value will not effect the Item.
     *
     * @return a <code>Vector2</code> instance. Will not return <code>null</code>.
     */
    @Override
    public Vector2 getSize() {
        return size.cpy();
    }

    /**
     * Returns the width of the Item, specified in pixels.
     *
     * @return the width of the Item.
     */
    @Override
    public float getWidth() {
        return size.x;
    }

    /**
     * Returns the height of the Item, specified in pixels.
     *
     * @return the height of the Item.
     */
    @Override
    public float getHeight() {
        return size.y;
    }

    /**
     * Returns a {@link Vector2} describing position of the Item in pixels.
     * Updating the returned value will not effect the Item.
     *
     * @return a <code>Vector2</code> instance. Will not return <code>null</code>.
     */
    @Override
    public Vector2 getPosition() {
        return position.cpy();
    }

    /**
     * Returns a {@link Vector2} describing position of the Item in pixels with
     * respect to Item as determined by the given {@link Alignment}. Updating
     * the returned value will not effect the Item.
     *
     * @param alignment the <code>Alignment</code> of the resulting position.
     * @return          a <code>Vector2</code> instance. Will not return
     *                  <code>null</code>.
     */
    @Override
    public Vector2 getPosition(Alignment alignment) {
        Validate.notNull(alignment);
        int align = alignment.toGdxAlign();
        return new Vector2(delegate.getX(align), delegate.getY(align));
    }

    /**
     * Returns the horizontal coordinate of the Item, specified in pixels with
     * respect to the left hand side of the Item.
     *
     * @return the horizontal coordinate of the Item.
     */
    @Override
    public float getX() {
        return position.x;
    }

    /**
     * Returns the vertical coordinate of the Item, specified in pixels with
     * respect to the bottom of the Item.
     *
     * @return the vertical coordinate of the Item.
     */
    @Override
    public float getY() {
        return position.y;
    }

    /**
     * Determines if the Item has currently been assigned an {@link Action}.
     *
     * @return <code>true</code> if the Item has an assigned Action.
     */
    @Override
    public boolean hasActions() {
        return delegate.hasActions();
    }

    /**
     * Sets the unique {@link Identifier} of the Item.
     *
     * @param identifier an <code>Identifier</code>. Cannot be <code>null</code>.
     */
    @Override
    public void setIdentifier(Identifier identifier) {
        Validate.notNull(id);
        this.id = identifier;
    }

    /**
     * Sets the type of the Item.
     *
     * @param type a type {@link Identifier}. Cannot be <code>null</code>.
     */
    @Override
    public void setType(Identifier type) {
        Validate.notNull(type);
        this.type = type;
    }

    /**
     * Sets the direct ancestor of the Item in the Item hierarchy.
     *
     * @param parent an {@link ItemGroup} instance. Cannot be <code>null</code>.
     */
    @Override
    public void setParent(ItemGroup parent) {
        Validate.notNull(parent);
        this.parent = parent;
    }

    /**
     * Sets the most distant ancestor of the Item in the Item hierarchy.
     *
     * @param root an {@link ItemRoot} instance.
     */
    @Override
    public void setRoot(ItemRoot root) {
        this.root = root;
        actions.forEach(action -> action.setRoot(root));
    }

    /**
     * Sets whether the Item has been selected by the user: a process that aids
     * the user by allowing them to issue commands to multiple items at the
     * same time.
     *
     * @param selected <code>true</code> if the Item has been selected.
     */
    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * Sets whether or not the user can select the item, a process that aids
     * the user by allowing them to issue commands to multiple items at the
     * same time.
     *
     * @param selectable <code>true</code> if the Item can been selected.
     */
    @Override
    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    /**
     * Sets whether the item can be interacted with by the user or not; should
     * it generate user input events, or not.
     *
     * @param touchable <code>true</code> if the Item can be interacted with.
     */
    @Override
    public void setTouchable(Touchable touchable) {
        this.touchable = touchable;
        this.delegate.setTouchable(touchable);
    }

    /**
     * Sets whether the Item is drawn or not.
     *
     * @param visible <code>true</code> if the Item should be drawn.
     */
    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
        this.delegate.setVisible(visible);
    }

    /**
     * Sets the spatial dimensions of the Item.
     *
     * @param width  the new width of the item.
     * @param height the new height of the item.
     */
    @Override
    public void setSize(float width, float height) {
        this.size.x = width;
        this.size.y = height;
        this.delegate.setSize(width, height);
    }

    /**
     * Sets the spatial dimensions of the Item.
     *
     * @param size the new spatial dimensions of the Item. Cannot be
     *             <code>null</code>.
     */
    @Override
    public void setSize(Vector2 size) {
        Validate.notNull(size);
        this.size = size;
        this.delegate.setSize(size.x, size.y);
    }

    /**
     * Sets the spatial location of the Item.
     *
     * @param x the new horizontal coordinate of the Item.
     * @param y the new vertical coordinate of the Item.
     */
    @Override
    public void setPosition(float x, float y) {
        this.position.x = x;
        this.position.y = y;
        this.delegate.setPosition(x, y);
    }

    /**
     * Sets the spatial location of the Item.
     *
     * @param position a {@link Vector2 position}. Cannot be <code>null</code>.
     */
    @Override
    public void setPosition(Vector2 position) {
        Validate.notNull(position);
        this.position = position;
        this.delegate.setPosition(position.x, position.y);
    }

    /**
     * Renders the Item.
     *
     * @param batch a {@link Batch} instance, which combines multiple draw
     *              operations for better efficiency.
     * @param alpha the transparency of the Items parent.
     */
    @Override
    public void draw(Batch batch, float alpha) {
    }

    /**
     * Updates the Item based on time. Typically this is called each frame by
     * {@link ItemRoot#update(float)}.
     *
     * @param delta Time in seconds since the last frame.
     */
    @Override
    public void update(float delta) {
        iterator = actions.listIterator();
        while (iterator.hasNext()) {
            Action action = iterator.next();
            if (action.act(delta)) {
                iterator.remove();
            }
        }
        iterator = null;
    }

    /**
     * Called when the item's position has been changed.
     */
    @Override
    public void positionChanged() {
    }

    /**
     * Called when the item's size has been changed.
     */
    @Override
    public void sizeChanged() {
    }

    /**
     * Returns the {@link ItemBasic} at the specified location in the items local coordinate system (0,0
     * is the bottom left of the actor and width,height is the upper right).
     *
     * @param position  the world coordinates to test.
     * @param touchable specifies if hit detection will respect the items touchability.
     * @return the item at the specified location or null if no item is located there.
     */
    @Override
    public Item hit(Vector2 position, boolean touchable) {
        if (touchable && delegate.getTouchable() != enabled) return null;
        return position.x >= 0 && position.x < delegate.getWidth() &&
                position.y >= 0 && position.y < delegate.getHeight() ? this : null;
    }

    /**
     * Converts the coordinates given in the parent's coordinate system to this items's coordinate
     * system.
     *
     * @param coordinates given in the parent's coordinate system.
     * @return coordinates given in the items's coordinate system.
     */
    @Override
    public Vector2 parentToLocalCoordinates(Vector2 coordinates) {
        Vector2 result = new Vector2(coordinates);
        return delegate.parentToLocalCoordinates(result);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", id)
            .append("type", type)
            .append("selected", selected)
            .append("selectable", selectable)
            .append("visible", visible)
            .append("touchable", touchable)
            .append("size", size)
            .append("position", position)
            .append("actions", actions)
            .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

        ItemBasic item = (ItemBasic)obj;
        return new EqualsBuilder()
            .append(id, item.id)
            .append(type, item.type)
            .append(selected, item.selected)
            .append(selectable, item.selectable)
            .append(visible, item.visible)
            .append(touchable, item.touchable)
            .append(size, item.size)
            .append(position, item.position)
            .append(actions, item.actions)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(id)
            .append(type)
            .append(selected)
            .append(selectable)
            .append(visible)
            .append(touchable)
            .append(size)
            .append(position)
            .append(actions)
            .toHashCode();
    }

    @Override
    public Actor toActor() {
        return delegate;
    }

    protected Actor getDelegate() {
        Actor result = newDelegate();
        result.setUserObject(this);
        return result;
    }

    protected Actor newDelegate() {
        return new ActorDecorator(this);
    }

    @SerializedInitializer
    protected void updateDelegate() {
        delegate.setVisible(visible);
        delegate.setTouchable(touchable);
        delegate.setSize(size.x, size.y);
        delegate.setPosition(position.x, position.y);
    }
}
