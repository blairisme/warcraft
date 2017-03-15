package com.evilbird.engine.action.value;

import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemProperty;
import com.evilbird.engine.item.ItemRoot;

import static com.evilbird.engine.item.ItemPredicates.itemWithId;

public class ItemReferenceValue implements ActionValue
{
    private ItemRoot root;
    private NamedIdentifier identifier;
    private ItemProperty property;

    public ItemReferenceValue(ItemRoot root, NamedIdentifier identifier, ItemProperty property)
    {
        this.root = root;
        this.identifier = identifier;
        this.property = property;
    }

    @Override
    public Object get()
    {
        Item item = root.find(itemWithId(identifier));
        return item.getProperty(property);
    }

    @Override
    public void set(Object value)
    {
        Item item = root.find(itemWithId(identifier));
        item.setProperty(property, value);
    }
}