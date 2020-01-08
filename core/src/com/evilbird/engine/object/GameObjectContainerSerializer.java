/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.object;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Instances of this class serialize and deserialize {@link GameObjectContainer
 * GameObjectContainers}. The serializer sets deserialized values via methods
 * on the {@code GameObjectContainer} (instead of via its fields) aiding the
 * correct setup of the object.
 *
 * @author Blair Butterworth
 */
public class GameObjectContainerSerializer
    implements JsonSerializer<GameObjectContainer>, JsonDeserializer<GameObjectContainer>
{
    private static final String GRAPH = "graph";
    private static final String OBJECTS = "objects";
    private static final String ID = "id";

    @Override
    public JsonElement serialize(GameObjectContainer container, Type type, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        serializeId(result, container, context);
        serializeGraph(result, container, context);
        serializeItems(result, container, context);
        return result;
    }

    private void serializeId(JsonObject json, GameObjectContainer root, JsonSerializationContext context) {
        json.add(ID, context.serialize(root.getIdentifier(), Identifier.class));
    }

    private void serializeGraph(JsonObject json, GameObjectContainer root, JsonSerializationContext context) {
        GameObjectGraph graph = root.getSpatialGraph();
        if (graph != null) {
            json.add(GRAPH, context.serialize(graph, GameObjectGraph.class));
        }
    }

    private void serializeItems(JsonObject json, GameObjectContainer root, JsonSerializationContext context) {
        JsonArray array = new JsonArray();
        for (GameObject gameObject : root.getObjects()) {
            array.add(context.serialize(gameObject, gameObject.getClass()));
        }
        json.add(OBJECTS, array);
    }

    @Override
    public GameObjectContainer deserialize(JsonElement json, Type type, JsonDeserializationContext context) {
        GameObjectContainer result = new GameObjectContainer();
        JsonObject object = json.getAsJsonObject();
        deserializeId(result, object, context);
        deserializeGraph(result, object, context);
        deserializeItems(result, object, context);
        return result;
    }

    private void deserializeId(GameObjectContainer container, JsonObject json, JsonDeserializationContext context) {
        if (json.has(ID)) {
            JsonElement element = json.get(ID);
            Identifier id = context.deserialize(element, Identifier.class);
            container.setIdentifier(id);
        }
    }

    private void deserializeGraph(GameObjectContainer container, JsonObject json, JsonDeserializationContext context) {
        if (json.has(GRAPH)) {
            JsonElement graphElement = json.get(GRAPH);
            GameObjectGraph graph = context.deserialize(graphElement, GameObjectGraph.class);
            container.setSpatialGraph(graph);
        }
    }

    private void deserializeItems(GameObjectContainer container, JsonObject json, JsonDeserializationContext context) {
        JsonArray items = json.getAsJsonArray(OBJECTS);
        for (JsonElement item: items) {
            container.addObject(context.deserialize(item, GameObject.class));
        }
    }
}