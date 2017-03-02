package com.evilbird.warcraft.item.hud;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.item.control.Image;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class MinimapPane extends ItemGroup
{
    private Image image;

    public MinimapPane()
    {
        this.image = new Image();
        this.image.setSize(176, 136);
        setSize(176, 136);
        addItem(image);
    }

    public void setBackground(Drawable drawable)
    {
        this.image.setBackground(drawable);
    }

}
