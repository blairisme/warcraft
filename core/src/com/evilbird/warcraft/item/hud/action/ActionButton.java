package com.evilbird.warcraft.item.hud.action;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.item.control.ImageButton;

import java.util.Collections;
import java.util.Map;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ActionButton extends ImageButton
{
    private Map<ActionIdentifier, Drawable> icons;

    public ActionButton()
    {
        icons = Collections.emptyMap();
        setPadding(4);
        setSize(54, 46);
        setType(new NamedIdentifier("ActionButton"));
        setTouchable(Touchable.enabled);
    }

    public void setAction(ActionIdentifier action)
    {
        setImage(icons.get(action));
        setType(new NamedIdentifier(action.toString()));
    }

    public void setActionIcons(Map<ActionIdentifier, Drawable> icons)
    {
        this.icons = icons;
    }
}

