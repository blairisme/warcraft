/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.control;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;

/**
 * Defines the visual presentation of a {@link TextInput} control.
 *
 * @author Blair Butterworth
 */
public class TextInputStyle extends TextFieldStyle
{
    public Color borderColour;
    public Color borderColourFocused;

    public TextInputStyle() {
        super();
    }

    public TextInputStyle(TextInputStyle style) {
        super(style);
        this.borderColour = style.borderColour;
        this.borderColourFocused = style.borderColourFocused;
    }
}