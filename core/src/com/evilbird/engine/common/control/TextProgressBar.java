/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.control;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
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

    public TextProgressBar(float min, float max, float stepSize, String text, Skin skin, String style) {
        progressBar = new ProgressBar(min, max, stepSize, false, skin, style);
        outline = new Image(skin.get(style, ProgressBarStyle.class).background);

        label = new Label(text, skin, style);
        label.setAlignment(Align.center);

        add(progressBar);
        add(outline);
        add(label);
    }

    public void setValue(float value) {
        progressBar.setValue(value);
    }

    public void setText(String text) {
        label.setText(text);
    }
}