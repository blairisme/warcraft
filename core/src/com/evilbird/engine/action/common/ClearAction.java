package com.evilbird.engine.action.common;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.item.Item;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
//TODO: Separate cancellation and replacement action
public class ClearAction extends Action
{
    private Item item;
    private Action next;

    public ClearAction(Item item)
    {
        this.item = item;
    }

    public ClearAction(Item item, Action next)
    {
        this.item = item;
        this.next = next;
    }

    @Override
    public boolean act(float delta)
    {
        item.clearActions();
        if (next != null) {
            item.addAction(next);
        }
        return true;
    }

    @Override
    public void restart()
    {
    }
}