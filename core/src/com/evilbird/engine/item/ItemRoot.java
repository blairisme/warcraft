package com.evilbird.engine.item;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.evilbird.engine.utility.Predicate;

import java.util.Collection;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ItemRoot
{
    private Stage delegate;
    private ItemGroup group;

    public ItemRoot()
    {
        this.delegate = new Stage();
        this.group = new ItemGroup();
        this.group.setRoot(this);
        this.delegate.addActor(group.delegate);
    }

    public Item find(Predicate<Item> predicate)
    {
        return group.find(predicate);
    }

    public Collection<Item> findAll(Predicate<Item> predicate)
    {
        return group.findAll(predicate);
    }

    public void addItem(Item item)
    {
        this.group.addItem(item);
    }

    public void removeItem(Item item)
    {
        this.group.removeItem(item);
    }

    public void draw()
    {
        delegate.draw();
    }

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
     * @param coordinates   The screen coordinates to convert into world coordinates.
     * @return              The screen coordinates that were passed in, transformed into world
     *                      coordinates.
     */
    public Vector2 unproject(Vector2 coordinates)
    {
        return delegate.screenToStageCoordinates(coordinates);
    }

    /**
     * Transforms the given world coordinates into screen coordinates.
     *
     * @param coordinates   The world coordinates to convert into screen coordinates.
     * @return              The world coordinates that were passed in, transformed into screen
     *                      coordinates.
     */
    public Vector2 project(Vector2 coordinates)
    {
        return delegate.stageToScreenCoordinates(coordinates);
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
