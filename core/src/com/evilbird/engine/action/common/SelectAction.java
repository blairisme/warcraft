package com.evilbird.engine.action.common;

import com.badlogic.gdx.scenes.scene2d.Action;

/**
 * Created by blair on 15/09/2017.
 */

public class SelectAction extends Action
{
    private com.evilbird.engine.item.Selectable selectable;
    private boolean selected;

    public SelectAction(com.evilbird.engine.item.Selectable selectable, boolean selected)
    {
        this.selectable = selectable;
        this.selected = selected;
    }

    @Override
    public boolean act(float delta)
    {
        selectable.setSelected(selected);
        return true;
    }
}
