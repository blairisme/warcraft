/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.control;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.graphics.Fonts;

import java.util.Collection;

public class StyledList<T> extends List<Object>
{
    public StyledList(Collection<T> items) {
        super(getDefaultStyle());
        setItems(items.toArray());
    }

    public StyledList(Skin skin) {
        super(skin);
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

    public void setSkin(Skin skin) {
        setStyle(skin.get(ListStyle.class));
    }

    private static ListStyle getDefaultStyle() {
        ListStyle style = new ListStyle();
        style.font = Fonts.ARIAL;
        style.selection = new BaseDrawable();
        return style;
    }
}
