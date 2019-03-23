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
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemAdapter;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemType;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import javax.inject.Inject;

/**
 * Instances of this class serialize and deserialize {@link Layer} objects.
 * The adapter uses the same approach as {@link ItemAdapter}, creating a new
 * object instance using the {@link ItemFactory}, however while ItemAdapter
 * does this using the Items type, LayerAdapter uses the Layers identifier.
 *
 * @author Blair Butterworth
 */
public class LayerAdapter extends ItemAdapter
{
    protected static final String ID = "id";

    @Inject
    public LayerAdapter(ItemFactory itemFactory, TypeRegistry typeRegistry) {
        super(itemFactory, typeRegistry);
    }

    @SerializedConstructor
    protected LayerAdapter() {
        super();
    }

    @Override
    protected Item getDeserializedInstance(JsonObject json, JsonDeserializationContext context) {
        ItemType identifier = context.deserialize(json.get(ID), ItemType.class);
        return itemFactory.newItem(identifier);
    }
}
