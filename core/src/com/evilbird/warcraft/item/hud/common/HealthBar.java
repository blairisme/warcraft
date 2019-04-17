/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.common;

import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Instances of this user interface control represents a health bar, a progress
 * bar display a units remaining health.
 *
 * @author Blair Butterworth
 */
//TODO: Use constants for style
public class HealthBar extends ProgressBar
{
    private Skin skin;
    private String style;

    public HealthBar(float min, float max, Skin skin) {
        super(min, max, 1, false, skin, "health-progress-high");
        this.skin = skin;
        this.style = "health-progress-high";
    }

    @Override
    public boolean setValue(float progress) {
//        if (progress >= 0f && progress < 0.33f) {
//            setStyle("health-progress-low");
//        } else if (progress >= 0.33f && progress < 0.66f) {
//            setStyle("health-progress-medium");
//        } else {
//            setStyle("health-progress-high");
//        }

        return super.setValue(progress);
    }

    private void setStyle(String name) {
        if (!name.equals(style)) {
            style = name;
            setStyle(skin.get(name, ProgressBarStyle.class));
        }
    }
}
