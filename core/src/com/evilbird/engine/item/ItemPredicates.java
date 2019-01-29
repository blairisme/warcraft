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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.common.math.ShapeUtilities;

/**
 * Instances of this class provide commonly used {@link Predicate Predicates}
 * that operate on {@link Item Items}.
 *
 * @author Blair Butterworth
 */
public class ItemPredicates
{
    private ItemPredicates() {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("unchecked")
    public static <T extends Item> Predicate<T> itemWithClass(Class<T> clazz) {
        return new ItemWithClass(clazz);
    }

    public static class ItemWithClass <T extends Item> implements Predicate<T> {
        private Class<T> clazz;

        public ItemWithClass(Class<T> clazz) {
            this.clazz = clazz;
        }

        @Override
        public boolean test(T item) {
            return clazz.isAssignableFrom(item.getClass());
        }
    }

    public static Predicate<Item> itemWithId(Identifier id) {
        return new ItemWithId(id);
    }

    public static class ItemWithId implements Predicate<Item> {
        private Identifier id;

        public ItemWithId(Identifier id) {
            this.id = id;
        }

        @Override
        public boolean test(Item item) {
            return id.equals(item.getIdentifier());
        }
    }

    public static Predicate<Item> itemWithType(Identifier type) {
        return new ItemWithType(type);
    }

    public static class ItemWithType implements Predicate<Item> {
        private Identifier type;

        public ItemWithType(Identifier type) {
            this.type = type;
        }

        @Override
        public boolean test(Item item) {
            return type.equals(item.getType());
        }
    }

    public static Predicate<Item> itemWithAction() {
        return new ItemWithAction();
    }

    public static class ItemWithAction implements Predicate<Item> {
        @Override
        public boolean test(Item item) {
            return item.hasActions();
        }
    }

    public static Predicate<Item> itemWithOwner(Identifier id) {
        return new ItemWithOwner(id);
    }

    public static class ItemWithOwner implements Predicate<Item> {
        private Identifier id;

        public ItemWithOwner(Identifier id) {
            this.id = id;
        }

        @Override
        public boolean test(Item item) {
            Item parent = item.getParent();
            return Objects.equals(parent.getIdentifier(), id);
        }
    }

    public static Predicate<Item> selectedItem() {
        return new SelectedItem();
    }

    public static class SelectedItem implements Predicate<Item> {
        @Override
        public boolean test(Item item) {
            return item.getSelected();
        }
    }

    public static Predicate<Item> selectableItem() {
        return new SelectableItem();
    }

    public static class SelectableItem implements Predicate<Item> {
        @Override
        public boolean test(Item item) {
            return item.getSelectable();
        }
    }

    public static Predicate<Item> isIdle() {
        return new Predicate<Item>() {
            @Override
            public boolean test(Item item) {
                return !item.hasActions();
            }
        };
    }

    public static Predicate<Item> isOwnedBy(Item ownerA) {
        return new Predicate<Item>() {
            @Override
            public boolean test(Item item) {
                Item ownerB = item.getParent();
                return Objects.equals(ownerA, ownerB);
            }
        };
    }

    public static Predicate<Item> isNear(Item item, float radius) {
        return new IsNear(item, radius);
    }

//    private static class IsNear implements Predicate<Item> {
//        private Item locus;
//        private Rectangle perimeter;
//
//        public IsNear(Item item, float radius) {
//            locus = item;
//            perimeter = scale(item.getBounds(), radius);
//        }
//
//        @Override
//        public boolean test(Item target) {
//            perimeter.setCenter(locus.getPosition(Alignment.Center));
//            return perimeter.contains(target.getBounds());
//        }
//
//        private Rectangle scale(Rectangle rectangle, float factor) {
//            Vector2 center = rectangle.getCenter(new Vector2());
//            Vector2 size = rectangle.getSize(new Vector2());
//            Vector2 scaled = size.add(factor, factor);
//            rectangle.setSize(scaled.x, scaled.y);
//            rectangle.setCenter(center);
//            return rectangle;
//        }
//    }

    private static class IsNear implements Predicate<Item> {
        private Item locus;
        private Circle perimeter;

        public IsNear(Item item, float radius) {
            locus = item;
            perimeter = new Circle(item.getPosition(), radius);
        }

//        @Override
//        public boolean test(Item target) {
//            perimeter.setPosition(locus.getPosition(Alignment.Center));
//            return perimeter.contains(target.getPosition(Alignment.Center));
//        }

        @Override
        public boolean test(Item target) {
            perimeter.setPosition(locus.getPosition(Alignment.Center));
            return ShapeUtilities.contains(perimeter, target.getBounds());
        }
    }
}
