/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.utility;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.math.ShapeUtilities;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;

import java.util.function.Predicate;

import static com.badlogic.gdx.Gdx.graphics;
import static com.evilbird.engine.item.utility.ItemPredicates.itemWithType;

/**
 * Instances of this class contain common functions for working with
 * {@link Item Items}.
 *
 * @author Blair Butterworth
 */
public class ItemOperations
{
    private ItemOperations(){
    }

    public static Item findAncestor(Item item, Predicate<Item> predicate) {
        Item parent = item.getParent();
        while (parent != null) {
            if (predicate.test(parent)) {
                return parent;
            }
            parent = parent.getParent();
        }
        return null;
    }

    public static Item findAncestorByType(Item item, Identifier type) {
        return findAncestor(item, itemWithType(type));
    }

    public static Vector2 getScreenCenter(Item item) {
        return getScreenCenter(item.getRoot());
    }

    public static Vector2 getScreenCenter(ItemRoot root) {
        float x = graphics.getWidth() * 0.5f;
        float y = graphics.getHeight() * 0.5f;
        Vector2 screenCenter = new Vector2(x, y);
        return root.unproject(screenCenter);
    }

    public static boolean isIdle(Item item) {
        return !item.hasActions();
    }

    public static boolean isNear(Item locus, float radius, Item target) {
        Circle perimeter = new Circle(locus.getPosition(Alignment.Center), radius);
        return ShapeUtilities.contains(perimeter, target.getBounds());
    }

    public static boolean overlaps(Item itemA, Item itemB) {
        Rectangle rectangleA = itemA.getBounds();
        Rectangle rectangleB = itemB.getBounds();
        return rectangleA.overlaps(rectangleB);
    }
}
