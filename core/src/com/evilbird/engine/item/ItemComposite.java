/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

import com.badlogic.gdx.math.Vector2;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * Implementors of this interface represent a container for a collection of
 * {@link Item Items}.
 *
 * @author Blair Butterworth
 */
public interface ItemComposite
{
    /**
     * Adds an {@link Item} as a child of the ItemComposite.
     *
     * @param item  the item to set.
     */
    void addItem(Item item);

    /**
     * Returns a collection containing the children of the ItemComposite.
     *
     * @return the children of the ItemGroup.
     */
    Collection<Item> getItems();

    /**
     * Determines whether the given {@link Item} is contained in the {@code
     * ItemComposite}: its one of its children.
     *
     * @param item  the {@code Item} to search for. This parameter cannot be
     *              {@code null}.
     *
     * @return  {@code true} if the given {@code Item} is contained in the
     *          {@code ItemComposite}
     */
    boolean containsItem(Item item);

    /**
     * Removes all {@link Item}s from the ItemComposite.
     */
    void clearItems();

    /**
     * Removes an {@link Item} from the ItemComposite.
     *
     * @param item  the item to remove.
     */
    void removeItem(Item item);

    /**
     * Returns the first child {@link Item} that satisfies the given
     * {@link Predicate}.
     *
     * @param predicate a predicate implementation used to differentiate
     *                  between items.
     * @return          a child item satisfying the given predicate.
     */
    Item find(Predicate<Item> predicate);

    /**
     * Returns the all child {@link Item}s that satisfy the given
     * {@link Predicate}.
     *
     * @param predicate a predicate implementation used to differentiate
     *                  between items.
     * @return          all child items satisfying the given predicate.
     */
    <T extends Item> Collection<T> findAll(Predicate<T> predicate);

    /**
     * Returns the {@link Item} at the specified location in world
     * coordinates. Hit testing is performed in the order the item were
     * inserted into the root, last inserted actors being tested first.
     *
     * @param coordinates   the world coordinates to test.
     * @param touchable     specifies if hit detection will respect the items
     *                      touchability.
     * @return              the item at the specified location or null if no
     *                      item is located there.
     */
    Item hit(Vector2 coordinates, boolean touchable);
}
