package com.evilbird.engine.item;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.evilbird.engine.utility.Predicate;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ItemGroup extends Item implements GroupObserver
{
    private Collection<Item> items;

    public ItemGroup()
    {
        this.items = new ArrayList<Item>();
    }

    protected Actor initializeDelegate()
    {
        return new GroupExtension(this);
    }

    public void addItem(Item item)
    {
        Group group = (Group)delegate;
        group.addActor(item.delegate);
        item.setParent(this);
        item.setRoot(getRoot());
        items.add(item);
    }

    public void removeItem(Item item)
    {
        Group group = (Group)delegate;
        group.removeActor(item.delegate);
        items.remove(item);
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

    public Item hit(Vector2 coordinates, boolean touchable)
    {
        Actor actor = delegate.hit(coordinates.x, coordinates.y, touchable);
        if (actor != null){
            Item item = (Item) actor.getUserObject();
            return item;
        }
        return null;
    }

    public Item find(Predicate<Item> predicate)
    {
        return find(this, predicate);
    }

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
