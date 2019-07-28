/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.warcraft.common.WarcraftFaction;
import com.evilbird.warcraft.common.WarcraftSeason;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import javax.inject.Inject;
import java.lang.reflect.Type;

public class WarcraftContextAdapter implements JsonSerializer<WarcraftContextNew>, JsonDeserializer<WarcraftContextNew>
{
    private static final String FACTION = "faction";
    private static final String ASSETS = "assets";

    @Inject
    public WarcraftContextAdapter() {
    }

    @Override
    public JsonElement serialize(WarcraftContextNew source, Type type, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        json.add(FACTION, context.serialize(source.getFaction(), Identifier.class));
        json.add(ASSETS, context.serialize(source.getAssetSet(), Identifier.class));
        return json;
    }

    @Override
    public WarcraftContextNew deserialize(JsonElement element, Type type, JsonDeserializationContext context) {
        JsonObject json = element.getAsJsonObject();
        WarcraftFaction faction = context.deserialize(json.get(FACTION), Identifier.class);
        WarcraftSeason season = context.deserialize(json.get(ASSETS), Identifier.class);
        return new WarcraftContextNew(faction, season);
    }
}
