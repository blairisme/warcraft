/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.graphics.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;

/**
 * An entry in an {@link AnimationCatalog} representing a single
 * {@link Animation} whose visual data is provided by a single
 * {@link Texture}.
 *
 * @author Blair Butterworth
 */
public class AnimationCatalogEntry implements AnimationCatalogProvider
{
    private Texture texture;
    private AnimationBuilder animationBuilder;
    private AnimationLayoutBuilder layoutBuilder;

    /**
     * Constructs a new instance of this class.
     */
    public AnimationCatalogEntry() {
        animationBuilder = new AnimationBuilder();
        layoutBuilder = new AnimationLayoutBuilder();
    }

    /**
     * Constructs an {@link Animation} using the attributes of the entry.
     *
     * @return a new {@code Animation}.
     */
    public Animation getAnimation() {
        animationBuilder.setLayout(layoutBuilder.build());
        animationBuilder.setTexture(texture);
        return animationBuilder.build();
    }

    /**
     * Instructs {@link Animation Animations} produced by the entry to loop:
     * once complete the animation will start playing from the beginning
     * again.
     *
     * @return the AnimationCatalogEntry.
     */
    public AnimationCatalogEntry looping() {
        layoutBuilder.setLooping(true);
        return this;
    }

    /**
     * Instructs {@link Animation Animations} produced by the entry NOT to
     * loop: once complete the animation will not continue from the beginning
     * of the animation, rather the last frame of the animation will be
     * continually shown.
     *
     * @return the AnimationCatalogEntry.
     */
    public AnimationCatalogEntry notLooping() {
        layoutBuilder.setLooping(false);
        return this;
    }

    public AnimationCatalogEntry reversed() {
        layoutBuilder.setReversed();
        return this;
    }

    public AnimationCatalogEntry singleDirection() {
        layoutBuilder.setDirections(1);
        return this;
    }

    public AnimationCatalogEntry withInterval(float interval) {
        layoutBuilder.setInterval(interval);
        return this;
    }

    public AnimationCatalogEntry withTexture(Texture texture) {
        this.texture = texture;
        return this;
    }

    public AnimationCatalogEntry withSequence(int start, int count) {
        layoutBuilder.addSequence(start, count);
        return this;
    }

    public AnimationCatalogEntry withSize(GridPoint2 size) {
        layoutBuilder.setSize(size.x, size.y);
        return this;
    }

    public AnimationCatalogEntry withSize(int width, int height) {
        layoutBuilder.setSize(width, height);
        return this;
    }
}
