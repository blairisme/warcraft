/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.forest;

import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.game.GameService;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemType;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Instances of this class serialize and deserialize {@link Forest} objects.
 *
 * @author Blair Butterworth
 */
public class ForestAdapter implements JsonSerializer<Forest>, JsonDeserializer<Forest>
{
    private static final String TYPE = "type";
    private static final String TREES = "trees";
    private static final String WOOD = "wood";
    private static final String LOCATION = "location";

    private ItemFactory itemFactory;

    public ForestAdapter() {
        GameService service = GameService.getInstance();
        this.itemFactory = service.getItemFactory();
    }

    public ForestAdapter(ItemFactory itemFactory) {
        this.itemFactory = itemFactory;
    }

    @Override
    public JsonElement serialize(Forest source, Type type, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add(TYPE, context.serialize(source.getIdentifier(), Identifier.class));
        result.add(TREES, serializeCells(source, context));
        return result;
    }

    private JsonElement serializeCells(Forest forest, JsonSerializationContext context) {
        JsonArray result = new JsonArray();
        for (Item item: forest.getItems()) {
            ForestCell tree = (ForestCell)item;
            JsonElement element = serializeCell(tree, context);
            result.add(element);
        }
        return result;
    }

    private JsonElement serializeCell(ForestCell tree, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.addProperty(WOOD, tree.getWood());
        result.add(LOCATION, context.serialize(tree.getLocation(), GridPoint2.class));
        return result;
    }

    @Override
    public Forest deserialize(JsonElement json, Type type, JsonDeserializationContext context) {
        JsonObject object = json.getAsJsonObject();
        Forest forest = deserializeInstance(object, context);
        Collection<ForestCell> cells = deserializeCells(object, context);
        cells.forEach(forest::addItem);
        return forest;
    }

    private Forest deserializeInstance(JsonObject json, JsonDeserializationContext context) {
        JsonElement element = json.get(TYPE);
        ItemType itemType = context.deserialize(element, Identifier.class);
        return (Forest)itemFactory.newItem(itemType);
    }

    private Collection<ForestCell> deserializeCells(JsonObject json, JsonDeserializationContext context) {
        Collection<ForestCell> result = new ArrayList<>();
        for (JsonElement tree: json.getAsJsonArray(TREES)) {
            ForestCell cell = deserializeCell(tree.getAsJsonObject(), context);
            result.add(cell);
        }
        return result;
    }

    private ForestCell deserializeCell(JsonObject json, JsonDeserializationContext context) {
        ForestCell result = new ForestCell();
        result.setWood(json.get(WOOD).getAsFloat());
        result.setLocation(context.deserialize(json.get(LOCATION), GridPoint2.class));
        return result;
    }
}
