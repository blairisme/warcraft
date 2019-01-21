/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Objects;

public class ItemPredicates
{
    public static Predicate<Item> itemWithId(Identifier id)
    {
        return new ItemWithId(id); //TODO: Pool?
    }

    public static class ItemWithId implements Predicate<Item>
    {
        private Identifier id;

        public ItemWithId(Identifier id)
        {
            this.id = id;
        }

        @Override
        public boolean test(Item item)
        {
            return id.equals(item.getIdentifier());
        }
    }

    public static Predicate<Item> itemWithType(Identifier type)
    {
        return new ItemWithType(type); //TODO: Pool?
    }

    public static class ItemWithType implements Predicate<Item>
    {
        private Identifier type;

        public ItemWithType(Identifier type)
        {
            this.type = type;
        }

        @Override
        public boolean test(Item item)
        {
            return type.equals(item.getType());
        }
    }

    public static Predicate<Item> itemWithAction()
    {
        return new ItemWithAction(); //TODO singleton?
    }

    public static class ItemWithAction implements Predicate<Item>
    {
        @Override
        public boolean test(Item item)
        {
            return item.hasActions();
        }
    }

    public static Predicate<Item> itemWithOwner(Identifier id)
    {
        return new ItemWithOwner(id);
    }

    public static class ItemWithOwner implements Predicate<Item>
    {
        private Identifier id;

        public ItemWithOwner(Identifier id)
        {
            this.id = id;
        }

        @Override
        public boolean test(Item item)
        {
            Item parent = item.getParent();
            return Objects.equals(parent.getIdentifier(), id);
        }
    }

    public static Predicate<Item> selectedItem()
    {
        return new SelectedItem(); //TODO singleton?
    }

    public static class SelectedItem implements Predicate<Item>
    {
        @Override
        public boolean test(Item item)
        {
            return item.getSelected();
        }
    }

    public static Predicate<Item> selectableItem()
    {
        return new SelectableItem(); //TODO singleton?
    }

    public static class SelectableItem implements Predicate<Item>
    {
        @Override
        public boolean test(Item item)
        {
            return item.getSelectable();
        }
    }
}
