package com.evilbird.warcraft.item.common.animation;

import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.item.specialized.animated.AnimationIdentifier;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class AnimationCollectionBuilder
{
    private Map<AnimationIdentifier, AnimationIdentifier> aliases;
    private Map<AnimationIdentifier, List<Pair<AnimationSchema, Texture>>> animations;

    public AnimationCollectionBuilder()
    {
        aliases = new HashMap<>(2);
        animations = new HashMap<>(2);
    }

    public void associate(AnimationIdentifier sourceId, AnimationIdentifier targetId)
    {
        aliases.put(sourceId, targetId);
    }

    public void set(AnimationIdentifier id, AnimationSchema schema, Texture texture)
    {
        animations.put(id, Arrays.asList(Pair.of(schema, texture)));
    }

    public void set(AnimationIdentifier id, List<Pair<AnimationSchema, Texture>> data)
    {
        animations.put(id, data);
    }

    //TODO: Move multiple schema/texture logic into AnimationBuilder. Stop animation merging.
    public Map<AnimationIdentifier, DirectionalAnimation> build()
    {
        AnimationBuilder builder = new AnimationBuilder();
        Map<AnimationIdentifier, DirectionalAnimation> result = new HashMap<>();

        for (Entry<AnimationIdentifier, List<Pair<AnimationSchema, Texture>>> animation: animations.entrySet()){
            DirectionalAnimation product = null;
            for (Pair<AnimationSchema, Texture> schema: animation.getValue()) {
                builder.setSchema(schema.getKey());
                builder.setTexture(schema.getValue());
                product = product == null ? builder.build() : AnimationUtils.combine(product, builder.build());
            }
            result.put(animation.getKey(), product);
        }
        for (Entry<AnimationIdentifier, AnimationIdentifier> alias: aliases.entrySet()){
            result.put(alias.getKey(), result.get(alias.getValue()));
        }
        return result;
    }
}
