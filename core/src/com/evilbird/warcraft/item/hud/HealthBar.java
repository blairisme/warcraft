package com.evilbird.warcraft.item.hud;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.warcraft.item.hud.control.ProgressBar;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class HealthBar extends ProgressBar
{
    private Drawable highHealthTexture;
    private Drawable mediumHealthTexture;
    private Drawable lowHealthTexture;

    public void setHighHealthTexture(Drawable texture)
    {
        this.highHealthTexture = texture;
    }

    public void setMediumHealthTexture(Drawable texture)
    {
        this.mediumHealthTexture = texture;
    }

    public void setLowHealthTexture(Drawable texture)
    {
        this.lowHealthTexture = texture;
    }

    @Override
    public void setProgress(float progress)
    {
        super.setProgress(progress);

        if (progress >= 0f && progress < 0.33f) {
            setTexture(lowHealthTexture);
        }
        else if (progress >= 0.33f && progress < 0.66f) {
            setTexture(mediumHealthTexture);
        }
        else {
            setTexture(highHealthTexture);
        }
    }
}
