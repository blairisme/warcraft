/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.minimap;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.item.control.Image;
import com.evilbird.warcraft.item.hud.HudControl;

public class MinimapPane extends ItemGroup
{
    private Image image;

    public MinimapPane() {
        this.image = new Image();
        this.image.setSize(176, 136);
        setSize(176, 136);
        addItem(image);
        setId(HudControl.MinimapPane);
        setType(HudControl.MinimapPane);
        setTouchable(Touchable.disabled);
    }

    public void setBackground(Drawable drawable) {
        this.image.setBackground(drawable);
    }
}
