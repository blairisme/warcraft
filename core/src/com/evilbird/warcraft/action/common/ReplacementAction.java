package com.evilbird.warcraft.action.common;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.common.ClearAction;
import com.evilbird.engine.item.Item;

/**
 * Created by blair on 24/09/2017.
 */
public class ReplacementAction extends Action
{
    private ClearAction delegate;

    public ReplacementAction(Item item, Action replacement)
    {
        delegate = new ClearAction(item, replacement);
    }

    @Override
    public boolean act(float delta)
    {
        return delegate.act(delta);
    }


}
