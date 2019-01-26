/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.item.framework.GroupExtension;
import com.evilbird.engine.item.framework.GroupObserver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Instances of this class represent a node in the item graph that contains
 * other items.
 *
 * @author Blair Butterworth
 */
//TODO: Add caching to find
public class ItemGroup extends Item implements GroupObserver, ItemComposite
{
    protected List<Item> items;
    protected Collection<ItemGroupObserver> observers;

    /**
     * Constructs a new instance of this class.
     */
    public ItemGroup() {
        this.items = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    protected Actor initializeDelegate() {
        return new GroupExtension(this);
    }

    /**
     * Adds an {@link Item} as a child of this Group.
     *
     * @param item the item to set.
     */
    public void addItem(Item item) {
        Group group = (Group) delegate;
        group.addActor(item.delegate);
        item.setParent(this);
        item.setRoot(getRoot());
        items.add(item);
        notifyItemAdded(item);
    }

    /**
     * Removes an {@link Item} from this group.
     *
     * @param item the item to remove.
     */
    public void removeItem(Item item) {
        item.delegate.remove();
        items.remove(item);
        notifyItemRemoved(item);
    }

    /**
     * Removes all {@link Item}s from this group.
     */
    public void clearItems() {
        Group group = (Group) delegate;
        group.clearChildren();
        items.clear();
        notifyItemsCleared();
    }

    /**
     * Returns a collection containing the children of the ItemGroup.
     *
     * @return the children of the ItemGroup.
     */
    public Collection<Item> getItems() {
        return items;
    }

    /**
     * Adds an {@link ItemGroupObserver observer} to the collection of
     * observers that will be notified when an {@link Item} is added or
     * removed from the ItemGroup.
     *
     * @param observer an <code>ItemGroupObserver</code>.
     */
    public void addObserver(ItemGroupObserver observer) {
        this.observers.add(observer);
    }

    /**
     * Removes an {@link ItemGroupObserver observer} from the collection of
     * observers that are be notified when an {@link Item} is added or
     * removed from the ItemGroup.
     *
     * @param observer an <code>ItemGroupObserver</code>.
     */
    public void removeObserver(ItemGroupObserver observer) {
        this.observers.remove(observer);
    }

    /**
     * Returns the {@link Item} at the specified location in world coordinates.
     * Hit testing is performed in the order the item were inserted into the
     * group, last inserted actors being tested first, with the ItemGroup
     * itself tested last.
     *
     * @param coordinates         the world coordinates to test.
     * @param respectTouchability specifies if hit detection will respect the
     *                            items touchability.
     * @return the item at the specified location or null if no item is
     * located there.
     */
    @Override
    public Item hit(Vector2 coordinates, boolean respectTouchability) {
        if (respectTouchability && delegate.getTouchable() == Touchable.disabled) return null;
        Item childHit = childHit(coordinates, respectTouchability);
        if (childHit != null) {
            return childHit;
        }
        return super.hit(coordinates, respectTouchability);
    }

    /**
     * Returns the {@link Item} at the specified location in world coordinates.
     * Hit testing is performed only on the child items contained the ItemGroup
     * in the order the item were inserted into the group, last inserted actors
     * being tested first. This method does not respect the touchability of the
     * ItemGroup, only its children.
     *
     * @param coordinates         the world coordinates to test.
     * @param respectTouchability specifies if hit detection will respect the
     *                            items touchability.
     * @return the item at the specified location or null if no item is
     * located there.
     */
    protected Item childHit(Vector2 coordinates, boolean respectTouchability) {
        for (int itemIndex = items.size() - 1; itemIndex >= 0; itemIndex--) {
            Item item = items.get(itemIndex);
            Vector2 localCoordinates = item.parentToLocalCoordinates(coordinates);
            Item hit = item.hit(localCoordinates, respectTouchability);
            if (hit != null) {
                return hit;
            }
        }
        return null;
    }

    /**
     * Returns the first child {@link Item} that satisfies the given {@link Predicate}.
     *
     * @param predicate a predicate implementation used to differentiate between items.
     * @return a child item satisfying the given predicate.
     */
    public Item find(Predicate<Item> predicate) {
        return find(this, predicate);
    }

    /**
     * Returns the all child {@link Item}s that satisfy the given {@link Predicate}.
     *
     * @param predicate a predicate implementation used to differentiate between items.
     * @return all child items satisfying the given predicate.
     */
    public <T extends Item> Collection<T> findAll(Predicate<T> predicate) {
        return findAll(this, predicate);
    }

    private Item find(ItemGroup group, Predicate<Item> predicate) {
        for (Item item : group.items) {
            if (predicate.test(item)) {
                return item;
            }
            if (item instanceof ItemGroup) {
                Item result = find((ItemGroup) item, predicate);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private <T extends Item> Collection<T> findAll(ItemGroup group, Predicate<T> predicate) {
        Collection<T> result = new ArrayList<>();
        for (Item item : group.items) {
            if (predicate.test((T)item)) {
                result.add((T)item);
            }
            if (item instanceof ItemGroup) {
                result.addAll(findAll((ItemGroup) item, predicate));
            }
        }
        return result;
    }

    private void notifyItemAdded(Item item) {
        for (ItemGroupObserver observer: observers) {
            observer.itemAdded(item);
        }
    }

    private void notifyItemRemoved(Item item) {
        for (ItemGroupObserver observer: observers) {
            observer.itemRemoved(item);
        }
    }

    private void notifyItemsCleared() {
        for (ItemGroupObserver observer: observers) {
            observer.itemsCleared();
        }
    }
}
