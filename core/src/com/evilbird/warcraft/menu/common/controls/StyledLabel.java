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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.evilbird.engine.common.graphics.Fonts;

//TODO: Move into core / common
public class StyledLabel extends Label
{
    public StyledLabel(String text) {
        super(text, defaultStyle());
        setWrap(true);
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

    private static LabelStyle defaultStyle() {
        return new LabelStyle(Fonts.ARIAL, Color.BLACK);
    }
}
