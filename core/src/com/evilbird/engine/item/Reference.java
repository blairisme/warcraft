package com.evilbird.engine.item;

import com.evilbird.engine.common.lang.Identifier;

import static com.evilbird.engine.item.ItemPredicates.itemWithId;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class Reference<T>
{
    private ItemComposite composite;
    private Identifier identifier;

    public Reference(ItemComposite composite, Identifier identifier)
    {
        this.composite = composite;
        this.identifier = identifier;
    }

    public T get()
    {
        return (T)composite.find(itemWithId(identifier));
    }
}
