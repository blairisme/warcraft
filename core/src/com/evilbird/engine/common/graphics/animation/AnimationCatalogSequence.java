/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.graphics.animation;

import java.util.ArrayList;
import java.util.List;

/**
 * An entry in an {@link AnimationCatalog} representing a sequence of
 * {@link Animation Animations} whose visual data is provided by a multiple
 * {@link AnimationCatalogSequenceElement AnimationCatalogSequenceEntries}.
 *
 * @author Blair Butterworth
 */
public class AnimationCatalogSequence implements AnimationCatalogProvider
{
    private List<AnimationCatalogProvider> sequence;

    public AnimationCatalogSequence() {
        sequence = new ArrayList<>();
    }

    public Animation getAnimation() {
        List<Animation> animations = new ArrayList<>(sequence.size());
        for (AnimationCatalogProvider entry: sequence) {
            animations.add(entry.getAnimation());
        }
        return new AnimationSequence(animations);
    }

    public AnimationCatalogSequenceElement element() {
        AnimationCatalogSequenceElement definition = new AnimationCatalogSequenceElement(this);
        sequence.add(definition);
        return definition;
    }
}
