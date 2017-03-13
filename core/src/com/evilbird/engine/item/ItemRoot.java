package com.evilbird.engine.item;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.evilbird.engine.common.function.Predicate;

import java.util.Collection;

/**
 * Instances of this class represent the root node in the item graph.
 *
 * @author Blair Butterworth
 */
public class ItemRoot implements ItemComposite
{
    private Stage delegate;
    private ItemGroup group;

    /**
     * Constructs a new instance of this class.
     */
    public ItemRoot()
    {
        this.delegate = new Stage();
        this.group = new ItemGroup();
        this.group.setRoot(this);
        this.delegate.addActor(group.delegate);
    }

    /**
     * Adds an {@link Item} as a child of the item root.
     *
     * @param item  the item to add.
     */
    @Override
    public void addItem(Item item)
    {
        this.group.addItem(item);
    }

    /**
     * Removes an {@link Item} from the item root.
     *
     * @param item  the item to remove.
     */
    @Override
    public void removeItem(Item item)
    {
        this.group.removeItem(item);
    }

    /**
     * Removes all {@link Item}s from the item root.
     */
    @Override
    public void clearItems()
    {
        this.group.clearItems();
    }

    /**
     * Returns a collection containing the children of the ItemComposite.
     *
     * @return the children of the ItemGroup.
     */
    @Override
    public Collection<Item> getItems()
    {
        return this.group.getItems();
    }

    /**
     * Returns the first child {@link Item} that satisfies the given {@link Predicate}.
     *
     * @param predicate a predicate implementation used to differentiate between items.
     * @return          a child item satisfying the given predicate.
     */
    public Item find(Predicate<Item> predicate)
    {
        return group.find(predicate);
    }

    /**
     * Returns the all child {@link Item}s that satisfy the given {@link Predicate}.
     *
     * @param predicate a predicate implementation used to differentiate between items.
     * @return          all child items satisfying the given predicate.
     */
    public Collection<Item> findAll(Predicate<Item> predicate)
    {
        return group.findAll(predicate);
    }

    /**
     * Draws the item graph.
     */
    public void draw()
    {
        delegate.draw();
    }

    /**
     * Updates every item in the item graph.
     *
     * @param delta the time between this frame and the last frame.
     */
    public void update(float delta)
    {
        delegate.getCamera().update();
        delegate.act(delta);
    }

    public Viewport getViewport()
    {
        return delegate.getViewport();
    }

    public void setViewport(Viewport viewport)
    {
        delegate.setViewport(viewport);
    }

    public void resize(int width, int height)
    {
        delegate.getViewport().update(width, height);
    }

    public void dispose()
    {
        delegate.dispose();
    }

    /**
     * Transforms the given screen coordinates into world coordinates.
     *
     * @param coordinates   The screen coordinates to convert.
     * @return              The given screen coordinates transformed into world coordinates.
     */
    public Vector2 unproject(Vector2 coordinates)
    {
        Vector2 result = new Vector2(coordinates);
        return delegate.screenToStageCoordinates(result);
    }

    /**
     * Transforms the given world coordinates into screen coordinates.
     *
     * @param coordinates   The world coordinates to convert.
     * @return              The given world coordinates transformed into screen coordinates.
     */
    public Vector2 project(Vector2 coordinates)
    {
        Vector2 result = new Vector2(coordinates);
        return delegate.stageToScreenCoordinates(result);
    }

    /**
     * Returns the {@link Item} at the specified location in world coordinates. Hit testing is
     * performed in the order the item were inserted into the root, last inserted actors being
     * tested first. To get world coordinates from screen coordinates, use {@link #unproject(Vector2)}.
     *
     * @param coordinates   the world coordinates to test.
     * @param touchable     specifies if hit detection will respect the items touchability.
     * @return              the item at the specified location or null if no item is located there.
     */
    public Item hit(Vector2 coordinates, boolean touchable)
    {
        return group.hit(coordinates, touchable);
    }
}
