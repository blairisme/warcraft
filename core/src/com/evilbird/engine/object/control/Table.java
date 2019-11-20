/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.object.control;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
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
    public void clearObjects() {
        objects.clear();
        control.clear();
        CollectionUtils.forEach(observers, GameObjectGroupObserver::objectsCleared);
    }

    public boolean isShown(GameObject gameObject) {
        Collection<GameObject> gameObjects = getObjects();
        return gameObjects.contains(gameObject);
    }

    public void debug() {
        control.debug();
    }

    public Skin getSkin() {
        return control.getSkin();
    }

    public void setSkin(Skin skin) {
        control.setSkin(skin);
    }

    public void setAlignment(Alignment alignment) {
        control.align(alignment.toGdxAlign());
    }

    public void setBackground(Drawable background) {
        control.setBackground(background);
    }

    public void setBackground(String background) {
        control.setBackground(background);
    }

    public void setCentered() {
        control.center();
    }

    public void setFillParent(boolean fill) {
        control.setFillParent(fill);
    }

    @Override
    protected Actor newDelegate() {
        control = new TableDecorator(this);
        return control;
    }
}
