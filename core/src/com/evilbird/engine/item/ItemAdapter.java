/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.common.serialization.AbstractAdapter;
import com.evilbird.engine.game.GameService;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Instances of this class serialize and deserialize {@link Item Items}.
 *
 * @author Blair Butterworth
 */
//TODO: Dont write out empty values
public class ItemAdapter extends AbstractAdapter<Item>
{
    private static final String TYPE = "type";
    private ItemFactory itemFactory;

    public ItemAdapter() {
        GameService service = GameService.getInstance();
        this.itemFactory = service.getItemFactory();
    }

    public ItemAdapter(ItemFactory itemFactory) {
        this.itemFactory = itemFactory;
    }

    @Override
    protected JsonObject getSerializedType(Item target, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add(TYPE, context.serialize(target.getType(), ItemType.class));
        return result;
    }

    @Override
    protected boolean isSerializedField(Item target, Field field) {
        return !Modifier.isTransient(field.getModifiers()) && !Objects.equals(field.getName(), TYPE);
    }

    @Override
    protected Class<?> getDeserializedType(JsonObject json, JsonDeserializationContext context) {
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
