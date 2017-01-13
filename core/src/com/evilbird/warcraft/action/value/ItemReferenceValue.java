package com.evilbird.warcraft.action.value;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.evilbird.warcraft.item.Item;
import com.evilbird.warcraft.item.ItemUtils;
import com.evilbird.warcraft.utility.Identifier;

public class ItemReferenceValue implements ActionValue
{
    private Stage stage;
    private Identifier identifier;
    private Identifier property;

    public ItemReferenceValue(Stage stage, Identifier identifier, Identifier property)
    {
        this.stage = stage;
        this.identifier = identifier;
        this.property = property;
    }

    @Override
    public Object get()
    {
        Item item = ItemUtils.findById(stage, identifier);
        return item.getProperty(property);
    }

    @Override
    public void set(Object value)
    {
        Item item = ItemUtils.findById(stage, identifier);
        item.setProperty(property, value);
    }
}