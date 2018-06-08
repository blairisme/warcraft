package com.evilbird.engine.item;

import com.evilbird.engine.common.function.Supplier;
import com.evilbird.warcraft.item.common.capability.Destructible;

public class ItemSuppliers
{
    public static Supplier<Boolean> isDead(final Destructible item)
    {
        return new Supplier<Boolean>() {
            public Boolean get() {
                return (item.getHealth() <= 0);
            }
        };
    }

    public static Supplier<Boolean> isAlive(final Destructible item)
    {
        return new Supplier<Boolean>() {
            public Boolean get() {
                return (item.getHealth() > 0);
            }
        };
    }
}
