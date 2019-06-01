/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

import com.evilbird.engine.common.reflect.TypeRegistry;
import com.evilbird.engine.common.serialization.AbstractAdapter;
import com.evilbird.engine.common.serialization.SerializedConstructor;
import com.evilbird.engine.game.GameService;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import javax.inject.Inject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Objects;

/**
 * Instances of this class serialize and deserialize {@link Item Items}. New
 * new object instances are created using the {@link ItemFactory} and the type
 * field in the given Java or Json data.
 *
 * @author Blair Butterworth
 */
public class ItemBasicAdapter extends AbstractAdapter<Item>
{
    protected static final String TYPE = "type";
    protected static final String CLASS = "class";

    protected ItemFactory itemFactory;
    protected TypeRegistry typeRegistry;

    @Inject
    public ItemBasicAdapter(ItemFactory itemFactory, TypeRegistry typeRegistry) {
        this.itemFactory = itemFactory;
        this.typeRegistry = typeRegistry;
    }

    @SerializedConstructor
    protected ItemBasicAdapter() {
        GameService service = GameService.getInstance();
        this.itemFactory = service.getItemFactory();
        this.typeRegistry = service.getTypeRegistry();
    }

    @Override
    protected JsonObject getSerializedType(Item target, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.addProperty(CLASS, typeRegistry.getName(target.getClass()));
        result.add(TYPE, context.serialize(target.getType(), ItemType.class));
        return result;
    }

    @Override
    protected boolean isSerializedField(Item target, Field field) {
        return !Modifier.isTransient(field.getModifiers()) && !Objects.equals(field.getName(), TYPE);
    }

    @Override
    protected Class<?> getDeserializedType(JsonObject json, JsonDeserializationContext context) {
        if (json.has(CLASS)) {
            String name = json.get(CLASS).getAsString();
            return typeRegistry.getType(name);
        }
        return Item.class;
    }

    @Override
    protected Item getDeserializedInstance(JsonObject json, JsonDeserializationContext context) {
        ItemType identifier = context.deserialize(json.get(TYPE), ItemType.class);
        return itemFactory.newItem(identifier);
    }

    @Override
    protected boolean isDeserializedField(String name, JsonElement value) {
        return true;
    }
}
