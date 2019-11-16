/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.graphics.animation;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Represents a single image element in an {@link Animation}.
 *
 * @author Blair Butterworth
 */
public class AnimationFrame implements Drawable 
{
    private Drawable delegate;

    public AnimationFrame(TextureRegion region) {
        this.delegate = new TextureRegionDrawable(region);
    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        delegate.draw(batch, x, y, width, height);
    }

    public Drawable getDrawable() {
        return delegate;
    }

    @Override
    public float getLeftWidth() {
        return delegate.getLeftWidth();
    }

    @Override
    public float getRightWidth() {
        return delegate.getRightWidth();
    }

    @Override
    public float getTopHeight() {
        return delegate.getTopHeight();
    }

    @Override
    public float getBottomHeight() {
        return delegate.getBottomHeight();
    }

    @Override
    public float getMinWidth() {
        return delegate.getMinWidth();
    }

    @Override
    public float getMinHeight() {
        return delegate.getMinHeight();
    }

    public void setDrawable(Drawable drawable) {
        this.delegate = drawable;
    }

    @Override
    public void setLeftWidth(float leftWidth) {
        delegate.setLeftWidth(leftWidth);
    }

    @Override
    public void setRightWidth(float rightWidth) {
        delegate.setRightWidth(rightWidth);
    }

    @Override
    public void setTopHeight(float topHeight) {
        delegate.setTopHeight(topHeight);
    }

    @Override
    public void setBottomHeight(float bottomHeight) {
        delegate.setBottomHeight(bottomHeight);
    }

    @Override
    public void setMinWidth(float minWidth) {
        delegate.setMinWidth(minWidth);
    }

    @Override
    public void setMinHeight(float minHeight) {
        delegate.setMinHeight(minHeight);
    }
}
