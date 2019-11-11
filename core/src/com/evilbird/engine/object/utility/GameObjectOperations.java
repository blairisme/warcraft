/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.object.utility;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectComposite;
import com.evilbird.engine.object.GameObjectContainer;
import org.apache.commons.lang3.Validate;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

import static com.badlogic.gdx.Gdx.graphics;

/**
 * Instances of this class contain common functions for working with
 * {@link GameObject Items}.
 *
 * @author Blair Butterworth
 */
public class GameObjectOperations
{
    private GameObjectOperations(){
    }

    /**
     * Assigns an {@link Action} to the given {@link GameObject} if it hasn't already
     * been assigned. If the {@code Action} hasn't been assigned, any existing
     * {@code Actions} will be removed prior to assigning the new
     * {@code Action}.
     *
     * @param gameObject      an {@code Item} that will receive the {@link Action}.
     * @param action    the {@link Action} that will be assigned.
     *
     * @throws NullPointerException if either the given {@code Item} or
     *                              {@code Action} is {@code null}.
     */
    public static void assignIfAbsent(GameObject gameObject, Action action) {
        Objects.requireNonNull(gameObject);
        Objects.requireNonNull(action);

        if (!hasAction(gameObject, action)) {
            action.reset();
            action.setItem(gameObject);
            gameObject.clearActions();
            gameObject.addAction(action);
        }
    }

    /**
     * Searches the {@link GameObject} hierarchy (Items are owned other Items) for
     * the first Item that matches the given {@link Predicate}, inclusive of
     * the Item.
     *
     * @param gameObject      an {@code Item} whose hierarchy will be tested using
     *                  the given condition.
     * @param predicate a {@code Predicate} to test for.
     *
     * @return  the first parent of the given Item that matches the specified
     *          condition, or {@code null} if no match is found.
     *
     * @throws NullPointerException if the given {@code Item} or
     *                              {@code Predicate} is {@code null}.
     */
    public static GameObject findAncestor(GameObject gameObject, Predicate<GameObject> predicate) {
        Objects.requireNonNull(gameObject);
        Objects.requireNonNull(predicate);

        GameObject parent = gameObject;
        while (parent != null) {
            if (predicate.test(parent)) {
                return parent;
            }
            parent = parent.getParent();
        }
        return null;
    }

    public static Vector2 getScreenCenter(GameObject gameObject) {
        return getScreenCenter(gameObject.getRoot());
    }

    public static Vector2 getScreenCenter(GameObjectContainer root) {
        float x = graphics.getWidth() * 0.5f;
        float y = graphics.getHeight() * 0.5f;
        Vector2 screenCenter = new Vector2(x, y);
        return root.unproject(screenCenter);
    }

    public static boolean hasAction(GameObject gameObject, Predicate<Action> condition) {
        Objects.requireNonNull(gameObject);
        Objects.requireNonNull(condition);
        return gameObject.getActions().stream().anyMatch(condition);
    }

    public static boolean hasAction(GameObject gameObject, Identifier identifier) {
        Objects.requireNonNull(identifier);
        return hasAction(gameObject, action -> action.getIdentifier() == identifier);
    }

    public static boolean hasAction(GameObject gameObject, Collection<Identifier> identifiers) {
        Objects.requireNonNull(identifiers);
        return hasAction(gameObject, action -> identifiers.contains(action.getIdentifier()));
    }

    public static boolean hasAction(GameObject gameObject, Identifier[] identifiers) {
        Objects.requireNonNull(identifiers);
        return hasAction(gameObject, Arrays.asList(identifiers));
    }

    public static boolean hasAction(GameObject gameObject, Action action) {
        Objects.requireNonNull(action);
        return hasAction(gameObject, action.getIdentifier());
    }

    public static boolean hasIdStartingWith(GameObject gameObject, String id) {
        Identifier identifier = gameObject.getIdentifier();
        if (identifier != null) {
            return identifier.toString().startsWith(id);
        }
        return false;
    }

    public static boolean isIdle(GameObject gameObject) {
        return !gameObject.hasActions();
    }

    public static boolean isIdle(GameObject gameObject, Class<?> allowed) {
        Collection<Action> actions = gameObject.getActions();
        if (actions.isEmpty()) {
            return true;
        }
        if (actions.size() == 1) {
            Action action = actions.iterator().next();
            return action.getIdentifier().getClass().isAssignableFrom(allowed);
        }
        return false;
    }

    public static boolean isNear(GameObject locus, GameObject target) {
        return isNear(locus, locus.getWidth(), target);
    }

    /**
     * Determines if an {@link GameObject} is within the given radius of another
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
    public static boolean isNear(GameObject locus, float radius, GameObject target) {
        Validate.notNull(locus);
        Validate.notNull(target);
        Validate.isTrue(radius >= 0);
        return isNearCircular(locus, radius, target);
    }

    private static boolean isNearCircular(GameObject locus, float radius, GameObject target) {
        Vector2 targetSize = target.getSize();

        float targetDimension = Math.max(targetSize.x, targetSize.y);
        float targetRadius = targetDimension / 1.5f; //targetDimension / 2;

        Vector2 locusPosition = locus.getPosition(Alignment.Center);
        Vector2 targetPosition = target.getPosition(Alignment.Center);

        Circle locusPerimeter = new Circle(locusPosition.x, locusPosition.y, radius);
        Circle targetPerimeter = new Circle(targetPosition.x, targetPosition.y, targetRadius);

        return locusPerimeter.overlaps(targetPerimeter);
    }

    private static boolean isNearRectangular(GameObject locus, float radius, GameObject target) {
        float diameter = (radius * 2) + 5;
        Vector2 locusPosition = locus.getPosition();

        Rectangle locusBounds = new Rectangle(locusPosition.x, locusPosition.y, diameter, diameter);
        locusBounds.setCenter(locus.getPosition());

        Rectangle targetBounds = target.getBounds();
        return locusBounds.overlaps(targetBounds);
    }

    public static boolean overlaps(GameObject gameObjectA, GameObject gameObjectB) {
        Rectangle rectangleA = gameObjectA.getBounds();
        Rectangle rectangleB = gameObjectB.getBounds();
        return rectangleA.overlaps(rectangleB);
    }

    public static boolean overlaps(GameObject gameObject, Rectangle bounds) {
        Rectangle itemBounds = gameObject.getBounds();
        return itemBounds.overlaps(bounds);
    }

    public static boolean hasMinimum(GameObjectComposite composite, Predicate<GameObject> condition, int count) {
        return composite.findAll(condition).size() >= count;
    }

    public static boolean hasMaximum(GameObjectComposite composite, Predicate<GameObject> condition, int count) {
        return composite.findAll(condition).size() <= count;
    }

    public static boolean hasAny(GameObjectComposite composite, Predicate<GameObject> condition) {
        return composite.findAll(condition).size() > 0;
    }

    public static boolean hasNone(GameObjectComposite composite, Predicate<GameObject> condition) {
        return composite.findAll(condition).size() == 0;
    }
}
