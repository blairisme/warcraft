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
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;

import static com.evilbird.engine.common.math.GridPoints.Zero;

/**
 * Instances of this class render {@link Animation Animations}.
 *
 * @author Blair Butterworth
 */
public class AnimationRenderable implements Renderable
{
    private Animation animation;
    private float animationTime;
    private Vector2 frameOffset;
    private Vector2 position;
    private Vector2 size;
    private GridPoint2 animationSize;

    public AnimationRenderable() {
        this(null);
    }

    public AnimationRenderable(Animation animation) {
        this.frameOffset = new Vector2();
        this.position = new Vector2();
        this.size = new Vector2();
        setAnimation(animation);
    }

    public Animation getAnimation() {
        return animation;
    }

    public float getOffset() {
        return 0;
    }

    @Override
    public void draw(Batch batch, float alpha) {
        if (animation != null) {
            AnimationFrame frame = animation.getFrame(animationTime);
            frame.draw(batch,
                position.x - frameOffset.x,
                position.y - frameOffset.y,
                animationSize.x,
                animationSize.y);
        }
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
        calculateLayout();
    }

    public void setAnimationTime(float time) {
        this.animationTime = time;
    }

    public void setOffset(float offset) {

    }

    @Override
    public void setPosition(float x, float y) {
        position.set(x, y);
    }

    @Override
    public void setSize(float x, float y) {
        size.set(x, y);
        calculateLayout();
    }

    @Override
    public void update(float delta) {
        animationTime += delta;
    }

    private void calculateLayout() {
        if (animation != null) {
            animationSize = animation.getSize();
            animationSize = animationSize != null ? animationSize : Zero;
            frameOffset.x = (animationSize.x - size.x) * 0.5f;
            frameOffset.y = (animationSize.y - size.y) * 0.5f;
        }
    }
}
