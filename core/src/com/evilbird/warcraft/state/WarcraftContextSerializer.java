/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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

/**
 * Serializes and deserializes {@link WarcraftContext WarcraftContexts}.
 *
 * @author Blair Butterworth
 */
public class WarcraftContextSerializer implements JsonSerializer<WarcraftContext>, JsonDeserializer<WarcraftContext>
{
    private static final String FACTION = "faction";
    private static final String ASSETS = "assets";

    @Inject
    public WarcraftContextSerializer() {
    }

    @Override
    public JsonElement serialize(WarcraftContext source, Type type, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        json.add(FACTION, context.serialize(source.getFaction(), Identifier.class));
        json.add(ASSETS, context.serialize(source.getAssetSet(), Identifier.class));
        return json;
    }

    @Override
    public WarcraftContext deserialize(JsonElement element, Type type, JsonDeserializationContext context) {
        JsonObject json = element.getAsJsonObject();
        WarcraftFaction faction = context.deserialize(json.get(FACTION), Identifier.class);
        WarcraftSeason season = context.deserialize(json.get(ASSETS), Identifier.class);
        return new WarcraftContext(faction, season);
    }
}
