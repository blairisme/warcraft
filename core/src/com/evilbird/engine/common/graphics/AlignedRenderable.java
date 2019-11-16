/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.lang.Alignment;

/**
 * @author Blair Butterworth
 */
public class AlignedRenderable implements Renderable
{
    private Vector2 position;
    private Vector2 size;
    private Vector2 delegateSize;
    private Renderable delegate;
    private Alignment alignment;

    public AlignedRenderable(Alignment alignment, Renderable renderable, Vector2 size) {
        this.alignment = alignment;
        this.delegate = renderable;
        this.delegateSize = size;
        this.position = new Vector2(0, 0);
        this.size = new Vector2(0, 0);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        draw(batch, position, size);
    }

    public void draw(Batch batch, Vector2 position, Vector2 size) {
        Vector2 aligned = getPosition(position, size, delegateSize);
        delegate.setPosition(aligned.x, aligned.y);
        delegate.draw(batch, 1f);
    }

    private Vector2 getPosition(Vector2 position, Vector2 size, Vector2 delegateSize) {
        Vector2 result = new Vector2();
        result.x = position.x + 20; //(position.x + (size.x / 2)) - (delegateSize.x / 2);
        result.y = position.y;
        return result;
    }

    @Override
    public void setPosition(float x, float y) {
        position.set(x, y);
    }

    @Override
    public void setSize(float x, float y) {
        size.set(x, y);
    }

    @Override
    public void update(float time) {
        delegate.update(time);
    }
}
