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
 * Instances of this class represent a node in the item graph that contains other items.
 *
 * @author Blair Butterworth
 */
public class ItemGroup extends Item implements GroupObserver, ItemComposite
{
    private List<Item> items;

    /**
     * Constructs a new instance of this class.
     */
    public ItemGroup()
    {
        this.items = new ArrayList<Item>();
    }

    protected Actor initializeDelegate()
    {
        return new GroupExtension(this);
    }

    /**
     * Adds an {@link Item} as a child of this Group.
     *
     * @param item  the item to add.
     */
    public void addItem(Item item)
    {
        Group group = (Group)delegate;
        group.addActor(item.delegate);
        item.setParent(this);
        item.setRoot(getRoot());
        items.add(item);
    }

    /**
     * Removes an {@link Item} from this group.
     *
     * @param item  the item to remove.
     */
    public void removeItem(Item item)
    {
        //Group group = (Group)delegate;
        //group.removeActor(item.delegate);

        item.delegate.remove();
        items.remove(item);
    }

    /**
     * Removes all {@link Item}s from this group.
     */
    public void clearItems()
    {
        Group group = (Group)delegate;
        group.clearChildren();
        items.clear();
    }

    /**
     * Returns a collection containing the children of the ItemGroup.
     *
     * @return the children of the ItemGroup.
     */
    public Collection<Item> getItems()
    {
        return items;
    }

    /**
     * Returns the {@link Item} at the specified location in world coordinates. Hit testing is
     * performed in the order the item were inserted into the group, last inserted actors being
     * tested first.
     *
     * @param coordinates   the world coordinates to test.
     * @param touchable     specifies if hit detection will respect the items touchability.
     * @return              the item at the specified location or null if no item is located there.
     */
    @Override
    public Item hit(Vector2 coordinates, boolean touchable)
    {
        if (touchable && delegate.getTouchable() == Touchable.disabled) return null;
        for (int itemIndex = items.size() - 1; itemIndex >= 0; itemIndex--){
            Item item = items.get(itemIndex);
            Vector2 localCoordinates = item.parentToLocalCoordinates(coordinates);
            Item hit = item.hit(localCoordinates, touchable);
            if (hit != null){
                return hit;
            }
        }
        return super.hit(coordinates, touchable);
    }

    /**
     * Returns the first child {@link Item} that satisfies the given {@link Predicate}.
     *
     * @param predicate a predicate implementation used to differentiate between items.
     * @return          a child item satisfying the given predicate.
     */
    public Item find(Predicate<Item> predicate)
    {
        return find(this, predicate);
    }

    /**
     * Returns the all child {@link Item}s that satisfy the given {@link Predicate}.
     *
     * @param predicate a predicate implementation used to differentiate between items.
     * @return          all child items satisfying the given predicate.
     */
    public Collection<Item> findAll(Predicate<Item> predicate)
    {
        return findAll(this, predicate);
    }

    private Item find(ItemGroup group, Predicate<Item> predicate)
    {
        for (Item item: group.items){
            if (predicate.test(item)){
                return item;
            }
            if (item instanceof ItemGroup){
                Item result = find((ItemGroup)item, predicate);
                if (result != null){
                    return result;
                }
            }
        }
        return null;
    }

    private Collection<Item> findAll(ItemGroup group, Predicate<Item> predicate)
    {
        Collection<Item> result = new ArrayList<Item>();
        for (Item item: group.items){
            if (predicate.test(item)){
                result.add(item);
            }
            if (item instanceof ItemGroup){
                result.addAll(findAll((ItemGroup)item, predicate));
            }
        }
        return result;
    }
}
