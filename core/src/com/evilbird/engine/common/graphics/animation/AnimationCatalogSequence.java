/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
