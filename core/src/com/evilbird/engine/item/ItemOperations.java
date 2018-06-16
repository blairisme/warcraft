package com.evilbird.engine.item;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.collection.Collections;
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.common.function.Predicates;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.warcraft.item.unit.UnitType;

import java.util.Collection;

import static com.badlogic.gdx.Gdx.graphics;
import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.engine.item.ItemComparators.closestItem;
import static com.evilbird.engine.item.ItemPredicates.itemWithType;
import static com.evilbird.engine.item.ItemPredicates.selectableItem;

public class ItemOperations
{
    public static Item findClosest(ItemComposite itemGroup, Identifier type, Item locus) {
        Predicate<Item> selector = both(itemWithType(type), selectableItem());
        Collection<Item> itemsWithType = itemGroup.findAll(selector);
        if (! itemsWithType.isEmpty()) {
            return Collections.min(itemsWithType, closestItem(locus));
        }
        return null;
    }

    public static Item findClosest(Item item) {
        if (! item.getSelectable()){
            return findClosest(item.getParent(), item.getType(), item);
        }
        return item;
    }

    public static Item findAncestorByType(Item item, Identifier type) {
        Item parent = item.getParent();

        while (parent != null) {
            if (Objects.equals(parent.getType(), type)) {
                return parent;
            }
            parent = parent.getParent();
        }
        throw new IllegalStateException("Illegal item hierarchy - missing player");
    }

    public static Vector2 getScreenCenter(ItemRoot root)
    {
        float x = graphics.getWidth() * 0.5f;
        float y = graphics.getHeight() * 0.5f;
        Vector2 screenCenter = new Vector2(x, y);
        return root.unproject(screenCenter);
    }
}
