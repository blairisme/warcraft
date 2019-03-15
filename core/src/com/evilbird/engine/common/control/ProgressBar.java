/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.control;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.item.ItemBasic;

import javax.inject.Inject;

public class ProgressBar extends ItemBasic
{
    private Drawable texture;
    private float textureWidth;
    private float progress;

    @Inject
    public ProgressBar()
    {
        texture = null;
        textureWidth = 0;
        progress = 0;
    }

    public void setTexture(Drawable texture)
    {
        this.texture = texture;
    }

    public void setProgress(float progress)
    {
        if (progress < 0 || progress > 1) {
            throw new IllegalArgumentException("Progress should be >0 && <1");
        }
        this.progress = progress;
        this.textureWidth = getWidth() * progress;
    }

    @Override
    public void sizeChanged()
    {
        this.textureWidth = getWidth() * progress;
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        if (texture != null){
            texture.draw(batch, getX(), getY(), textureWidth, getHeight());
        }
    }
}
