package com.evilbird.engine.item;

import com.evilbird.engine.common.collection.Collections;
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.common.function.Predicates;
import com.evilbird.engine.common.lang.Identifier;
import java.util.Collection;

import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.engine.item.ItemComparators.closestItem;
import static com.evilbird.engine.item.ItemPredicates.itemWithType;
import static com.evilbird.engine.item.ItemPredicates.selectableItem;

public class ItemOperations
{
    public static Item findClosest(ItemGroup itemGroup, Identifier type, Item locus)
    {
        Predicate<Item> selector = both(itemWithType(type), selectableItem());
        Collection<Item> itemsWithType = itemGroup.findAll(selector);
        if (! itemsWithType.isEmpty()) {
            return Collections.min(itemsWithType, closestItem(locus));
        }
        return null;
    }

    public static Item findClosest(Item item)
    {
        if (! item.getSelectable()){
            return findClosest(item.getParent(), item.getType(), item);
        }
        return item;
    }
}
