/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.menu;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.control.Image;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.warcraft.item.hud.HudControl;

public class MenuPane extends ItemGroup
{
    private Image image;

    public MenuPane() {
        this.image = new Image();
        this.image.setSize(176, 24);
        image.setType(HudControl.MenuPane);

       // button = new ImageButton();

        setSize(176, 24);
        addItem(image);
        setIdentifier(HudControl.MenuPane);
        setType(HudControl.MenuPane);
        setTouchable(Touchable.enabled);
    }

    public void setBackground(Drawable drawable) {
        this.image.setBackground(drawable);
    }
}
