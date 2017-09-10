package com.evilbird.warcraft.common;

import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.collection.Collections;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.item.specialized.AnimationIdentifier;

import java.util.ArrayList;
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
    private Map<AnimationIdentifier, Map<AnimationSchema, Texture>> animations;

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
        Map<AnimationSchema, Texture> schemas = animations.get(id);
        if (schemas == null){
            schemas = new HashMap<>();
        }
        schemas.put(schema, texture);
        animations.put(id, schemas);
    }

    public Map<AnimationIdentifier, DirectionalAnimation> build()
    {
        AnimationBuilder builder = new AnimationBuilder();
        Map<AnimationIdentifier, DirectionalAnimation> result = new HashMap<>();

        for (Entry<AnimationIdentifier, Map<AnimationSchema, Texture>> animation: animations.entrySet()){
            DirectionalAnimation product = null;
            for (Entry<AnimationSchema, Texture> schema: animation.getValue().entrySet()) {
                builder.setSchema(schema.getKey());
                builder.setTexture(schema.getValue());
                product = product == null ? builder.build() : AnimationUtils.combine(product, builder.build());
            }
            result.put(animation.getKey(), product);
            /*
            builder.setTexture(animation.getKey());

            for (Entry<AnimationIdentifier, AnimationSchema> schema: animation.getValue().entrySet()){
                builder.setSchema(schema.getValue());
                result.put(schema.getKey(), builder.build());
            }
            */
        }
        for (Entry<AnimationIdentifier, AnimationIdentifier> alias: aliases.entrySet()){
            result.put(alias.getKey(), result.get(alias.getValue()));
        }
//        for  (Entry<AnimationIdentifier, AnimationIdentifier> merge: merged.entrySet()){
//
//            DirectionalAnimation source =  result.get(merge.getKey());
//            DirectionalAnimation target =  result.get(merge.getValue());
//            result.put(merge.getKey(), AnimationUtils.combine(source, target));
//        }
        return result;
    }
}
