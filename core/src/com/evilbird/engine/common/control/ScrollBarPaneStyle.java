/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
