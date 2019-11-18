/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.object;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.common.lang.Disablable;
import com.evilbird.engine.common.lang.Identifiable;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Positionable;
import com.evilbird.engine.common.lang.Visible;
import com.google.gson.annotations.JsonAdapter;

import java.util.Collection;

/**
 * Implementors of this interface represent a basic game entity.
 *
 * @author Blair Butterworth
 */
@JsonAdapter(GameObjectSerializer.class)
public interface GameObject extends Identifiable, Positionable, Disablable, Visible
{
    /**
     * Assigns an {@link Action} to the game object, a "bundle" of behaviour
     * that modifies the game object is a meaningful manner.
     *
     * @param action an Action instance.
     */
    void addAction(Action action);

    /**
     * Assigns an {@link Action} to the Item to be executed after the given
     * delay, specified in seconds.
     */
    void addAction(Action action, float delay);

    /**
     * Removes the {@link Action Actions} assigned to the game object.
     */
    void clearActions();

    /**
     * Returns the {@link Action Actions} assigned to the game object.
     *
     * @return  a {@link Collection} of <code>Actions</code>. Will not return
     *          {@code null}.
     */
    Collection<Action> getActions();

    /**
     * Determines if the game object has currently been assigned an
     * {@link Action}.
     *
     * @return {@code true} if the game object has an assigned Action.
     */
    boolean hasActions();

    /**
     * Returns the unique {@link Identifier} of the game object.
     *
     * @return an {@code Identifier}. Will not return {@code null}.
     */
    Identifier getIdentifier();

    /**
     * Returns the type of the game object.
     *
     * @return a type {@link Identifier}. Will not be {@code null}.
     */
    Identifier getType();

    /**
     * Returns the parent of the GameObject : the direct ancestor of the game object
     * in the game object hierarchy.
     *
     * @return an {@link GameObjectGroup} instance. Will not be {@code null}.
     */
    GameObjectGroup getParent();

    /**
     * Returns the root of the GameObject : the most distant ancestor of the game
     * object in the game object hierarchy.
     *
     * @return an {@link GameObjectContainer} instance. Will not be {@code null}.
     */
    GameObjectContainer getRoot();

    /**
     * Returns whether the game object can be interacted with by the user or
     * not; should it generate user input events, or not.
     *
     * @return {@code true} if the game object can be interacted with.
     */
    boolean getTouchable();

    /**
     * Returns whether the game object is drawn or not.
     *
     * @return {@code true} if the game object should be drawn.
     */
    boolean getVisible();

    /**
     * Returns the bounds of the game object, specified in pixels. Updating the
     * returned value will not effect the game object.
     *
     * @return  a {@link Rectangle} describing the GameObject s bounds. Will not
     *          return {@code null}.
     */
    Rectangle getBounds();

    /**
     * Returns a {@link Vector2} describing size of the game object in pixels.
     * Updating the returned value will not effect the game object.
     *
     * @return a {@code Vector2} instance. Will not return {@code null}.
     */
    Vector2 getSize();

    /**
     * Returns the width of the game object, specified in pixels.
     *
     * @return the width of the game object.
     */
    float getWidth();

    /**
     * Returns the height of the game object, specified in pixels.
     *
     * @return the height of the game object.
     */
    float getHeight();

    /**
     * Returns a {@link Vector2} describing position of the game object in pixels.
     * Updating the returned value will not effect the game object.
     *
     * @return a {@code Vector2} instance. Will not return {@code null}.
     */
    Vector2 getPosition();

    /**
     * Returns a {@link Vector2} describing position of the game object in pixels with
     * respect to game object as determined by the given {@link Alignment}. Updating
     * the returned value will not effect the game object.
     *
     * @param alignment the <code>Alignment</code> of the resulting position.
     * @return          a {@code Vector2} instance. Will not return
     *                  {@code null}.
     */
    Vector2 getPosition(Alignment alignment);

    /**
     * Returns the horizontal coordinate of the game object, specified in pixels with
     * respect to the left hand side of the game object.
     *
     * @return the horizontal coordinate of the game object.
     */
    float getX();

    /**
     * Returns the vertical coordinate of the game object, specified in pixels with
     * respect to the bottom of the game object.
     *
     * @return the vertical coordinate of the game object.
     */
    float getY();

    /**
     * Returns the z-index of the game object.
     *
     * @return a positive integer.
     */
    int getZIndex();

