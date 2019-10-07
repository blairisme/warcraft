/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.graphics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
            animation = animation == null ? current : combine((BasicAnimation)animation, (BasicAnimation)current);
        }
        return animation;
    }

    public AnimationCatalogSequenceElement element() {
        AnimationCatalogSequenceElement definition = new AnimationCatalogSequenceElement(this);
        sequence.add(definition);
        return definition;
    }

    private BasicAnimation combine(BasicAnimation source, BasicAnimation target) {
        Map<Range<Float>, Array<TextureRegion>> sourceFrameSet = source.getFrames();
        Map<Range<Float>, Array<TextureRegion>> targetFrameSet = target.getFrames();
        Map<Range<Float>, Array<TextureRegion>> combinedFrames = new HashMap<>(sourceFrameSet.size());

        for (Map.Entry<Range<Float>, Array<TextureRegion>> sourceFrameEntry : sourceFrameSet.entrySet()) {
            Range<Float> range = sourceFrameEntry.getKey();
            Array<TextureRegion> sourceFrames = sourceFrameEntry.getValue();
            Array<TextureRegion> targetFrames = targetFrameSet.get(range);
            combinedFrames.put(range, union(sourceFrames, targetFrames));
        }
        return new BasicAnimation(source.getDirection(), source.getDuration(), combinedFrames, source.getMode());
    }
}
