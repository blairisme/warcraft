package com.evilbird.engine.action.common;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.item.Selectable;

/**
 * Created by blair on 15/09/2017.
 */

public class SelectAction extends Action
{
    private Selectable selectable;
    private boolean selected;

    public SelectAction(Selectable selectable, boolean selected)
    {
        this.selectable = selectable;
        this.selected = selected;
    }

    @Override
    public boolean act(float time)
    {
        if (selectable.getSelectable()) {
            selectable.setSelected(selected);
        }
        return true;
    }
}
