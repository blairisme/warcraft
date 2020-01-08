/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.control;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.utils.Align;

/**
 * A graphical control that renders a label above a progress bar.
 *
 * @author Blair Butterworth
 */
public class TextProgressBar extends Stack
{
    private Label label;
    private Image outline;
    private ProgressBar progressBar;

    public TextProgressBar(float min, float max, float stepSize, String text, Skin skin) {
        this(min, max, stepSize, text, skin, "default");
    }

    public TextProgressBar(float min, float max, float increment, String text, Skin skin, String style) {
        progressBar = new ProgressBar(min, max, increment, false, skin, style);
        outline = new Image(skin.get(style, ProgressBarStyle.class).background);

        label = new Label(text, skin, style);
        label.setAlignment(Align.center);

        add(progressBar);
        add(outline);
        add(label);
    }

    public void setMaxValue(float maxValue) {
        progressBar.setRange(progressBar.getMinValue(), maxValue);
    }

    public void setMinValue(float minValue) {
        progressBar.setRange(minValue, progressBar.getMaxValue());
    }

    public void setValue(float value) {
        progressBar.setValue(value);
    }

    public void setText(String text) {
        label.setText(text);
    }
}