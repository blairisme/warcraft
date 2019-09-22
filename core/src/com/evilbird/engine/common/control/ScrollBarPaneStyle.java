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
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;

/**
 * Defines the visual presentation of a {@link ScrollBarPane} control.
 *
 * @author Blair Butterworth
 */
public class ScrollBarPaneStyle extends ScrollPaneStyle
{
    public Color borderColour;
    public Color borderColourFocused;

    public ScrollBarPaneStyle() {
        super();
    }

    public ScrollBarPaneStyle(ScrollBarPaneStyle style) {
        super(style);
        this.borderColour = style.borderColour;
        this.borderColourFocused = style.borderColourFocused;
    }
}
