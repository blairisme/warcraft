/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.display.control.common;

import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * Instances of this user interface control represents a health bar, a progress
 * bar display a units remaining health.
 *
 * @author Blair Butterworth
 */
public class HealthBar extends ProgressBar
{
    private enum HealthStyle {
        High,
        Medium,
        Low
    }

    private Skin skin;
    private HealthStyle style;

    public HealthBar(float min, float max, Skin skin) {
        super(min, max, 1, false, getStyle(skin, HealthStyle.High));
        this.skin = skin;
        this.style = HealthStyle.High;
    }

    @Override
    public boolean setValue(float value) {
        boolean result = super.setValue(value);
        setStyle(getPercent());
        return result;
    }

    private void setStyle(float progress) {
        if (progress >= 0f && progress < 0.33f) {
            setStyle(skin, HealthStyle.Low);
        } else if (progress >= 0.33f && progress < 0.66f) {
            setStyle(skin, HealthStyle.Medium);
        } else {
            setStyle(skin, HealthStyle.High);
        }
    }

    private void setStyle(Skin skin, HealthStyle newStyle) {
        if (style != newStyle) {
            style = newStyle;
            setStyle(getStyle(skin, newStyle));
        }
    }

    private static ProgressBarStyle getStyle(Skin skin, HealthStyle styleName) {
        HealthBarStyle healthStyle = skin.get("default", HealthBarStyle.class);
        switch (styleName) {
            case High: return getStyle(healthStyle.highBar);
            case Medium: return getStyle(healthStyle.mediumBar);
            case Low: return getStyle(healthStyle.lowBar);
            default: throw new UnsupportedOperationException();
        }
    }

    private static ProgressBarStyle getStyle(Drawable drawable) {
        ProgressBarStyle style = new ProgressBarStyle();
        style.knob = drawable;
        style.knobBefore = drawable;
        return style;
    }
}
