package com.evilbird.engine.item;

import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.common.lang.Objects;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ItemPredicates
{
    public static Predicate<Item> itemWithId(NamedIdentifier id)
    {
        return new ItemWithId(id); //TODO: Pool?
    }

    public static class ItemWithId implements Predicate<Item>
    {
        private NamedIdentifier id;

        public ItemWithId(NamedIdentifier id)
        {
            this.id = id;
        }

        @Override
        public boolean test(Item item)
        {
            return id.equals(item.getId());
        }
    }

    public static Predicate<Item> itemWithType(NamedIdentifier type)
    {
        return new ItemWithType(type); //TODO: Pool?
    }

    public static class ItemWithType implements Predicate<Item>
    {
        private NamedIdentifier type;

        public ItemWithType(NamedIdentifier type)
        {
            this.type = type;
        }

        @Override
        public boolean test(Item item)
        {
            return type.equals(item.getType());
        }
    }

    public static Predicate<Item> itemWithProperty(ItemProperty key, Object value)
    {
        return new ItemWithProperty(key, value); //TODO: Pool?
    }

    public static class ItemWithProperty implements Predicate<Item>
    {
        private ItemProperty key;
        private Object value;

        public ItemWithProperty(ItemProperty key, Object value)
        {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean test(Item item)
        {
            Object otherValue = item.getProperty(key);
            return Objects.equals(value, otherValue);
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

    public static Predicate<Item> itemWithOwner(NamedIdentifier id)
    {
        return new ItemWithOwner(id);
    }

    public static class ItemWithOwner implements Predicate<Item>
    {
        private NamedIdentifier id;

        public ItemWithOwner(NamedIdentifier id)
        {
            this.id = id;
        }

        @Override
        public boolean test(Item item)
        {
            Item parent = item.getParent();
            return Objects.equals(parent.getId(), id);
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


}
