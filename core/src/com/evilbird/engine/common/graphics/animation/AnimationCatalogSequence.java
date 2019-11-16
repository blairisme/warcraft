/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.graphics.animation;

import com.badlogic.gdx.utils.Array;
import org.apache.commons.lang3.Range;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.evilbird.engine.common.collection.Arrays.union;

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
        Animation animation = null;
        for (AnimationCatalogProvider entry: sequence) {
            Animation current = entry.getAnimation();
            animation = animation == null ? current : combine(
                (DirectionalAnimation)animation, (DirectionalAnimation)current);
        }
        return animation;
    }

    public AnimationCatalogSequenceElement element() {
        AnimationCatalogSequenceElement definition = new AnimationCatalogSequenceElement(this);
        sequence.add(definition);
        return definition;
    }

    private BasicAnimation combine(DirectionalAnimation source, DirectionalAnimation target) {
        Map<Range<Float>, Array<AnimationFrame>> sourceFrameSet = source.getFrameRanges();
        Map<Range<Float>, Array<AnimationFrame>> targetFrameSet = target.getFrameRanges();
        Map<Range<Float>, Array<AnimationFrame>> combinedFrames = new HashMap<>(sourceFrameSet.size());

        for (Map.Entry<Range<Float>, Array<AnimationFrame>> sourceFrameEntry : sourceFrameSet.entrySet()) {
            Range<Float> range = sourceFrameEntry.getKey();
            Array<AnimationFrame> sourceFrames = sourceFrameEntry.getValue();
            Array<AnimationFrame> targetFrames = targetFrameSet.get(range);
            combinedFrames.put(range, union(sourceFrames, targetFrames));
        }
        return new BasicAnimation(combinedFrames, source.getInterval(), source.getLooping());
    }
}
