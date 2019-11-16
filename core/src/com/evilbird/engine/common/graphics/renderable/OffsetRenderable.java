/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.graphics.renderable;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * A {@link Renderable} implementation that renders a delegate aligned to a
 * given axis.
 *
 * @author Blair Butterworth
 */
public class OffsetRenderable extends BaseRenderable
{
    private Renderable delegate;
    private float delegateWidth;
    private float delegateHeight;
    private float offsetX;
    private float offsetY;

    /**
     * Creates a new instance of this class given a {@link Renderable} that
     * will be drawn offset by a given offset.
     */
    public OffsetRenderable(Renderable renderable) {
        this.delegate = renderable;
        this.delegateWidth = renderable.getMinWidth();
        this.delegateHeight = renderable.getMinHeight();
    }

    public void setOffset(float x, float y) {
        offsetX = x;
        offsetY = y;
    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        delegate.draw(batch, x + offsetX, y + offsetY, delegateWidth, delegateHeight);
    }

    @Override
    public void update(float time) {
        delegate.update(time);
    }
}