/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour;

import com.badlogic.gdx.ai.GdxAI;
import com.badlogic.gdx.ai.Timepiece;
import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.game.GameService;
import com.evilbird.warcraft.behaviour.ai.AiTimeService;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import javax.inject.Inject;
import java.lang.reflect.Type;

/**
 * Instances of this class serialize and deserialize {@link WarcraftBehaviour}
 * objects, storing their identifier and the time stored in the
 * {@link GdxAI#getTimepiece() AI time service}. When deserialized, the stored
 * identifier will be used to create a new behaviour and the time service set
 * using the stored time.
 *
 * @author Blair Butterworth
 */
public class WarcraftBehaviourSerializer implements JsonSerializer<Behaviour>, JsonDeserializer<Behaviour>
{
    private static final String TYPE = "type";
    private static final String TIME = "time";

    private BehaviourFactory behaviourFactory;

    @Inject
    public WarcraftBehaviourSerializer(BehaviourFactory behaviourFactory) {
        this.behaviourFactory = behaviourFactory;
    }

    private WarcraftBehaviourSerializer() {
        GameService service = GameService.getInstance();
        this.behaviourFactory = service.getBehaviourFactory();
    }

    @Override
    public JsonElement serialize(Behaviour source, Type type, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        serializeType(json, source, context);
        serializeTime(json, source);
        return json;
    }

    private void serializeType(JsonObject json, Behaviour behaviour, JsonSerializationContext context) {
        Identifier type = behaviour.getIdentifier();
        json.add(TYPE, context.serialize(type, Identifier.class));
    }

    private void serializeTime(JsonObject json, Behaviour behaviour) {
        Timepiece timeService = GdxAI.getTimepiece();
        json.addProperty(TIME, timeService.getTime());
    }

    @Override
    public Behaviour deserialize(JsonElement source, Type type, JsonDeserializationContext context) {
        JsonObject json = (JsonObject)source;
        deserializeTime(json);
        return deserializeBehaviour(json, context);
    }

    private void deserializeTime(JsonObject json) {
        JsonPrimitive time = (JsonPrimitive)json.get(TIME);
        AiTimeService timeService = new AiTimeService(time.getAsFloat());
        GdxAI.setTimepiece(timeService);
    }

    private Behaviour deserializeBehaviour(JsonObject json, JsonDeserializationContext context) {
        JsonElement type = json.get(TYPE);
        Identifier identifier = context.deserialize(type, Identifier.class);
        return behaviourFactory.get(identifier);
    }
}
