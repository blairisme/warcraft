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
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.graphics.animation.Animation;
import com.evilbird.engine.common.graphics.animation.AnimationFrame;

import static com.evilbird.engine.common.math.GridPoints.Zero;

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

    public AnimationRenderable() {
        this.animation = null;
        this.animationSize = Zero;
        this.animationTime = 0;
        this.cacheSize = new Vector2(0, 0);
    }

    public AnimationRenderable(Animation animation) {
        this.animation = animation;
        this.animationSize = animation.getSize();
        this.animationTime = 0;
        this.cacheSize = new Vector2(0, 0);
    }

    public Animation getAnimation() {
        return animation;
    }

    public float getOffset() {
        return 0;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
        this.animationSize = animation.getSize();
    }

    public void setAnimationTime(float time) {
        this.animationTime = time;
    }

    public void setOffset(float offset) {

    }

    public void draw (Batch batch, float x, float y, float width, float height) {
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

    public void update(float delta) {
        animationTime += delta;
    }

    private Vector2 getPositionOffset(float width, float height) {
        if (cacheSize.x != width || cacheSize.y != height || positionOffset == null) {
            cacheSize.set(width, height);
            positionOffset = new Vector2();
            positionOffset.x = (animationSize.x - width) * 0.5f;
            positionOffset.y = (animationSize.y - height) * 0.5f;
        }
        return positionOffset;
    }
}
