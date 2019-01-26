/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Objects;

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

    public static Predicate<Item> isNear(Vector2 position, float radius) {
        return new Predicate<Item>() {
            @Override
            public boolean test(Item target) {
                Vector2 targetPosition = target.getPosition();
                float distance = position.dst(targetPosition);
                return distance <= radius;
            }
        };
    }
}
