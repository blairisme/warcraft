/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.common.function.Supplier;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.common.math.ShapeUtilities;

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

    public static <T extends Item> Predicate<T> itemWithClass(final Class<T> clazz) {
        return (item) -> clazz.isAssignableFrom(item.getClass());
    }

    public static Predicate<Item> itemWithId(final Identifier id) {
        return (item) -> id.equals(item.getIdentifier());
    }

    public static Predicate<Item> itemWithType(final Identifier type) {
        return (item) -> type.equals(item.getType());
    }

    public static Predicate<Item> itemWithAction() {
        return Item::hasActions;
    }

    public static Predicate<Item> selectedItem() {
        return Item::getSelected;
    }

    public static Predicate<Item> selectableItem() {
        return Item::getSelectable;
    }

    public static Predicate<Item> isIdle() {
        return (item) -> !item.hasActions();
    }

    public static Predicate<Item> isOwnedBy(final Item owner) {
        return (item) -> Objects.equals(owner, item.getParent());
    }

    public static Predicate<Item> isNear(Item item, float radius) {
        return new IsNear(item, radius);
    }

    public static Predicate<Item> isNear(Supplier<? extends Item> itemSupplier, Supplier<Float> radiusSupplier) {
        return new IsNearDeferred(itemSupplier, radiusSupplier);
    }

    private static class IsNear implements Predicate<Item> {
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

    private static class IsNearDeferred implements Predicate<Item> {
        private Supplier<? extends Item> locusSupplier;
        private Supplier<Float> radiusSupplier;

        private IsNearDeferred(Supplier<? extends Item> locusSupplier, Supplier<Float> radiusSupplier) {
            this.locusSupplier = locusSupplier;
            this.radiusSupplier = radiusSupplier;
        }

        @Override
        public boolean test(Item target) {
            Item locus = locusSupplier.get();
            Float radius = radiusSupplier.get();
            Vector2 center = locus.getPosition(Alignment.Center);
            Circle perimeter = new Circle(center, radius);
            return ShapeUtilities.contains(perimeter, target.getBounds());
        }
    }
}
