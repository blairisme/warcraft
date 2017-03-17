package com.evilbird.engine.action.value;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.engine.item.ItemProperty;

import static com.evilbird.engine.item.ItemPredicates.itemWithId;

public class ItemReferenceValue implements ActionValue
{
    private ItemComposite composite;
    private Identifier identifier;
    private ItemProperty property;

    public ItemReferenceValue(ItemComposite composite, Identifier identifier, ItemProperty property)
    {
        this.composite = composite;
        this.identifier = identifier;
        this.property = property;
    }

    @Override
    public Object get()
    {
        Item item = composite.find(itemWithId(identifier));
        return item.getProperty(property);
    }

    @Override
    public void set(Object value)
    {
        Item item = composite.find(itemWithId(identifier));
        item.setProperty(property, value);
    }
}