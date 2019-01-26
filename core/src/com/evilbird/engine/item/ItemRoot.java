/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.evilbird.engine.common.function.Predicate;

import java.util.Collection;

/**
 * Instances of this class represent the root node in the item hierarchy.
 *
 * @author Blair Butterworth
 */
public class ItemRoot implements ItemComposite, ItemGroupObserver
{
    private Stage delegate;
    private ItemGroup group;
    private ItemGraph graph;

    public ItemRoot(ItemGraph graph){
        this.group = new ItemGroup();
        this.group.setRoot(this);

        this.delegate = new Stage();
        this.delegate.addActor(group.delegate);

        if (graph != null) {
            this.graph = graph;
            this.group.addObserver(this);
        }
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
     * Returns a {@link ItemGraph}, a graph of the game space, represented
     * as a 2 dimensional matrix of 32 x 32 pixel sized nodes, containing all
     * of the touchable items contained in the item root.
     *
     * @return  a {@link ItemGraph}.
     */
    public ItemGraph getSpatialGraph() {
        return graph;
    }

    /**
     * Returns the first child {@link Item} that satisfies the given {@link Predicate}.
     *
     * @param predicate a predicate implementation used to differentiate between items.
     * @return a child item satisfying the given predicate.
     */
    public Item find(Predicate<Item> predicate) {
        return group.find(predicate);
    }

    /**
     * Returns the all child {@link Item}s that satisfy the given {@link Predicate}.
     *
     * @param predicate a predicate implementation used to differentiate between items.
     * @return all child items satisfying the given predicate.
     */
    public <T extends Item> Collection<T> findAll(Predicate<T> predicate) {
        return group.findAll(predicate);
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
        if (graph != null) {
            graph.update();
        }
        delegate.getCamera().update();
        delegate.act(delta);
    }

    /**
     * Returns the roots {@link Viewport} which manages the {@link Camera} and determines how world
     * coordinates are mapped to and from the screen.
     *
     * @return a viewport.
     */
    public Viewport getViewport() {
        return delegate.getViewport();
    }

    /**
     * Sets the roots {@link Viewport} which manages the {@link Camera} and determines how world
     * coordinates are mapped to and from the screen.
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
     */
    public void resize(int width, int height) {
        delegate.getViewport().update(width, height);
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
     * Returns the {@link Item} at the specified location in world coordinates. Hit testing is
     * performed in the order the item were inserted into the root, last inserted actors being
     * tested first. To get world coordinates from screen coordinates, use {@link #unproject(Vector2)}.
     *
     * @param coordinates the world coordinates to test.
     * @param touchable   specifies if hit detection will respect the items touchability.
     * @return the item at the specified location or null if no item is located there.
     */
    public Item hit(Vector2 coordinates, boolean touchable) {
        return group.hit(coordinates, touchable);
    }

    @Override
    public void itemAdded(Item item) {
        graph.addOccupants(item);
        if (item instanceof ItemGroup) {
            ItemGroup group = (ItemGroup)item;
            group.addObserver(this);
            graph.addOccupants(group.getItems());
        }
    }

    @Override
    public void itemRemoved(Item item) {
        graph.removeOccupants(item);
        if (item instanceof ItemGroup) {
            ItemGroup group = (ItemGroup)item;
            group.removeObserver(this);
            graph.removeOccupants(group.getItems());
        }
    }

    @Override
    public void itemsCleared() {
        graph.clearOccupants();
    }
}
