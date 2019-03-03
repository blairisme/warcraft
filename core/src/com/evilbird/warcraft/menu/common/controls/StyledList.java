/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.common.controls;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.graphics.Fonts;

public class StyledList extends List
{
    public StyledList() {
        super(getDefaultStyle());
    }

    public void setBackground(Drawable drawable) {
        ListStyle style = getStyle();
        style.background = drawable;
        setStyle(style);
    }

    public void setFont(BitmapFont font) {
        ListStyle style = getStyle();
        style.font = font;
        setStyle(style);
    }

    public void setSelectedColour(Color color) {
        ListStyle style = getStyle();
        style.fontColorSelected = color;
        setStyle(style);
    }

    public void setUnselectedColour(Color color) {
        ListStyle style = getStyle();
        style.fontColorUnselected = color;
        setStyle(style);
    }

    public void setSelection(Drawable drawable) {
        ListStyle style = getStyle();
        style.selection = drawable;
        setStyle(style);
    }

    private static ListStyle getDefaultStyle() {
        ListStyle style = new ListStyle();
        style.font = Fonts.ARIAL;
        style.selection = new BaseDrawable();
        return style;
    }
}
