/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.evilbird.warcraft.state.campaign.CampaignState;
import com.evilbird.warcraft.state.scenario.ScenarioState;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Instances of this class serialize and deserialize {@link WarcraftState}
 * objects.
 *
 * @author Blair Butterworth
 */
public class WarcraftStateAdapter implements JsonSerializer<WarcraftState>, JsonDeserializer<WarcraftState>
{
    @Override
    public JsonElement serialize(WarcraftState source, Type type, JsonSerializationContext context) {
        return context.serialize(source, serializedType(source));
    }

    private Class<?> serializedType(WarcraftState state) {
        return state instanceof CampaignState ? CampaignState.class : ScenarioState.class;
    }

    @Override
    public WarcraftState deserialize(JsonElement json, Type type, JsonDeserializationContext context)
            throws JsonParseException {
        return context.deserialize(json, deserializedType(json));
    }

    private Class<?> deserializedType(JsonElement json) {
        return json.getAsJsonObject().has("introduction") ? CampaignState.class : ScenarioState.class;
    }
}
