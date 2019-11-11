/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer;

import com.evilbird.engine.common.reflect.TypeRegistry;
import com.evilbird.engine.common.serialization.SerializedConstructor;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.engine.object.GameObjectSerializer;
import com.evilbird.engine.object.GameObjectType;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import javax.inject.Inject;

/**
 * Instances of this class serialize and deserialize {@link Layer} objects.
 * The adapter uses the same approach as {@link GameObjectSerializer}, creating a new
 * object instance using the {@link GameObjectFactory}, however while ItemBasicAdapter
 * does this using the Items type, LayerAdapter uses the Layers identifier.
 *
 * @author Blair Butterworth
 */
public class LayerAdapter extends GameObjectSerializer
{
    protected static final String ID = "id";

    @Inject
    public LayerAdapter(GameObjectFactory objectFactory, TypeRegistry typeRegistry) {
        super(objectFactory, typeRegistry);
    }

    @SerializedConstructor
    protected LayerAdapter() {
        super();
    }

    @Override
    protected GameObject getDeserializedInstance(JsonObject json, JsonDeserializationContext context) {
        GameObjectType identifier = context.deserialize(json.get(ID), GameObjectType.class);
        return objectFactory.get(identifier);
    }
}
