package com.evilbird.engine.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;

public class RemoveAction extends Action
{
    private ItemGroup parent;
    private Item item;

    public RemoveAction(Item item)
    {
        this(item.getParent(), item);
    }

    public RemoveAction(ItemGroup parent, Item item)
    {
        this.parent = parent;
        this.item = item;
    }

    @Override
    public boolean act(float delta)
    {
        item.clearActions();
        parent.removeItem(item);
        return true;
    }

    @Override
    public void restart()
    {
    }
}
