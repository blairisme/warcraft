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
import com.evilbird.engine.common.math.ShapeUtils;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.engine.item.ItemRoot;
import org.apache.commons.lang3.Validate;

import java.util.Collection;
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
     * Assigns an {@link Action} to the given {@link Item} if it hasn't already
     * been assigned. If the {@code Action} hasn't been assigned, any existing
     * {@code Actions} will be removed prior to assigning the new
     * {@code Action}.
     *
     * @param item      an {@code Item} that will receive the {@link Action}.
     * @param action    the {@link Action} that will be assigned.
     *
     * @throws NullPointerException if either the given {@code Item} or
     *                              {@code Action} is {@code null}.
     */
    public static void assignIfAbsent(Item item, Action action) {
        Objects.requireNonNull(item);
        Objects.requireNonNull(action);

        if (!hasAction(item, action)) {
            action.reset();
            action.setItem(item);
            item.clearActions();
            item.addAction(action);
        }
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
        Objects.requireNonNull(item);
        Objects.requireNonNull(condition);
        return item.getActions().stream().anyMatch(condition);
    }

    public static boolean hasAction(Item item, Identifier identifier) {
        Objects.requireNonNull(identifier);
        return hasAction(item, action -> action.getIdentifier() == identifier);
    }

    public static boolean hasAction(Item item, Action action) {
        Objects.requireNonNull(action);
        return hasAction(item, action.getIdentifier());
    }

    public static boolean isIdle(Item item) {
        return !item.hasActions();
    }

    public static boolean isIdle(Item item, Class<?> allowed) {
        Collection<Action> actions = item.getActions();
        if (actions.isEmpty()) {
            return true;
        }
        if (actions.size() == 1) {
            Action action = actions.iterator().next();
            return action.getIdentifier().getClass().isAssignableFrom(allowed);
        }
        return false;
    }

    /**
     * Determines if an {@link Item} is within the given radius of another
     * {@code Item}.
     *
     * @param locus     the {@code Item} at the center of the radius.
     * @param radius    the maximum distance the target can be away from the
     *                  central item.
     * @param target    the {@code Item} whose nearness is being tests.
     * @return          {@code true} if the items are near each other.
     *
     * @throws NullPointerException     if either the locus or target items are
     *                                  {@code null}.
     * @throws IllegalArgumentException if the given radius is negative.
     */
    public static boolean isNear(Item locus, float radius, Item target) {
        Validate.notNull(locus);
        Validate.notNull(target);
        Validate.isTrue(radius >= 0);

        Rectangle bounds = target.getBounds();
        Vector2 position = locus.getPosition(Alignment.Center);
        Circle perimeter = new Circle(position, radius);

        return ShapeUtils.contains(perimeter, bounds);
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
