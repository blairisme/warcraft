/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer.fog;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.warcraft.object.layer.LayerGroupCell;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A {@link FogAdapter} specialization that serializes and deserialize
 * {@link ConcealingFog} objects.
 *
 * @author Blair Butterworth
 */
public class ConcealingFogAdapter extends FogAdapter
{
    protected static final String OCCUPANTS = "occupants";

    @Override
    protected JsonElement serializeCell(LayerGroupCell cell, JsonSerializationContext context) {
        JsonObject result = (JsonObject)super.serializeCell(cell, context);
        result.add(OCCUPANTS, serializeOccupants((ConcealingFogCell)cell, context));
        return result;
    }

    protected JsonElement serializeOccupants(ConcealingFogCell cell, JsonSerializationContext context) {
        JsonArray occupants = new JsonArray();
        for (Identifier occupant : cell.getOccupants()) {
            occupants.add(context.serialize(occupant, Identifier.class));
        }
        return occupants;
    }

    @Override
    protected LayerGroupCell deserializeCell(Fog group, JsonObject json, JsonDeserializationContext context) {
        ConcealingFogCell result = (ConcealingFogCell)super.deserializeCell(group, json, context);
        result.addOccupants(deserializeOccupants(json, context));
        return result;
    }

    protected Collection<Identifier> deserializeOccupants(JsonObject json, JsonDeserializationContext context) {
        Collection<Identifier> occupants = new ArrayList<>(2);
        for (JsonElement occupant: json.getAsJsonArray(OCCUPANTS)) {
            occupants.add(context.deserialize(occupant, Identifier.class));
        }
        return occupants;
    }
}
