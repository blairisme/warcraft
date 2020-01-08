/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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