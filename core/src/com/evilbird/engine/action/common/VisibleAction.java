package com.evilbird.engine.action.common;

import com.badlogic.gdx.scenes.scene2d.Action;

/**
 * Created by blair on 15/09/2017.
 */
public class VisibleAction extends Action
{
    private com.evilbird.engine.item.Visible visible;
    private boolean enabled;

    public VisibleAction(com.evilbird.engine.item.Visible visible, boolean enabled)
    {
        this.visible = visible;
        this.enabled = enabled;
    }

    @Override
    public boolean act(float time)
    {
        visible.setVisible(enabled);
        return true;
    }
}
