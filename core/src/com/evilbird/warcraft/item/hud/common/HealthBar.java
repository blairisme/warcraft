/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.common;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.item.control.ProgressBar;
import com.evilbird.warcraft.item.hud.HudControls;

/**
 * Instances of this user interface control represents a health bar, a progress
 * bar display a units remaining health.
 *
 * @author Blair Butterworth
 */
public class HealthBar extends ProgressBar
{
    private Drawable highHealthTexture;
    private Drawable mediumHealthTexture;
    private Drawable lowHealthTexture;

    public HealthBar() {
        setType(HudControls.HealthBar);
        setTouchable(Touchable.disabled);
    }

    public void setHighHealthTexture(Drawable texture) {
        this.highHealthTexture = texture;
    }

    public void setMediumHealthTexture(Drawable texture) {
        this.mediumHealthTexture = texture;
    }

    public void setLowHealthTexture(Drawable texture) {
        this.lowHealthTexture = texture;
    }

    @Override
    public void setProgress(float progress) {
        super.setProgress(progress);

        if (progress >= 0f && progress < 0.33f) {
            setTexture(lowHealthTexture);
        } else if (progress >= 0.33f && progress < 0.66f) {
            setTexture(mediumHealthTexture);
        } else {
            setTexture(highHealthTexture);
        }
    }
}
