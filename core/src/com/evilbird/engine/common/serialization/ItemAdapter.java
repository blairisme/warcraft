/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.serialization;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemType;
import com.google.gson.JsonSerializer;
import com.google.gson.*;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Map.Entry;

import static java.lang.reflect.Modifier.isTransient;

public class ItemAdapter implements JsonSerializer<Item>, JsonDeserializer<Item>
{
    private ItemFactory itemFactory;

    public ItemAdapter(ItemFactory itemFactory) {
        this.itemFactory = itemFactory;
    }

    @Override
    public JsonElement serialize(Item item, Type type, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        for (Field field: FieldUtils.getAllFields(item.getClass())) {
            if (! isTransient(field.getModifiers())) {
                serialize(result, context, field, item);
            }
        }
        return result;
    }

    private void serialize(JsonObject json, JsonSerializationContext context, Field field, Item item) {
        String name = field.getName();
        Class<?> type = field.getType();
        Object value = readField(field, item);
        json.add(name, context.serialize(value, type));
    }

    private Object readField(Field field, Object target) {
        try {
            return FieldUtils.readField(field, target, true);
        }
        catch (IllegalAccessException error) {
            throw new JsonParseException(error);
        }
    }

    @Override
    public Item deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        Item item = createItem(jsonObject, context);

        for (Entry<String, JsonElement> entry: jsonObject.entrySet()) {
            deserialize(entry.getKey(), entry.getValue(), context, item, item.getClass());
        }
        return item;
    }

    private Item createItem(JsonObject json, JsonDeserializationContext context) {
        JsonElement element = json.get("type");
        ItemType type = context.deserialize(element, Identifier.class);
        return itemFactory.newItem(type);
    }

    private void deserialize(String name, JsonElement json, JsonDeserializationContext context, Item item, Class<?> type) {
        Field field = FieldUtils.getField(type, name, true);
        Object value = context.deserialize(json, field.getType());
        writeField(field, item, value);
    }

    private void writeField(Field field, Object target, Object value) {
        try {
            FieldUtils.writeField(field, target, value, true);
        }
        catch (IllegalAccessException error) {
            throw new JsonParseException(error);
        }
    }
}
