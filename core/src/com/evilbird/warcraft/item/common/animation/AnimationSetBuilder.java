/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.evilbird.engine.common.graphics.AnimationBuilder;
import com.evilbird.engine.common.graphics.AnimationSchema;
import com.evilbird.engine.common.graphics.BasicAnimation;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.lang.Identifier;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static com.evilbird.engine.common.collection.Arrays.union;

public class AnimationSetBuilder
{
    private Map<Identifier, Identifier> aliases;
    private Map<Identifier, List<Pair<AnimationSchema, Texture>>> animations;

    public AnimationSetBuilder() {
        aliases = new HashMap<>(2);
        animations = new HashMap<>(2);
    }

    public void associate(Identifier sourceId, Identifier targetId) {
        aliases.put(sourceId, targetId);
    }

    public void set(Identifier id, AnimationSchema schema, Texture texture) {
        animations.put(id, Arrays.asList(Pair.of(schema, texture)));
    }

    public void set(Identifier id, List<Pair<AnimationSchema, Texture>> data) {
        animations.put(id, data);
    }

    public Map<Identifier, DirectionalAnimation> build() {
        AnimationBuilder builder = new AnimationBuilder();
        Map<Identifier, DirectionalAnimation> result = new HashMap<>();

        for (Entry<Identifier, List<Pair<AnimationSchema, Texture>>> animation : animations.entrySet()) {
            BasicAnimation product = null;
            for (Pair<AnimationSchema, Texture> schema : animation.getValue()) {
                builder.setSchema(schema.getKey());
                builder.setTexture(schema.getValue());
                product = product == null ? builder.build() : combine(product, builder.build());
            }
            result.put(animation.getKey(), product);
        }
        for (Entry<Identifier, Identifier> alias : aliases.entrySet()) {
            result.put(alias.getKey(), result.get(alias.getValue()));
        }
        return result;
    }

    private BasicAnimation combine(BasicAnimation source, BasicAnimation target) {
        Map<Range<Float>, Array<TextureRegion>> sourceFrameSet = source.getFrames();
        Map<Range<Float>, Array<TextureRegion>> targetFrameSet = target.getFrames();
        Map<Range<Float>, Array<TextureRegion>> combinedFrames = new HashMap<>(sourceFrameSet.size());

        for (Entry<Range<Float>, Array<TextureRegion>> sourceFrameEntry : sourceFrameSet.entrySet()) {
            Range<Float> range = sourceFrameEntry.getKey();
            Array<TextureRegion> sourceFrames = sourceFrameEntry.getValue();
            Array<TextureRegion> targetFrames = targetFrameSet.get(range);
            combinedFrames.put(range, union(sourceFrames, targetFrames));
        }
        return new BasicAnimation(source.getDirection(), source.getDuration(), combinedFrames, source.getMode());
    }
}
