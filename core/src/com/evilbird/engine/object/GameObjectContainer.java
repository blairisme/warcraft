/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.game.GameController;
import com.evilbird.engine.object.cache.GameObjectGroupCache;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.engine.object.spatial.GameObjectGraphUpdater;
import com.google.gson.annotations.JsonAdapter;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

/**
 * Instances of this class represent the root node in the game object
 * hierarchy.
 *
 * @author Blair Butterworth
 */
@JsonAdapter(GameObjectContainerSerializer.class)
public class GameObjectContainer implements GameObjectComposite, Disposable
{
    private GameObjectGroup group;
    private GameObjectGraph graph;
    private transient Stage delegate;
    private transient GameController controller;
    private transient GameObjectGraphUpdater graphUpdater;

    public GameObjectContainer() {
        this.group = new GameObjectGroupCache();
        this.group.setRoot(this);
        this.group.setTouchable(Touchable.childrenOnly);
        this.group.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        this.graphUpdater = new GameObjectGraphUpdater();
        this.group.addObserver(graphUpdater);

        this.delegate = new Stage();
        this.delegate.addActor(group.delegate);
    }

    /**
     * Adds a {@link GameObject} as a child of the {@code GameObjectContainer}.
     */
    @Override
    public void addObject(GameObject gameObject) {
        this.group.addObject(gameObject);
    }

    /**
     * Adds a collection of {@link GameObject GameObjects} as children of the
     * {@code GameObjectContainer}.
     */
    @Override
    public void addObjects(Collection<GameObject> gameObjects) {
        this.group.addObjects(gameObjects);
    }

    /**
     * Determines whether the given {@link GameObject} is contained in the {@code
     * GameObjectContainer }: its one of its children.
     *
     * @param gameObject  the {@code Item} to search for. This parameter cannot be
     *              {@code null}.
     *
     * @return  {@code true} if the given {@code Item} is contained in the
     *          {@code GameObjectContainer }
     */
    public boolean containsObject(GameObject gameObject) {
        return group.containsObject(gameObject);
    }

    /**
     * Release system resources used by the root.
     */
    @Override
    public void dispose() {
        delegate.dispose();
    }

    /**
     * Draws the container.
     */
    public void draw() {
        delegate.draw();
    }

    /**
     * Returns the first child {@link GameObject} that satisfies the given
     * {@link Predicate}.
     *
     * @param predicate a predicate implementation used to differentiate
     *                  between items.
     *
     * @return a child item satisfying the given predicate.
     */
    public GameObject find(Predicate<GameObject> predicate) {
        return group.find(predicate);
    }

    /**
     * Returns the all child {@link GameObject}s that satisfy the given
     * {@link Predicate}.
     *
     * @param predicate a predicate implementation used to differentiate
     *                  between items.
     *
     * @return all child items satisfying the given predicate.
     */
    public Collection<GameObject> findAll(Predicate<GameObject> predicate) {
        return group.findAll(predicate);
    }

    /**
     * Returns the group that contains all of the containers children.
     */
    public GameObjectGroup getBaseGroup() {
        return group;
    }

    /**
     * Returns a {@link GameController} instance, used to control whats content
     * is rendered to the screen and to obtain system wide preferences.
     *
     * @return a {@code GameController}.
     */
    public GameController getController() {
        return controller;
    }

    /**
     * Returns the unique {@link Identifier} of the GameObjectContainer .
     *
     * @return an <code>Identifier</code>. Will not return {@code null}.
     */
    public Identifier getIdentifier() {
        return group.getIdentifier();
    }

    /**
     * Returns a collection containing the children of the ItemComposite.
     *
     * @return the children of the ItemGroup.
     */
    @Override
    public List<GameObject> getObjects() {
        return this.group.getObjects();
    }

    /**
     * Returns a {@link GameObjectGraph}, a graph of the game space, represented
     * as a 2 dimensional matrix of nodes, containing all of the touchable
     * items contained in the item root.
     *
     * @return an {@link GameObjectGraph}. This method may return {@code null}.
     */
    public GameObjectGraph getSpatialGraph() {
        return graph;
    }

