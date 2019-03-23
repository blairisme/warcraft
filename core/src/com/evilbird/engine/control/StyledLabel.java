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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.graphics.Fonts;

public class StyledLabel extends Label
{
    public StyledLabel(String text) {
        super(text, defaultStyle());
        setWrap(true);
    }

    public StyledLabel(String text, Skin skin, int align) {
        super(text, skin);
        setWrap(true);
        setAlignment(align);
    }

    public void setFont(BitmapFont font) {
        LabelStyle style = getStyle();
        style.font = font;
        setStyle(style);
    }

    public void setFontColour(Color color) {
        LabelStyle style = getStyle();
        style.fontColor = color;
        setStyle(style);
    }

    public void setSkin(Skin skin) {
        setStyle(skin.get(LabelStyle.class));
    }

    private static LabelStyle defaultStyle() {
        return new LabelStyle(Fonts.ARIAL, Color.BLACK);
    }
}
