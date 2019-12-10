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
 * A proxy for an {@link AnimationCatalogEntry} representing a single element
 * in an {@link AnimationCatalogSequence}. An addition to AnimationCatalogEntry
 * this class also provides the ability to append a new element to the
 * sequence.
 * 
 * @author Blair Butterworth
 */
public class AnimationCatalogSequenceElement implements AnimationCatalogProvider
{
    private AnimationCatalogEntry entry;
    private AnimationCatalogSequence sequence;

    public AnimationCatalogSequenceElement(AnimationCatalogSequence sequence) {
        this.sequence = sequence;
        this.entry = new AnimationCatalogEntry();
        this.entry.notLooping();
    }

    public Animation getAnimation() {
        return entry.getAnimation();
    }

    public AnimationCatalogSequenceElement element() {
        return sequence.element();
    }

//    public AnimationCatalogSequenceElement looping() {
//        entry.looping();
//        return this;
//    }
//
//    public AnimationCatalogSequenceElement notLooping() {
//        entry.notLooping();
//        return this;
//    }

    public AnimationCatalogSequenceElement reversed() {
        entry.reversed();
        return this;
    }

    public AnimationCatalogSequenceElement singleDirection() {
        entry.singleDirection();
        return this;
    }

    public AnimationCatalogSequenceElement withInterval(float interval) {
        entry.withInterval(interval);
        return this;
    }

    public AnimationCatalogSequenceElement withTexture(Texture texture) {
        this.entry.withTexture(texture);
        return this;
    }

    public AnimationCatalogSequenceElement withSequence(int start, int count) {
        entry.withSequence(start, count);
        return this;
    }

    public AnimationCatalogSequenceElement withSize(GridPoint2 size) {
        entry.withSize(size);
        return this;
    }

    public AnimationCatalogSequenceElement withSize(int width, int height) {
        entry.withSize(width, height);
        return this;
    }
}
