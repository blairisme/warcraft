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
public class Reference<T> implements Supplier<T>
{
    private ItemComposite composite;
    private Identifier identifier;
    private T cache;

    public Reference(ItemComposite composite, Identifier identifier) {
        this.composite = composite;
        this.identifier = identifier;
        this.cache = null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get() {
        if (cache == null) {
            cache = (T)composite.find(itemWithId(identifier));
        }
        return cache;
    }
}
