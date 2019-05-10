/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.spatial;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Instances of this class serialize and deserialize {@link ItemGraph ItemGraphs}.
 * ItemGraphs are instantiated with deserialized data, facilitating its setup.
 *
 * @author Blair Butterworth
 */
public class ItemGraphAdapter implements JsonSerializer<ItemGraph>, JsonDeserializer<ItemGraph>
{
    private static final String NODE_COUNT_X = "nodeCountX";
    private static final String NODE_COUNT_Y = "nodeCountY";
    private static final String NODE_HEIGHT = "nodeHeight";
    private static final String NODE_WIDTH = "nodeWidth";

    @Override
    public JsonElement serialize(ItemGraph source, Type type, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.addProperty(NODE_WIDTH, source.getNodeWidth());
        result.addProperty(NODE_HEIGHT, source.getNodeHeight());
        result.addProperty(NODE_COUNT_X, source.getNodeCountX());
        result.addProperty(NODE_COUNT_Y, source.getNodeCountY());
        return result;
    }

    @Override
    public ItemGraph deserialize(JsonElement source, Type type, JsonDeserializationContext context) {
        JsonObject json = source.getAsJsonObject();
        int nodeCountX = json.get(NODE_COUNT_X).getAsInt();
        int nodeCountY = json.get(NODE_COUNT_Y).getAsInt();
        int nodeHeight = json.get(NODE_HEIGHT).getAsInt();
        int nodeWidth = json.get(NODE_WIDTH).getAsInt();
        return new ItemGraph(nodeWidth, nodeHeight, nodeCountX, nodeCountY);
    }
}
