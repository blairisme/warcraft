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
import com.evilbird.engine.common.lang.*;
import com.google.gson.annotations.JsonAdapter;

import java.util.Collection;

/**
 * Implementors of this interface represent a basic game entity.
 *
 * @author Blair Butterworth
 */
@JsonAdapter(ItemBasicAdapter.class)
public interface Item extends Identifiable, Categorizable, Positionable, Selectable, Disablable, Visible
{
    /**
     * Assigns an {@link Action} to the Item, a "bundle" of behaviour that
     * modifies the Item is a meaningful manner.
     *
     * @param action an Action instance.
     */
    void addAction(Action action);

    /**
     * Removes the {@link Action Actions} assigned to the Item.
     */
    void clearActions();

    /**
     * Returns the {@link Action Actions} assigned to the Item.
     *
     * @return  a {@link Collection} of <code>Actions</code>. Will not return
     *          {@code null}.
     */
    Collection<Action> getActions();

    /**
     * Determines if the Item has currently been assigned an {@link Action}.
     *
     * @return {@code true} if the Item has an assigned Action.
     */
    boolean hasActions();

    /**
     * Returns the unique {@link Identifier} of the Item.
     *
     * @return an {@code Identifier}. Will not return {@code null}.
     */
    Identifier getIdentifier();

    /**
     * Returns the type of the Item.
     *
     * @return a type {@link Identifier}. Will not be {@code null}.
     */
    Identifier getType();

    /**
     * Returns the parent of the Item: the direct ancestor of the Item in the
     * Item hierarchy.
     *
     * @return an {@link ItemGroup} instance. Will not be {@code null}.
     */
    ItemGroup getParent();

    /**
     * Returns the root of the Item: the most distant ancestor of the Item in
     * the Item hierarchy.
     *
     * @return an {@link ItemRoot} instance. Will not be {@code null}.
     */
    ItemRoot getRoot();

    /**
     * Returns whether the Item has been selected by the user: a process that aids
     * the user by allowing them to issue commands to multiple Items at the
     * same time.
     *
     * @return {@code true} if the Item has been selected.
     */
    boolean getSelected();

    /**
     * Returns whether or not the user can select the Item, a process that aids
     * the user by allowing them to issue commands to multiple items at the
     * same time.
     *
     * @return {@code true} if the Item can been selected.
     */
    boolean getSelectable();

    /**
     * Returns whether the Item can be interacted with by the user or not; should
     * it generate user input events, or not.
     *
     * @return {@code true} if the Item can be interacted with.
     */
    boolean getTouchable();

    /**
     * Returns whether the Item is drawn or not.
     *
     * @return {@code true} if the Item should be drawn.
     */
    boolean getVisible();

    /**
     * Returns the bounds of the Item, specified in pixels. Updating the
     * returned value will not effect the Item.
     *
     * @return  a {@link Rectangle} describing the Items bounds. Will not
     *          return {@code null}.
     */
    Rectangle getBounds();

    /**
     * Returns a {@link Vector2} describing size of the Item in pixels.
     * Updating the returned value will not effect the Item.
     *
     * @return a {@code Vector2} instance. Will not return {@code null}.
     */
    Vector2 getSize();

    /**
     * Returns the width of the Item, specified in pixels.
     *
     * @return the width of the Item.
     */
    float getWidth();

    /**
     * Returns the height of the Item, specified in pixels.
     *
     * @return the height of the Item.
     */
    float getHeight();

    /**
     * Returns a {@link Vector2} describing position of the Item in pixels.
     * Updating the returned value will not effect the Item.
     *
     * @return a {@code Vector2} instance. Will not return {@code null}.
     */
    Vector2 getPosition();

    /**
     * Returns a {@link Vector2} describing position of the Item in pixels with
     * respect to Item as determined by the given {@link Alignment}. Updating
     * the returned value will not effect the Item.
     *
     * @param alignment the <code>Alignment</code> of the resulting position.
     * @return          a {@code Vector2} instance. Will not return
     *                  {@code null}.
     */
    Vector2 getPosition(Alignment alignment);

