package com.evilbird.warcraft.item.hud;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.control.ImageButton;
import com.evilbird.engine.item.control.Invokable;
import com.evilbird.warcraft.action.ActionType;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ActionButton extends ImageButton implements Invokable
{
    private ActionIdentifier actionId;
    private Collection<Item> items;
    private Map<ActionIdentifier, Drawable> icons;

    public ActionButton()
    {
        setPadding(4);
        setSize(54, 46);
        icons = Collections.emptyMap();
        items = Collections.emptyList();
        actionId = ActionType.Unknown;
    }

    public void setAction(ActionIdentifier action)
    {
        actionId = action;
        setImage(icons.get(actionId));
    }

    public void setActionIcons(Map<ActionIdentifier, Drawable> icons)
    {
        this.icons = icons;
    }

    public void setItems(Collection<Item> items)
    {
        this.items = items;
    }

    public void invoke(ActionFactory actionFactory)
    {
        /*
        for (Item item: items){
            Action action = actionFactory.newAction(actionId, item, null);
            item.addAction(action);
        }
        */
    }
}