    /**
     * Sets the unique {@link Identifier} of the game object.
     *
     * @param identifier an {@code Identifier}. Cannot be {@code null}.
     */
    void setIdentifier(Identifier identifier);

    /**
     * Sets the type of the game object.
     *
     * @param type a type {@link Identifier}. Cannot be {@code null}.
     */
    void setType(Identifier type);

    /**
     * Sets the direct ancestor of the game object in the game object hierarchy.
     *
     * @param parent an {@link GameObjectGroup} instance. Cannot be {@code null}.
     */
    void setParent(GameObjectGroup parent);

    /**
     * Sets the most distant ancestor of the game object in the game object hierarchy.
     *
     * @param root an {@link GameObjectContainer} instance.
     */
    void setRoot(GameObjectContainer root);

    /**
     * Sets whether the item can be interacted with by the user or not; should
     * it generate user input events, or not.
     *
     * @param touchable {@code true} if the game object can be interacted with.
     */
    void setTouchable(Touchable touchable);

    /**
     * Sets whether the game object is drawn or not.
     *
     * @param visible {@code true} if the game object should be drawn.
     */
    void setVisible(boolean visible);

    /**
     * Sets the spatial dimensions of the game object.
     *
     * @param width  the new width of the item.
     * @param height the new height of the item.
     */
    void setSize(float width, float height);

    /**
     * Sets the spatial dimensions of the game object.
     *
     * @param size the new spatial dimensions of the game object. Cannot be
     *             {@code null}.
     */
    void setSize(Vector2 size);

    /**
     * Sets the spatial location of the GameObject s bottom left corner.
     *
     * @param x the new horizontal coordinate of the game object.
     * @param y the new vertical coordinate of the game object.
     */
    void setPosition(float x, float y);

    /**
     * Sets the spatial location of the game object using the given {@link Alignment}.
     *
     * @param x         the new horizontal coordinate of the game object.
     * @param y         the new vertical coordinate of the game object.
     * @param alignment defines which the path of the game object the given position
     *                  refers to.
     */
    void setPosition(float x, float y, Alignment alignment);

    /**
     * Sets the spatial location of the GameObject s bottom left corner.
     *
     * @param position a {@link Vector2 position}. Cannot be {@code null}.
     */
    void setPosition(Vector2 position);

    /**
     * Sets the spatial location of the game object using the given {@link Alignment}.
     *
     * @param position  a {@link Vector2 position}. Cannot be {@code null}.
     * @param alignment defines which the path of the game object the given position
     *                  refers to.
     */
    void setPosition(Vector2 position, Alignment alignment);

    /**
     * Sets the z-index of the game object. The z-index is the index into the
     * parent's children, where a lower index is below a higher index. Setting a
     * z-index higher than the number of children will move the child to the
     * front. Setting a z-index less than zero is invalid.
     *
     * @param index the new z-index. Must be a positive value.
     */
    void setZIndex(int index);

    /**
     * Renders the game object.
     *
     * @param batch a {@link Batch} instance, which combines multiple draw
     *              operations for better efficiency.
     * @param alpha the transparency of the GameObject s parent.
     */
    void draw(Batch batch, float alpha);

    /**
     * Updates the game object based on time. Typically this is called each frame by
     * {@link GameObjectContainer#update(float)}.
     *
     * @param delta Time in seconds since the last frame.
     */
    void update(float delta);

    /**
     * Called when the GameObject 's position has been changed.
     */
    void positionChanged();

    /**
     * Called when the GameObject 's size has been changed.
     */
    void sizeChanged();

    /**
     * Returns the {@link GameObject} at the specified location in the
     * GameObjects local coordinate system (0,0) s the bottom left of the actor
     * and width, height is the upper right).
     *
     * @param position  the world coordinates to test.
     * @param touchable specifies if hit detection will respect the GameObjects
     *                  touchability.
     *
     * @return  the GameObject at the specified location or null if no GameObject
     *          is located there.
     */
    GameObject hit(Vector2 position, boolean touchable);

    /**
     * Converts the coordinates given in the parent's coordinate system to this
     * GameObjects coordinate system.
     *
     * @param coordinates given in the parent's coordinate system.
     * @return coordinates given in the GameObject s's coordinate system.
     */
    Vector2 parentToLocalCoordinates(Vector2 coordinates);

    Actor toActor();
}
