/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

import com.google.gson.*;

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

    @Override
    public JsonElement serialize(ItemRoot root, Type type, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        serializeGraph(result, root, context);
        serializeItems(result, root, context);
        return result;
    }

    private void serializeGraph(JsonObject json, ItemRoot root, JsonSerializationContext context) {
        ItemGraph graph = root.getSpatialGraph();
        if (graph != null) {
            json.add(GRAPH, context.serialize(graph, ItemGraph.class));
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
    public ItemRoot deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        ItemRoot result = new ItemRoot();
        JsonObject object = json.getAsJsonObject();
        deserializeGraph(result, object, context);
        deserializeItems(result, object, context);
        return result;
    }

    private void deserializeGraph(ItemRoot root, JsonObject json, JsonDeserializationContext context) {
        if (json.has(GRAPH)) {
            JsonElement graphElement = json.get(GRAPH);
            ItemGraph graph = context.deserialize(graphElement, ItemGraph.class);
            root.setSpatialGraph(graph);
        }
    }

    private void deserializeItems(ItemRoot root, JsonObject json, JsonDeserializationContext context) {
        JsonArray items = json.getAsJsonArray(ITEMS);
        for (JsonElement item: items) {
            JsonObject itemElement = item.getAsJsonObject();
            Class<?> itemType = itemElement.has(ITEMS) ? ItemGroup.class : Item.class;
            root.addItem(context.deserialize(item, itemType));
        }
    }
}