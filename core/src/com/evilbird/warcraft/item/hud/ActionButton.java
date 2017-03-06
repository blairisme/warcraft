package com.evilbird.warcraft.item.hud;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.control.ImageButton;
import com.evilbird.warcraft.action.ActionType;

import java.util.Collections;
import java.util.Map;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ActionButton extends ImageButton
{
    private ActionIdentifier actionId;
    private Map<ActionIdentifier, Drawable> icons;

    public ActionButton()
    {
        icons = Collections.emptyMap();
        actionId = ActionType.Unknown;
        setPadding(4);
        setSize(54, 46);
        setType(new Identifier("ActionButton"));
        setTouchable(Touchable.enabled);
    }

    public void setAction(ActionIdentifier action)
    {
        actionId = action;
        setImage(icons.get(actionId));

        setType(new Identifier(action.toString() + "Button"));
    }

    public void setActionIcons(Map<ActionIdentifier, Drawable> icons)
    {
        this.icons = icons;
    }
}

