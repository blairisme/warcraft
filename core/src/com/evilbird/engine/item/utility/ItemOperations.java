/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.utility;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.math.ShapeUtilities;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.engine.item.ItemRoot;

import java.util.Objects;
import java.util.function.Predicate;

import static com.badlogic.gdx.Gdx.graphics;

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

    /**
     * Searches the {@link Item} hierarchy (Items are owned other Items) for
     * the first Item that matches the given {@link Predicate}, inclusive of
     * the Item.
     *
     * @param item      an {@code Item} whose hierarchy will be tested using
     *                  the given condition.
     * @param predicate a {@code Predicate} to test for.
     *
     * @return  the first parent of the given Item that matches the specified
     *          condition, or {@code null} if no match is found.
     */
    public static Item findAncestor(Item item, Predicate<Item> predicate) {
        Objects.requireNonNull(item);
        Objects.requireNonNull(predicate);

        Item parent = item;
        while (parent != null) {
            if (predicate.test(parent)) {
                return parent;
            }
            parent = parent.getParent();
        }
        return null;
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

    public static boolean hasAction(Item item, Predicate<Action> condition) {
        return item != null && item.getActions().stream().anyMatch(condition);
    }

    public static boolean hasAction(Item item, Identifier identifier) {
        return hasAction(item, action -> action.getIdentifier() == identifier);
    }

    public static boolean isIdle(Item item) {
        return !item.hasActions();
    }

    public static boolean isNear(Item locus, float radius, Item target) {
        return isNear(locus, radius, target.getBounds());
    }

    public static boolean isNear(Item locus, float radius, Rectangle target) {
        Vector2 position = locus.getPosition(Alignment.Center);
        float size = locus.getSize().x * 0.5f + radius;
        Circle perimeter = new Circle(position, size);
        return ShapeUtilities.contains(perimeter, target);
    }

    public static boolean overlaps(Item itemA, Item itemB) {
        Rectangle rectangleA = itemA.getBounds();
        Rectangle rectangleB = itemB.getBounds();
        return rectangleA.overlaps(rectangleB);
    }

    public static boolean hasMinimum(ItemComposite composite, Predicate<Item> condition, int count) {
        return composite.findAll(condition).size() >= count;
    }

    public static boolean hasMaximum(ItemComposite composite, Predicate<Item> condition, int count) {
        return composite.findAll(condition).size() <= count;
    }

    public static boolean hasAny(ItemComposite composite, Predicate<Item> condition) {
        return composite.findAll(condition).size() > 0;
    }

    public static boolean hasNone(ItemComposite composite, Predicate<Item> condition) {
        return composite.findAll(condition).size() == 0;
    }
}
