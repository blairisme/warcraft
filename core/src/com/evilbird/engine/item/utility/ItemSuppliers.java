/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.utility;

import com.evilbird.engine.item.Item;

import java.util.function.Supplier;

/**
 * Instances of this class define commonly used {@link Supplier Suppliers}
 * that operate on {@link Item Items}.
 *
 * @author Blair Butterworth
 */
public class ItemSuppliers
{
    private ItemSuppliers() {
    }

    public static Supplier<Item> ifExists(Item item) {
        return () -> item.getParent().containsItem(item) ? item : null;
        /*
                return () -> {
            if (item != null) {
                ItemGroup parent = item.getParent();
                if (parent != null && parent.containsItem(item)) {
                    return item;
                }
            }
            return null;
        };
         */
    }
}
