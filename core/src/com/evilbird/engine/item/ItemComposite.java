package com.evilbird.engine.item;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.function.Predicate;

import java.util.Collection;

/**
 * Implementors of this TODO:Finish
 *
 * @author Blair Butterworth
 */
public interface ItemComposite
{
    /**
     * Adds an {@link Item} as a child of the ItemComposite.
     *
     * @param item  the item to add.
     */
    void addItem(Item item);

    /**
     * Removes an {@link Item} from the ItemComposite.
     *
     * @param item  the item to remove.
     */
    void removeItem(Item item);

    /**
     * Removes all {@link Item}s from the ItemComposite.
     */
    void clearItems();

    /**
     * Returns a collection containing the children of the ItemComposite.
     *
     * @return the children of the ItemGroup.
     */
    Collection<Item> getItems();

    /**
     * Returns the first child {@link Item} that satisfies the given {@link Predicate}.
     *
     * @param predicate a predicate implementation used to differentiate between items.
     * @return          a child item satisfying the given predicate.
     */
    Item find(Predicate<Item> predicate);

    /**
     * Returns the all child {@link Item}s that satisfy the given {@link Predicate}.
     *
     * @param predicate a predicate implementation used to differentiate between items.
     * @return          all child items satisfying the given predicate.
     */
    Collection<Item> findAll(Predicate<Item> predicate);

    /**
     * Returns the {@link Item} at the specified location in world coordinates. Hit testing is
     * performed in the order the item were inserted into the root, last inserted actors being
     * tested first.
     *
     * @param coordinates   the world coordinates to test.
     * @param touchable     specifies if hit detection will respect the items touchability.
     * @return              the item at the specified location or null if no item is located there.
     */
    Item hit(Vector2 coordinates, boolean touchable);
}
