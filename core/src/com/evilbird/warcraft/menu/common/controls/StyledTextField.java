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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.graphics.Fonts;

public class StyledTextField extends TextField
{
    public StyledTextField() {
        super("", defaultStyle());
    }

    public void setBackground(Drawable drawable) {
        TextFieldStyle style = getStyle();
        style.background = drawable;
        setStyle(style);
    }

    public void setFontColour(Color color) {
        TextFieldStyle style = getStyle();
        style.fontColor = color;
        setStyle(style);
    }

    private static TextFieldStyle defaultStyle() {
        TextFieldStyle result = new TextFieldStyle();
        result.font = Fonts.ARIAL;
        result.fontColor = Color.BLACK;
        //result.cursor = Gdx.graphics.setSystemCursor();
        result.selection = new BaseDrawable();
        result.background = new BaseDrawable();
        return result;
    }
}
