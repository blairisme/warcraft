package com.evilbird.engine.action.common;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.item.Disablable;

/**
 * Created by blair on 15/09/2017.
 */

public class DisableAction extends Action
{
    private Disablable disablable;
    private boolean disable;

    public DisableAction(Disablable disablable, boolean disable)
    {
        this.disablable = disablable;
        this.disable = disable;
    }

    @Override
    public boolean act(float delta)
    {
        disablable.setTouchable(disable ? Touchable.disabled : Touchable.enabled);
        return true;
    }
}
