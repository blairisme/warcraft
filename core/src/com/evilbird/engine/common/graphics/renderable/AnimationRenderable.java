/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.graphics.renderable;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.graphics.animation.Animation;
import com.evilbird.engine.common.graphics.animation.AnimationFrame;

/**
 * Instances of this class render {@link Animation Animations}.
 *
 * @author Blair Butterworth
 */
public class AnimationRenderable extends BaseRenderable
{
    private Animation animation;
    private float animationTime;
    private Vector2 positionOffset;
    private Vector2 cacheSize;
    private GridPoint2 animationSize;

    public AnimationRenderable(Animation animation) {
        this(animation, 0);
    }

    public AnimationRenderable(Animation animation, float animationTime) {
        this.animation = animation;
        this.animationSize = animation.getSize();
        this.animationTime = animationTime;
        this.cacheSize = new Vector2(0, 0);
        this.positionOffset = new Vector2(0, 0);
    }

    public Animation getAnimation() {
        return animation;
    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        if (animation != null) {
            AnimationFrame frame = animation.getFrame(animationTime);
            Vector2 frameOffset = getPositionOffset(width, height);
            frame.draw(batch,
                x - frameOffset.x,
                y - frameOffset.y,
                animationSize.x,
                animationSize.y);
        }
    }

    @Override
    public void update(float delta) {
        animationTime += delta;
    }

    private Vector2 getPositionOffset(float width, float height) {
        if (cacheSize.x != width || cacheSize.y != height) {
            cacheSize.x = width;
            cacheSize.y = height;
            positionOffset.x = (animationSize.x - width) * 0.5f;
            positionOffset.y = (animationSize.y - height) * 0.5f;
        }
        return positionOffset;
    }
}
