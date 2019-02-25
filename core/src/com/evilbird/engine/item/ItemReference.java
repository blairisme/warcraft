/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

import com.evilbird.engine.common.function.Supplier;
import com.evilbird.engine.common.lang.Identifier;

import static com.evilbird.engine.item.ItemPredicates.itemWithId;

/**
 * Instances of this class lookup an {@link Item} when requested using the
 * given identifier.
 *
 * @author Blair Butterworth
 */
public class ItemReference implements Supplier<Item>
{
    private ItemComposite composite;
    private Identifier identifier;
    private transient Item cache;

    public ItemReference(Item item) {
        this(item.getRoot(), item.getIdentifier(), item);
    }

    public ItemReference(ItemComposite composite, Identifier identifier) {
        this(composite, identifier, null);
    }

    private ItemReference(ItemComposite composite, Identifier identifier, Item cache) {
        this.composite = composite;
        this.identifier = identifier;
        this.cache = cache;
    }

    @Override
    public Item get() {
        if (cache == null) {
            cache = composite.find(itemWithId(identifier));
        }
        return cache;
    }
}
