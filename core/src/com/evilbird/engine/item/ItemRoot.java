/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.game.GameController;
import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.engine.item.spatial.ItemGraphUpdater;
import com.google.gson.annotations.JsonAdapter;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * Instances of this class represent the root node in the item hierarchy.
 *
 * @author Blair Butterworth
 */
@JsonAdapter(ItemRootAdapter.class)
public class ItemRoot implements ItemComposite
{
    private ItemGroup group;
    private com.evilbird.engine.item.spatial.ItemGraph graph;
    private transient Stage delegate;
    private transient GameController controller;
    private transient com.evilbird.engine.item.spatial.ItemGraphUpdater graphUpdater;

    public ItemRoot() {
        this.group = new ItemGroup();
        this.group.setRoot(this);
        this.group.setTouchable(Touchable.childrenOnly);
        this.group.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        this.graphUpdater = new ItemGraphUpdater();
        this.group.addObserver(graphUpdater);

        this.delegate = new Stage();
        this.delegate.addActor(group.delegate);
    }

    /**
     * Adds an {@link Item} as a child of the item root.
     *
     * @param item the item to set.
     */
    @Override
    public void addItem(Item item) {
        this.group.addItem(item);
    }

    /**
     * Removes an {@link Item} from the item root.
     *
     * @param item the item to remove.
     */
    @Override
    public void removeItem(Item item) {
        this.group.removeItem(item);
    }

    /**
     * Removes all {@link Item}s from the item root.
     */
    @Override
    public void clearItems() {
        this.group.clearItems();
    }

    /**
     * Returns a collection containing the children of the ItemComposite.
     *
     * @return the children of the ItemGroup.
     */
    @Override
    public Collection<Item> getItems() {
        return this.group.getItems();
    }

    /**
     * Returns a {@link com.evilbird.engine.item.spatial.ItemGraph}, a graph of the game space, represented
     * as a 2 dimensional matrix of nodes, containing all of the touchable
     * items contained in the item root.
     *
     * @return an {@link com.evilbird.engine.item.spatial.ItemGraph}. This method may return <code>null</code>.
     */
    public com.evilbird.engine.item.spatial.ItemGraph getSpatialGraph() {
        return graph;
    }

    /**
     * Sets the {@link com.evilbird.engine.item.spatial.ItemGraph} associated with the ItemRoot. Any Items
     * added to the ItemRoot will automatically be provided to the ItemGraph
     * to update occupancy.
     *
     * @param graph an {@link com.evilbird.engine.item.spatial.ItemGraph}. Cannot be <code>null</code>.
     */
    public void setSpatialGraph(ItemGraph graph) {
        Validate.notNull(graph);
        this.graph = graph;
        this.graphUpdater.setGraph(graph);
    }

    /**
     * Returns the unique {@link Identifier} of the ItemRoot.
     *
     * @return an <code>Identifier</code>. Will not return <code>null</code>.
     */
    public Identifier getIdentifier() {
        return group.getIdentifier();
    }

    /**
     * Sets the unique {@link Identifier} of the ItemRoot.
     *
     * @param identifier an <code>Identifier</code>. Cannot be <code>null</code>.
     */
    public void setIdentifier(Identifier identifier) {
        group.setIdentifier(identifier);
    }

    /**
     * Returns the first child {@link Item} that satisfies the given
     * {@link Predicate}.
     *
     * @param predicate a predicate implementation used to differentiate
     *                  between items.
     *
     * @return a child item satisfying the given predicate.
     */
    public Item find(Predicate<Item> predicate) {
        return group.find(predicate);
    }

    /**
     * Returns the all child {@link Item}s that satisfy the given
     * {@link Predicate}.
     *
     * @param predicate a predicate implementation used to differentiate
     *                  between items.
     *
     * @return all child items satisfying the given predicate.
     */
    public <T extends Item> Collection<T> findAll(Predicate<T> predicate) {
        return group.findAll(predicate);
    }

    /**
     * Returns a {@link GameController} instance, used to control whats content
     * is rendered to the screen and to obtain system wide preferences.
     *
     * @return a <code>GameController</code>.
     */
    public GameController getController() {
        return controller;
    }

    public ItemGroup getBaseGroup() {
        return group;
    }

    /**
     * Sets a {@link GameController} instance, used to control whats content
     * is rendered to the screen and to obtain system wide preferences.
     *
     * @param controller a <code>GameController</code>.
     */
    public void setController(GameController controller) {
        this.controller = controller;
    }

    /**
     * Draws the item graph.
     */
    public void draw() {
        delegate.draw();
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
     * Release system resources used by the root.
     */
    public void dispose() {
        delegate.dispose();
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
     * Returns the {@link Item} at the specified location in world coordinates.
     * Hit testing is performed in the order the item were inserted into the
     * root, last inserted items being tested first. To get world coordinates
     * from screen coordinates, use {@link #unproject(Vector2)}.
     *
     * @param coordinates the world coordinates to test.
     * @param touchable   specifies if hit detection will respect the items
     *                    touchability.
     *
     * @return the item at the specified location. This method may return
     *         <code>null</code> if no item can be located.
     */
    public Item hit(Vector2 coordinates, boolean touchable) {
        return group.hit(coordinates, touchable);
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
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

        ItemRoot other = (ItemRoot)obj;
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
