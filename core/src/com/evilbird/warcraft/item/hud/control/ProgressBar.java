package com.evilbird.warcraft.item.hud.control;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.item.Item;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ProgressBar extends Item
{
    private Drawable texture;
    private float textureWidth;
    private float progress;

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
    public void setWidth(float width)
    {
        super.setWidth(width);
        this.textureWidth = getWidth() * progress;
    }

    @Override
    public void setSize(float width, float height)
    {
        super.setSize(width, height);
        this.textureWidth = width * progress;
    }

    @Override
    public void setBounds(float x, float y, float width, float height)
    {
        super.setBounds(x, y, width, height);
        this.textureWidth = width * progress;
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        if (texture != null){
            texture.draw(batch, getX(), getY(), textureWidth, getHeight());
        }
    }
}
