package com.evilbird.engine.item;

import com.evilbird.engine.common.collection.Collections;
import com.evilbird.engine.common.lang.Identifier;
import java.util.Collection;

import static com.evilbird.engine.item.ItemComparators.closestItem;
import static com.evilbird.engine.item.ItemPredicates.itemWithType;

public class ItemOperations
{
    public static Item findClosest(ItemGroup itemGroup, Identifier type, Item locus)
    {
        Collection<Item> depots = itemGroup.findAll(itemWithType(type));
        return Collections.min(depots, closestItem(locus));
    }
}
