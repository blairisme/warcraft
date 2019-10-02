/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.common.serialization.SerializedInitializer;
import com.evilbird.engine.item.interop.GroupDecorator;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

/**
 * Instances of this class represent a node in the item graph that contains
 * other items.
 *
 * @author Blair Butterworth
 */
public class ItemGroup extends ItemBasic implements ItemComposite
{
    protected boolean fill;
    protected List<Item> items;
    protected transient Collection<ItemGroupObserver> observers;

    public ItemGroup() {
        this.fill = false;
        this.items = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    /**
     * Adds an {@link Item} as a child of this Group.
     *
     * @param item the item to set.
     */
    public void addItem(Item item) {
        int index = Math.min(item.getZIndex(), items.size());
        Group group = (Group)delegate;
        group.addActorAt(index, item.toActor());
        item.setParent(this);
        item.setRoot(getRoot());
        items.add(index, item);
        observers.forEach(it -> it.itemAdded(item));
    }

    /**
     * Adds a collection of {@link Item Items} as children of the ItemComposite.
     *
     * @param items  the items to add.
     */
    @Override
    public void addItems(Collection<Item> items) {
        for (Item item: items) {
            addItem(item);
        }
    }

    /**
     * Removes an {@link Item} from this group.
     *
     * @param item the item to remove.
     */
    public void removeItem(Item item) {
        item.toActor().remove();
        items.remove(item);
        observers.forEach(it -> it.itemRemoved(item));
    }

    /**
     * Removes all {@link Item}s from this group.
     */
    public void clearItems() {
        Group group = (Group)delegate;
        group.clearChildren();
        items.clear();
        observers.forEach(ItemGroupObserver::itemsCleared);
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
     * Determines whether the given {@link Item} is contained in the {@code
     * ItemGroup}: its one of its children.
     *
     * @param item  the {@code Item} to search for. This parameter cannot be
     *              {@code null}.
     *
     * @return  {@code true} if the given {@code Item} is contained in the
     *          {@code ItemGroup}
     */
    public boolean containsItem(Item item) {
        return items.contains(item);
    }

    /**
     * Returns whether the ItemGroup has been assigned any child Items.
     *
     * @return <code>true</code> if the ItemGroup has child Items.
     */
    public boolean hasItems() {
        return !items.isEmpty();
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
        for (Item item: items) {
            if (item instanceof ItemGroup) {
                ItemGroup group = (ItemGroup)item;
                group.addObserver(observer);
            }
        }
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
        for (Item item: items) {
            if (item instanceof ItemGroup) {
                ItemGroup group = (ItemGroup)item;
                group.removeObserver(observer);
            }
        }
    }

    /**
     * Whether the ItemGroup should resize itself to fill its parent.
     *
     * @return <code>true</code> if the ItemGroup should fill its parent.
     */
    public boolean getFillParent() {
        return this.fill;
    }

    /**
     * Instructs the ItemGroup to resize to fill its parent.
     *
     * @param fill <code>true</code> if the ItemGroup should fill its parent.
     */
    public void setFillParent(boolean fill) {
        this.fill = fill;
    }

    /**
     * Sets the most distant ancestor of the Item in the Item hierarchy.
     *
     * @param root an {@link ItemRoot} instance.
     */
    @Override
    public void setRoot(ItemRoot root) {
        super.setRoot(root);
        items.forEach(item -> item.setRoot(root));
    }

    /**
     * Modifies the zIndex of the given {@link Item}. The zIndex defines at
     * items position in the rendering order and hit detection.
     *
     * @param item      the item whose zIndex will be modified.
     * @param zIndex    the new zIndex of the item.
     */
    public void setZIndex(Item item, int zIndex) {
        if (items.contains(item)) {
            int index = Math.min(zIndex, items.size());

            Group group = (Group) delegate;
            group.removeActor(item.toActor());
            group.addActorAt(index, item.toActor());

            items.remove(item);
            items.add(index, item);
        }
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
     *
     * @return  the item at the specified location. This method may return
     *          <code>null</code> if no item can be located.
     */
    @Override
    public Item hit(Vector2 coordinates, boolean respectTouchability) {
        if (respectTouchability && delegate.getTouchable() == Touchable.disabled) {
            return null;
        }
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
     *
     * @return  the item at the specified location. This method may return
     *          <code>null</code> if no item can be located.
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
     * Returns the first child {@link Item} that satisfies the given
     * {@link Predicate}.
     *
     * @param predicate a predicate implementation used to differentiate
     *                  between items.
     * @return a child item satisfying the given predicate.
     */
    public Item find(Predicate<Item> predicate) {
        return find(this, predicate);
    }

    private Item find(ItemComposite group, Predicate<Item> predicate) {
        for (Item item : group.getItems()) {
            if (predicate.test(item)) {
                return item;
            }
            if (item instanceof ItemComposite) {
                Item result = find((ItemComposite)item, predicate);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    /**
     * Returns the all child {@link Item}s that satisfy the given
     * {@link Predicate}.
     *
     * @param predicate a predicate implementation used to differentiate
     *                  between items.
     * @return all child items satisfying the given predicate.
     */
    public <T extends Item> Collection<T> findAll(Predicate<T> predicate) {
        return findAll(this, predicate);
    }

    @SuppressWarnings("unchecked")
    private <T extends Item> Collection<T> findAll(ItemComposite group, Predicate<T> predicate) {
        Collection<T> result = new ArrayList<>();
        for (Item item : group.getItems()) {
            if (predicate.test((T)item)) {
                result.add((T)item);
            }
            if (item instanceof ItemComposite) {
                result.addAll(findAll((ItemComposite)item, predicate));
            }
        }
        return result;
    }

    public void drawChildrenBegin(Batch batch, float alpha) {
    }

    public void drawChildrenEnd(Batch batch, float alpha) {
    }

    @Override
    public void update(float delta) {
        ItemGroup parent = getParent();
        if (fill && parent != null) {
            float parentWidth = parent.getWidth();
            float parentHeight = parent.getHeight();
            if (parentWidth != getWidth() || parentHeight != getHeight()) {
                setSize(parentWidth, parentHeight);
            }
        }
        super.update(delta);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper("group")
            .append("items", items)
            .append("fill", fill)
            .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        ItemGroup itemGroup = (ItemGroup)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(fill, itemGroup.fill)
            .append(items, itemGroup.items)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(fill)
            .append(items)
            .toHashCode();
    }

    @Override
    protected Actor newDelegate() {
        return new GroupDecorator(this);
    }

    @SerializedInitializer
    protected void updateDelegate() {
        super.updateDelegate();
        Group group = (Group)delegate;

        for (Item item: items) {
            group.addActor(item.toActor());
            item.setParent(this);
        }
    }
}
