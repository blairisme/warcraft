package com.evilbird.engine.action.replacement;

import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 * Created by blair on 15/09/2017.
 */

public interface Disablable
{
    public boolean getTouchable();

    public void setTouchable(Touchable touchable);
}
