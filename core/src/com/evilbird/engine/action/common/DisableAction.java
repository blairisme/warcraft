package com.evilbird.engine.action.common;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.item.Disablable;
import com.evilbird.engine.item.Selectable;

/**
 * Created by blair on 15/09/2017.
 */

public class DisableAction extends Action
{
    private Selectable selectable;
    private boolean disabled;

    public DisableAction(Selectable selectable, boolean disabled)
    {
        this.selectable = selectable;
        this.disabled = disabled;
    }

    @Override
    public boolean act(float delta)
    {
        selectable.setSelectable(disabled);
        return true;
    }
}
