package com.evilbird.warcraft.item.hud;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.hud.control.Image;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class MinimapPanel extends Item
{
    private Image image;

    public MinimapPanel()
    {
        this.image = new Image();
        this.image.setSize(176, 136);
    }

    public void setBackground(Drawable drawable)
    {
        this.image.setBackground(drawable);
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        image.draw(batch, parentAlpha);
    }

    @Override //TODO: Investigate better implementation
    public void positionChanged()
    {
        image.setPosition(getX(), getY());
    }

    @Override //TODO: Investigate better implementation
    public void sizeChanged()
    {
        image.setSize(getWidth(), getHeight());
    }
}
