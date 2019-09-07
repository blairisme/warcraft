/*
 * Copyright (c) 2019, Blair Butterworth
 *  
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *  
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer;

import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.reflect.TypeRegistry;
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

/**
 * Instances of this class serialize and deserialize {@link LayerGroup
 * LayerGroups}.
 *
 * @author Blair Butterworth
 */
public abstract class LayerGroupAdapter <T extends LayerGroup> implements JsonSerializer<T>, JsonDeserializer<T>
{
    private static final String CLAZZ = "class";
    private static final String TYPE = "type";
    private static final String LOCATION = "location";

    private ItemFactory itemFactory;
    protected TypeRegistry typeRegistry;

    public LayerGroupAdapter() {
        GameService service = GameService.getInstance();
        this.itemFactory = service.getItemFactory();
        this.typeRegistry = service.getTypeRegistry();
    }

    public LayerGroupAdapter(ItemFactory itemFactory, TypeRegistry typeRegistry) {
        this.itemFactory = itemFactory;
        this.typeRegistry = typeRegistry;
    }

    @Override
    public JsonElement serialize(T source, Type type, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.addProperty(CLAZZ, typeRegistry.getName(source.getClass()));
        result.add(TYPE, context.serialize(source.getIdentifier(), Identifier.class));
        result.add(getCellArrayProperty(), serializeCells(source, context));
        return result;
    }

    protected JsonElement serializeCells(T source, JsonSerializationContext context) {
        JsonArray result = new JsonArray();
        for (Item item: source.getItems()) {
            LayerGroupCell tree = (LayerGroupCell)item;
            JsonElement element = serializeCell(tree, context);
            result.add(element);
        }
        return result;
    }

    protected JsonElement serializeCell(LayerGroupCell cell, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.addProperty(getValueProperty(), cell.getValue());
        result.add(LOCATION, context.serialize(cell.getLocation(), GridPoint2.class));
        return result;
    }

    @Override
    public T deserialize(JsonElement json, Type type, JsonDeserializationContext context) {
        JsonObject object = json.getAsJsonObject();
        T group = deserializeInstance(object, context);
        deserializeCells(object, context, group);
        return group;
    }

    @SuppressWarnings("unchecked")
    protected T deserializeInstance(JsonObject json, JsonDeserializationContext context) {
        JsonElement element = json.get(TYPE);
        ItemType itemType = context.deserialize(element, Identifier.class);
        return (T)itemFactory.get(itemType);
    }

    protected void deserializeCells(JsonObject json, JsonDeserializationContext context, T group) {
        for (JsonElement cell: json.getAsJsonArray(getCellArrayProperty())) {
            deserializeCell(cell.getAsJsonObject(), context, group);
        }
    }

    protected void deserializeCell(JsonObject json, JsonDeserializationContext context, T group) {
        GridPoint2 location = context.deserialize(json.get(LOCATION), GridPoint2.class);
        float value = json.get(getValueProperty()).getAsFloat();
        group.addItem(createCell(location, value));
    }

    protected abstract LayerGroupCell createCell(GridPoint2 location, float value);

    protected abstract String getCellArrayProperty();

    protected abstract String getValueProperty();
}
