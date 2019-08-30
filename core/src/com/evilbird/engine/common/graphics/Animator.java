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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Instances of this class render {@link Animation Animations}.
 *
 * @author Blair Butterworth
 */
public class Animator
{
    private Animation animation;
    private float animationTime;
    private Vector2 positionOffset;
    private Vector2 cacheSize;
    private Vector2 animationSize;

    public Animation getAnimation() {
        return animation;
    }

    public float getAnimationTime() {
        return animationTime;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
        this.animationSize = animation.getSize();
    }

    public void setAnimationTime(float time) {
        this.animationTime = time;
    }

    public void draw(Batch batch, Vector2 position, Vector2 size) {
        if (animation != null) {
            TextureRegion frame = animation.getFrame(animationTime);
            Vector2 frameOffset = getPositionOffset(size);
            batch.draw(frame,
                position.x - frameOffset.x,
                position.y - frameOffset.y,
                animationSize.x,
                animationSize.y);
        }
    }

    public void update(float delta) {
        animationTime += delta;
    }

    private Vector2 getPositionOffset(Vector2 clientSize) {
        if (! clientSize.equals(cacheSize) || positionOffset == null) {
            cacheSize = clientSize;
            positionOffset = new Vector2();
            positionOffset.x = (animationSize.x - clientSize.x) * 0.5f;
            positionOffset.y = (animationSize.y - clientSize.y) * 0.5f;
        }
        return positionOffset;
    }
}
