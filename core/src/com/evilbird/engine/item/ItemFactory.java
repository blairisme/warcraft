package com.evilbird.engine.item;

import com.evilbird.engine.common.lang.Identifier;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public interface ItemFactory
{
    public void load();

    public Item newItem(ItemIdentifier type);

    public ItemRoot newItemGroup(Identifier type);
}
