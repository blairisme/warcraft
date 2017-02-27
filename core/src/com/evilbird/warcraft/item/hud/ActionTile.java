package com.evilbird.warcraft.item.hud;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.hud.control.Image;

import java.util.Map;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ActionTile extends Item
{
    private Image tile;
    private Map<ActionIdentifier, Drawable> actionIcons;

    public ActionTile()
    {
        tile = new Image();
        tile.setPadding(4);
        actionIcons = null;
    }

    public void setAction(ActionIdentifier action)
    {
        tile.setImage(actionIcons.get(action));
    }

    public void setBackground(Drawable drawable)
    {
        tile.setBackground(drawable);
    }

    public void setActionIcons(Map<ActionIdentifier, Drawable> actionIcons)
    {
        this.actionIcons = actionIcons;
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        tile.draw(batch, parentAlpha);
    }

    @Override //TODO: Investigate better implementation
    protected void positionChanged()
    {
        tile.setPosition(getX(), getY());
    }

    @Override //TODO: Investigate better implementation
    protected void sizeChanged()
    {
        tile.setSize(getWidth(), getHeight());
    }
}