    /**
     * Returns the roots {@link Viewport} which manages the {@link Camera} and
     * determines how world coordinates are mapped to and from the screen.
     *
     * @return a viewport.
     */
    public Viewport getViewport() {
        return delegate.getViewport();
    }

    /**
     * Returns the {@link GameObject} at the specified location in world coordinates.
     * Hit testing is performed in the order the item were inserted into the
     * root, last inserted items being tested first. To get world coordinates
     * from screen coordinates, use {@link #unproject(Vector2)}.
     *
     * @param coordinates the world coordinates to test.
     * @param touchable   specifies if hit detection will respect the items
     *                    touchability.
     *
     * @return the item at the specified location. This method may return
     *         {@code null} if no item can be located.
     */
    public GameObject hit(Vector2 coordinates, boolean touchable) {
        return group.hit(coordinates, touchable);
    }

    /**
     * Transforms the given world coordinates into screen coordinates.
     *
     * @param coordinates The world coordinates to convert.
     * @return The given world coordinates transformed into screen coordinates.
     */
    public Vector2 project(Vector2 coordinates) {
        Vector2 result = new Vector2(coordinates);
        return delegate.stageToScreenCoordinates(result);
    }

    /**
     * Sets the {@link GameObjectGraph} associated with the GameObjectContainer . Any Items
     * added to the GameObjectContainer  will automatically be provided to the ItemGraph
     * to apply occupancy.
     *
     * @param graph an {@link GameObjectGraph}. Cannot be {@code null}.
     */
    public void setSpatialGraph(GameObjectGraph graph) {
        Validate.notNull(graph);
        this.graph = graph;
        this.graphUpdater.setGraph(graph);
    }

    /**
     * Removes a {@link GameObject} from the {@code GameObjectContainer}.
     */
    @Override
    public void removeObject(GameObject gameObject) {
        this.group.removeObject(gameObject);
    }

    /**
     * Removes all {@link GameObject GameObjects} from the item
     * {@code GameObjectContainer}.
     */
    @Override
    public void removeObjects() {
        this.group.removeObjects();
    }

    /**
     * Sets a {@link GameController} instance, used to control whats content
     * is rendered to the screen and to obtain system wide preferences.
     *
     * @param controller a {@code GameController}.
     */
    public void setController(GameController controller) {
        this.controller = controller;
    }

    /**
     * Sets the unique {@link Identifier} of the GameObjectContainer .
     *
     * @param identifier an <code>Identifier</code>. Cannot be {@code null}.
     */
    public void setIdentifier(Identifier identifier) {
        group.setIdentifier(identifier);
    }

    /**
     * Sets the roots {@link Viewport} which manages the {@link Camera} and
     * determines how world coordinates are mapped to and from the screen.
     *
     * @param viewport a viewport.
     */
    public void setViewport(Viewport viewport) {
        delegate.setViewport(viewport);
    }

    /**
     * Configures the viewport's screen bounds using the specified screen size.
     *
     * @param width  the new width of the viewport.
     * @param height the new height of the viewport.
     * @param center whether the camera should be centered when resizing.
     */
    public void resize(int width, int height, boolean center) {
        Viewport viewport = delegate.getViewport();
        viewport.update(width, height, center);
        group.setSize(viewport.getWorldWidth(), viewport.getWorldHeight());
    }

    /**
     * Transforms the given screen coordinates into world coordinates.
     *
     * @param coordinates The screen coordinates to convert.
     * @return The given screen coordinates transformed into world coordinates.
     */
    public Vector2 unproject(Vector2 coordinates) {
        Vector2 result = new Vector2(coordinates);
        return delegate.screenToStageCoordinates(result);
    }

    /**
     * Updates every item in the item graph.
     *
     * @param delta the time between this frame and the last frame.
     */
    public void update(float delta) {
        delegate.getCamera().update();
        delegate.act(delta);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("group", group)
            .append("graph", graph)
            .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        GameObjectContainer other = (GameObjectContainer)obj;
        return new EqualsBuilder()
            .append(group, other.group)
            .append(graph, other.graph)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(group)
            .append(graph)
            .toHashCode();
    }
}
