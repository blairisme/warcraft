/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
