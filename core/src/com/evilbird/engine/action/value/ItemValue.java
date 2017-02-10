package com.evilbird.engine.action.value;

import com.evilbird.engine.item.Item;
import com.evilbird.engine.utility.Identifier;

public class ItemValue implements ActionValue
{
    private Item item;
    private Identifier property;

    public ItemValue(Item item, Identifier property)
    {
        this.item = item;
        this.property = property;
    }

    @Override
    public Object get()
    {
        return item.getProperty(property);
    }

    @Override
    public void set(Object value)
    {
        item.setProperty(property, value);
    }
}
