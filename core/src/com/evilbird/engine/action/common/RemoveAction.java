package com.evilbird.engine.action.common;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.engine.item.ItemPredicates;

public class RemoveAction extends Action
{
    private ItemComposite parent;
    private Item item;
    private Identifier id;

    public RemoveAction(Item item)
    {
        this(item.getParent(), item);
    }

    public RemoveAction(ItemComposite parent, Item item)
    {
        this.parent = parent;
        this.item = item;
    }

    public RemoveAction(ItemComposite parent, Identifier id)
    {
        this.parent = parent;
        this.id = id;
    }

    @Override
    public boolean act(float delta)
    {
        if (item == null)
        {
            item = parent.find(ItemPredicates.itemWithId(id));
        }
        if (item != null)
        {
            item.clearActions();
            parent.removeItem(item);
        }
        return true;
    }

    @Override
    public void restart()
    {
    }
}
