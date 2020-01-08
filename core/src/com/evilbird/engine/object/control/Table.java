/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.object.control;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectGroup;
import com.evilbird.engine.object.GameObjectGroupObserver;
import com.evilbird.engine.object.interop.TableDecorator;

import java.util.Collection;
import java.util.Objects;

/**
 * An item which arranges its children using a table pattern.
 *
 * @author Blair Butterworth
 */
public class Table extends GameObjectGroup
{
    protected transient com.badlogic.gdx.scenes.scene2d.ui.Table control;
    
    public Table() {
        super();
    }

    /**
     * Adds a new cell to the table containing the given {@link Actor}. A
     * {@link Cell} reference will be returned, allowing the layout of the new
     * {@code Cell} to be customised.
     *
     * @param actor  an {@code Actor} instance. This parameter cannot be
     *              {@code null}.
     * @return      a reference to the new {@code Cell}. This method will
     *              not return {@code null}.
     */
    public Cell<Actor> add(Actor actor) {
        Objects.requireNonNull(actor);
        return control.add(actor);
    }

    /**
     * Adds a new cell to the table containing the given {@link GameObject}. A
     * {@link Cell} reference will be returned, allowing the layout of the new
     * {@code Cell} to be customised.
     *
     * @param gameObject  an {@code Item} instance. This parameter cannot be
     *              {@code null}.
     * @return      a reference to the new {@code Cell}. This method will
     *              not return {@code null}.
     */
    public Cell<Actor> add(GameObject gameObject) {
        Objects.requireNonNull(gameObject);
        gameObject.setParent(this);
        gameObject.setRoot(getRoot());
        objects.add(gameObject);
        CollectionUtils.forEach(observers, it -> it.objectAdded(gameObject));
        return control.add(gameObject.toActor());
    }

    /**
     * Adds a new cell to the table containing the given {@link GameObject}.
     *
     * @param object  an {@code Item} instance. This parameter cannot be
     *              {@code null}.
     */
    @Override
    public void addObject(GameObject object) {
        add(object);
    }

    /**
     * Determines if the given {@link GameObject} is contained in the table.
     */
    public boolean contains(GameObject gameObject) {
        Collection<GameObject> gameObjects = getObjects();
        return gameObjects.contains(gameObject);
    }

    /**
     * Draws bordered regions around the table and its contents.
     */
    public void debug() {
        control.debug();
    }

    /**
     * Returns the {@link Cell Cells} displayed by the table.
     */
    public Array<Cell> getCells() {
        return control.getCells();
    }

    /**
     * Returns a {@link Vector2} describing position of the game object in pixels.
     * Updating the returned value will not effect the game object.
     *
     * @return a {@code Vector2} instance. Will not return {@code null}.
     */
    @Override
    public Vector2 getPosition() {
        return new Vector2(control.getX(), control.getY());
    }

    /**
     * Returns a {@link Vector2} describing position of the game object in pixels with
     * respect to game object as determined by the given {@link Alignment}. Updating
     * the returned value will not effect the game object.
     *
     * @param alignment the <code>Alignment</code> of the resulting position.
     * @return          a {@code Vector2} instance. Will not return
     *                  {@code null}.
     */
    @Override
    public Vector2 getPosition(Alignment alignment) {
        int align = alignment.toGdxAlign();
        return new Vector2(control.getX(align), control.getY(align));
    }

    /**
     * Returns the horizontal coordinate of the game object, specified in pixels with
     * respect to the left hand side of the game object.
     *
     * @return the horizontal coordinate of the game object.
     */
    @Override
    public float getX() {
        return control.getX();
    }

    /**
     * Returns the vertical coordinate of the game object, specified in pixels with
     * respect to the bottom of the game object.
     *
     * @return the vertical coordinate of the game object.
     */
    @Override
    public float getY() {
        return control.getY();
    }

    /**
     * Returns a {@link Skin}, specifying the visual and auditory presentation
     * of the table.
     */
    public Skin getSkin() {
        return control.getSkin();
    }

    /**
     * Sets the logical alignment of the table within its parent.
     */
    public void setAlignment(Alignment alignment) {
        control.align(alignment.toGdxAlign());
    }

    /**
     * Sets the background image of the table.
     */
    public void setBackground(Drawable background) {
        control.setBackground(background);
    }

    /**
     * Sets the background image of the table.
     */
    public void setBackground(String background) {
        control.setBackground(background);
    }

    /**
     * Sets the logical alignment of the table within its parent to its center.
     */
    public void setCentered() {
        control.center();
    }

    /**
     * Instructs the {@code GameObjectGroup} to resize to fill its parent.
     *
     * @param fill {@code true} if the {@code GameObjectGroup} should fill its parent.
     */
    @Override
    public void setFillParent(boolean fill) {
        super.setFillParent(fill);
        control.setFillParent(fill);
    }

    /**
     * Sets the tables {@link Skin}, specifying its visual and auditory
     * presentation.
     */
    public void setSkin(Skin skin) {
        control.setSkin(skin);
    }

    /**
     * Removes a cell from the Table containing the given {@link GameObject}.
     *
     * @param object  an {@code Item} instance. This parameter cannot be
     *              {@code null}.
     */
    @Override
    public void removeObject(GameObject object) {
        Objects.requireNonNull(object);
        objects.remove(object);
        control.removeActor(object.toActor());
        CollectionUtils.forEach(observers, it -> it.objectRemoved(object));
    }

    /**
     * Removes all cells from the Table.
     */
    @Override
    public void removeObjects() {
        objects.clear();
        control.clear();
        CollectionUtils.forEach(observers, GameObjectGroupObserver::objectsCleared);
    }

    @Override
    protected Actor newDelegate() {
        control = new TableDecorator(this);
        return control;
    }
}