    /**
     * Returns the horizontal coordinate of the Item, specified in pixels with
     * respect to the left hand side of the Item.
     *
     * @return the horizontal coordinate of the Item.
     */
    float getX();

    /**
     * Returns the vertical coordinate of the Item, specified in pixels with
     * respect to the bottom of the Item.
     *
     * @return the vertical coordinate of the Item.
     */
    float getY();

    /**
     * Sets the unique {@link Identifier} of the Item.
     *
     * @param identifier an {@code Identifier}. Cannot be {@code null}.
     */
    void setIdentifier(Identifier identifier);

    /**
     * Sets the type of the Item.
     *
     * @param type a type {@link Identifier}. Cannot be {@code null}.
     */
    void setType(Identifier type);

    /**
     * Sets the direct ancestor of the Item in the Item hierarchy.
     *
     * @param parent an {@link ItemGroup} instance. Cannot be {@code null}.
     */
    void setParent(ItemGroup parent);

    /**
     * Sets the most distant ancestor of the Item in the Item hierarchy.
     *
     * @param root an {@link ItemRoot} instance.
     */
    void setRoot(ItemRoot root);

    /**
     * Sets whether the Item has been selected by the user: a process that aids
     * the user by allowing them to issue commands to multiple items at the
     * same time.
     *
     * @param selected {@code true} if the Item has been selected.
     */
    void setSelected(boolean selected);

    /**
     * Sets whether or not the user can select the item, a process that aids
     * the user by allowing them to issue commands to multiple items at the
     * same time.
     *
     * @param selectable {@code true} if the Item can been selected.
     */
    void setSelectable(boolean selectable);

    /**
     * Sets whether the item can be interacted with by the user or not; should
     * it generate user input events, or not.
     *
     * @param touchable {@code true} if the Item can be interacted with.
     */
    void setTouchable(Touchable touchable);

    /**
     * Sets whether the Item is drawn or not.
     *
     * @param visible {@code true} if the Item should be drawn.
     */
    void setVisible(boolean visible);

    /**
     * Sets the spatial dimensions of the Item.
     *
     * @param width  the new width of the item.
     * @param height the new height of the item.
     */
    void setSize(float width, float height);

    /**
     * Sets the spatial dimensions of the Item.
     *
     * @param size the new spatial dimensions of the Item. Cannot be
     *             {@code null}.
     */
    void setSize(Vector2 size);

    /**
     * Sets the spatial location of the Item.
     *
     * @param x the new horizontal coordinate of the Item.
     * @param y the new vertical coordinate of the Item.
     */
    void setPosition(float x, float y);

    /**
     * Sets the spatial location of the Item.
     *
     * @param position a {@link Vector2 position}. Cannot be {@code null}.
     */
    void setPosition(Vector2 position);

    /**
     * Renders the Item.
     *
     * @param batch a {@link Batch} instance, which combines multiple draw
     *              operations for better efficiency.
     * @param alpha the transparency of the Items parent.
     */
    void draw(Batch batch, float alpha);

    /**
     * Updates the Item based on time. Typically this is called each frame by
     * {@link ItemRoot#update(float)}.
     *
     * @param delta Time in seconds since the last frame.
     */
    void update(float delta);

    /**
     * Called when the item's position has been changed.
     */
    void positionChanged();

    /**
     * Called when the item's size has been changed.
     */
    void sizeChanged();

    /**
     * Returns the {@link Item} at the specified location in the items local
     * coordinate system (0,0) s the bottom left of the actor and width, height
     * is the upper right).
     *
     * @param position  the world coordinates to test.
     * @param touchable specifies if hit detection will respect the items touchability.
     * @return the item at the specified location or null if no item is located there.
     */
    Item hit(Vector2 position, boolean touchable);

    /**
     * Converts the coordinates given in the parent's coordinate system to this items's coordinate
     * system.
     *
     * @param coordinates given in the parent's coordinate system.
     * @return coordinates given in the items's coordinate system.
     */
    Vector2 parentToLocalCoordinates(Vector2 coordinates);

    Actor toActor();
}
