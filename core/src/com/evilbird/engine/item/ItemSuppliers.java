package com.evilbird.engine.item;

import com.evilbird.engine.common.function.Supplier;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.warcraft.item.common.capability.Destructible;
import com.evilbird.warcraft.item.common.capability.ResourceContainer;
import com.evilbird.warcraft.item.unit.resource.ResourceType;

import static com.evilbird.engine.item.ItemPredicates.itemWithId;

public class ItemSuppliers
{
    public static Supplier<Boolean> isDead(Destructible item) {
        return new Supplier<Boolean>() {
            public Boolean get() {
                return (item.getHealth() <= 0);
            }
        };
    }

    public static Supplier<Boolean> isAlive(Destructible item) {
        return new Supplier<Boolean>() {
            public Boolean get() {
                return (item.getHealth() > 0);
            }
        };
    }

    public static Supplier<Boolean> hasResources(ResourceContainer container, ResourceType type) {
        return new Supplier<Boolean>() {
            public Boolean get() {
                return (container.getResource(type) > 0);
            }
        };
    }

    public static Supplier<Item> findClosest(Item item) {
        return new Supplier<Item>() {
            public Item get() {
                return ItemOperations.findClosest(item);
            }
        };
    }

    public static Supplier<Item> findClosest(ItemComposite group, Identifier type, Item locus) {
        return new Supplier<Item>() {
            public Item get() {
                return ItemOperations.findClosest(group, type, locus);
            }
        };
    }

    public static Supplier<Item> findById(ItemComposite group, Identifier id) {
        return new Supplier<Item>() {
            public Item get() {
                return group.find(itemWithId(id));
            }
        };
    }
}
