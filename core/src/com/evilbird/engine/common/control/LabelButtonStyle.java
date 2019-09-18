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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.evilbird.engine.common.audio.sound.Sound;

/**
 * Defines the visual presentation of a {@link LabelButton} control.
 *
 * @author Blair Butterworth
 */
public class LabelButtonStyle extends TextButtonStyle
{
    public Color borderColour;
    public Color borderColourFocused;
    public Sound clickSound;
    
    public LabelButtonStyle() {
        super();
    }
    
    public LabelButtonStyle(LabelButtonStyle style) {
        super(style);
        this.borderColour = style.borderColour;
        this.borderColourFocused = style.borderColourFocused;
        this.clickSound = style.clickSound;
    }
}
