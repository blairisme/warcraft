package com.evilbird.engine.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;

public class RemoveAction extends Action
{
    private ItemRoot root;
    private Item item;

    public RemoveAction(Item item)
    {
        this(item.getRoot(), item);
    }

    public RemoveAction(ItemRoot root, Item item)
    {
        this.root = root;
        this.item = item;
    }

    @Override
    public boolean act(float delta)
    {
        item.clearActions();
        root.removeItem(item);
        return true;
    }
}
