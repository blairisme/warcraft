/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.spatial.ItemGraph;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Instances of this class serialize and deserialize {@link ItemRoot} objects.
 * The ItemRootAdapter sets deserialized values via methods on ItemRoot
 * (instead of via its fields) aiding the correct setup of the object.
 *
 * @author Blair Butterworth
 */
public class ItemRootAdapter implements JsonSerializer<ItemRoot>, JsonDeserializer<ItemRoot>
{
    private static final String GRAPH = "graph";
    private static final String ITEMS = "items";
    private static final String ID = "id";

    @Override
    public JsonElement serialize(ItemRoot root, Type type, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        serializeId(result, root, context);
        serializeGraph(result, root, context);
        serializeItems(result, root, context);
        return result;
    }

    private void serializeId(JsonObject json, ItemRoot root, JsonSerializationContext context) {
        json.add(ID, context.serialize(root.getIdentifier(), Identifier.class));
    }

    private void serializeGraph(JsonObject json, ItemRoot root, JsonSerializationContext context) {
        com.evilbird.engine.item.spatial.ItemGraph graph = root.getSpatialGraph();
        if (graph != null) {
            json.add(GRAPH, context.serialize(graph, com.evilbird.engine.item.spatial.ItemGraph.class));
        }
    }

    private void serializeItems(JsonObject json, ItemRoot root, JsonSerializationContext context) {
        JsonArray array = new JsonArray();
        for (Item item: root.getItems()) {
            array.add(context.serialize(item, item.getClass()));
        }
        json.add(ITEMS, array);
    }

    @Override
    public ItemRoot deserialize(JsonElement json, Type type, JsonDeserializationContext context) {
        ItemRoot result = new ItemRoot();
        JsonObject object = json.getAsJsonObject();
        deserializeId(result, object, context);
        deserializeGraph(result, object, context);
        deserializeItems(result, object, context);
        return result;
    }

    private void deserializeId(ItemRoot root, JsonObject json, JsonDeserializationContext context) {
        if (json.has(ID)) {
            JsonElement element = json.get(ID);
            Identifier id = context.deserialize(element, Identifier.class);
            root.setIdentifier(id);
        }
    }

    private void deserializeGraph(ItemRoot root, JsonObject json, JsonDeserializationContext context) {
        if (json.has(GRAPH)) {
            JsonElement graphElement = json.get(GRAPH);
            com.evilbird.engine.item.spatial.ItemGraph graph = context.deserialize(graphElement, ItemGraph.class);
            root.setSpatialGraph(graph);
        }
    }

    private void deserializeItems(ItemRoot root, JsonObject json, JsonDeserializationContext context) {
        JsonArray items = json.getAsJsonArray(ITEMS);
        for (JsonElement item: items) {
            root.addItem(context.deserialize(item, Item.class));
        }
    }
}