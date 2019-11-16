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
import org.apache.commons.lang3.Validate;

/**
 * A {@link Renderable} implementation that renders a delegate aligned to a
 * given axis.
 *
 * @author Blair Butterworth
 */
public class AlignedRenderable extends BaseRenderable
{
    private Renderable delegate;
    private float delegateWidth;
    private float delegateHeight;
    private float cacheWidth;
    private float offsetX;

    /**
     * Creates a new instance of this class given a {@link Renderable} that
     * will be drawn aligned to the given {@link Alignment}.
     */
    public AlignedRenderable(Renderable renderable, Alignment alignment) {
        Validate.isTrue(alignment == Alignment.BottomCenter);
        this.delegate = renderable;
        this.delegateWidth = renderable.getMinWidth();
        this.delegateHeight = renderable.getMinHeight();
    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        updateAlignment(width, height);
        delegate.draw(batch, x + offsetX, y, delegateWidth, delegateHeight);
    }

    @Override
    public void update(float time) {
        delegate.update(time);
    }

    private void updateAlignment(float width, float height) {
        if (cacheWidth != width) {
            cacheWidth = width;
            offsetX = (width * 0.5f) - (delegateWidth * 0.5f);
        }
    }
}
