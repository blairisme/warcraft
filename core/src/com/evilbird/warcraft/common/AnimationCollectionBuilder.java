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
    private Map<AnimationIdentifier, AnimationIdentifier> merged;
    private Map<Texture, Map<AnimationIdentifier, AnimationSchema>> animations;

    public AnimationCollectionBuilder()
    {
        aliases = new HashMap<>(2);
        merged = new HashMap<>(2);
        animations = new HashMap<>(2);
    }

    public void associate(AnimationIdentifier sourceId, AnimationIdentifier targetId)
    {
        aliases.put(sourceId, targetId);
    }

    public void merge(AnimationIdentifier sourceId, AnimationIdentifier targetId)
    {
        merged.put(sourceId, targetId);
    }

    public void set(AnimationIdentifier id, AnimationSchema schema, Texture texture)
    {
        Map<AnimationIdentifier, AnimationSchema> schemas = animations.get(texture);
        if (schemas == null){
            schemas = new HashMap<>();
        }
        schemas.put(id, schema);
        animations.put(texture, schemas);
    }

    public Map<AnimationIdentifier, DirectionalAnimation> build()
    {
        AnimationBuilder builder = new AnimationBuilder();
        Map<AnimationIdentifier, DirectionalAnimation> result = new HashMap<>();

        for (Entry<Texture, Map<AnimationIdentifier, AnimationSchema>> animation: animations.entrySet()){
            builder.setTexture(animation.getKey());

            for (Entry<AnimationIdentifier, AnimationSchema> schema: animation.getValue().entrySet()){
                builder.setSchema(schema.getValue());
                result.put(schema.getKey(), builder.build());
            }
        }
        for  (Entry<AnimationIdentifier, AnimationIdentifier> alias: aliases.entrySet()){
            result.put(alias.getKey(), result.get(alias.getValue()));
        }
        for  (Entry<AnimationIdentifier, AnimationIdentifier> merge: merged.entrySet()){

            DirectionalAnimation source =  result.get(merge.getKey());
            DirectionalAnimation target =  result.get(merge.getValue());
            result.put(merge.getKey(), AnimationUtils.combine(source, target));
        }
        return result;
    }
}
