package com.evilbird.engine.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemProperties;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.ItemType;

public class CreateAction extends Action
{
    private ItemRoot root;
    private Identifier id;
    private ItemType type;
    private ItemFactory factory;
    private Vector2 position;
    private boolean selected;

    public CreateAction(
        ItemRoot root,
        ItemType type,
        ItemFactory factory,
        Identifier id,
        Vector2 position,
        boolean selected)
    {
        this.root = root;
        this.id = id;
        this.type = type;
        this.factory = factory;
        this.position = position;
        this.selected = selected;
    }

    @Override
    public boolean act(float delta)
    {
        Item item = factory.newItem(type);
        item.setPosition(position);
        item.setProperty(ItemProperties.Id, id);
        item.setSelected(selected);
        root.addItem(item);
        return true;
    }

    @Override
    public void restart()
    {
    }
}
