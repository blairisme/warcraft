package com.evilbird.engine.item;

import com.evilbird.engine.utility.Identifier;

public interface ItemFactory
{
    public void load();

    public Item newItem(Identifier type);

    public ItemGroup newItemGroup(Identifier type);
}
