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
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.math.ShapeUtilities;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;

import java.util.function.Predicate;

/**
 * Defines commonly used {@link Predicate Predicates} that operate on
 * {@link Item Items}.
 *
 * @author Blair Butterworth
 */
public class ItemPredicates
{
    private ItemPredicates() {
        throw new UnsupportedOperationException();
    }

    public static Predicate<Item> itemWithId(final Identifier id) {
        return (item) -> item != null && id.equals(item.getIdentifier());
    }

    public static Predicate<Item> itemWithType(final Identifier type) {
        return (item) -> item != null && type.equals(item.getType());
    }

    public static Predicate<Item> withType(final Identifier type) {
        return itemWithType(type);
    }

    public static Predicate<Item> withClazz(final Class<?> type) {
        return (item) -> item != null && type.isAssignableFrom(item.getClass());
    }

    public static Predicate<Item> touchableWithType(Identifier type) {
        return item -> item != null && item.getTouchable() && type.equals(item.getType());
    }

    public static Predicate<Item> isNear(Item item, float radius) {
        return new IsNear(item, radius);
    }

    public static class IsNear implements Predicate<Item>
    {
        private Item locus;
        private Circle perimeter;

        private IsNear(Item item, float radius) {
            locus = item;
            perimeter = new Circle(item.getPosition(), radius);
        }

        @Override
        public boolean test(Item target) {
            perimeter.setPosition(locus.getPosition(Alignment.Center));
            return ShapeUtilities.contains(perimeter, target.getBounds());
        }
    }

    public static Predicate<ItemRoot> hasMinimum(Predicate<Item> condition, int count) {
        return (composite) -> composite.findAll(condition).size() >= count;
    }

    public static Predicate<ItemRoot> hasMaximum(Predicate<Item> condition, int count) {
        return (composite) -> composite.findAll(condition).size() <= count;
    }

    public static Predicate<Item> hasType(Identifier type) {
        return item -> item != null && item.getType() == type;
    }

    public static Predicate<UserInput> hasType(UserInputType type) {
        return input -> input != null && input.getType() == type;
    }

    public static Predicate<Action> hasType(ActionIdentifier type) {
        return action -> action != null && action.getIdentifier() == type;
    }

    public static Predicate<Item> hasAction(Predicate<Action> condition) {
        return item -> item != null && item.getActions().stream().anyMatch(condition);
    }

    public static Predicate<Item> hasAction(Identifier identifier) {
        return item -> item != null && item.getActions().stream().anyMatch(
            action -> action.getIdentifier() == identifier);
    }

    public static Predicate<Item> overlapping(Item target) {
        return overlapping(target.getBounds());
    }

    public static Predicate<Item> overlapping(Rectangle target) {
        return item -> item != null && target.overlaps(item.getBounds());
    }

    public static Predicate<Item> touchable() {
        return item -> item != null && item.getTouchable();
    }
}
