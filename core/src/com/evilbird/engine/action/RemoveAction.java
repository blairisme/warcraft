package com.evilbird.engine.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;

public class RemoveAction extends Action
{
    private ItemRoot root;
    private Item item;

    public RemoveAction(ItemRoot root, Item item)
    {
        this.root = root;
        this.item = item;
    }
/*
    public RemoveAction(Stage stage, Identifier unitIdentifier)
    {
        this.stage = stage;
        this.actor = ItemUtils.findById(stage, unitIdentifier);
    }
*/
    @Override
    public boolean act(float delta)
    {
        item.clearActions();
        actor.remove();
        root.removeItem(item);
        return true;
    }
}
