/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.state;

import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.serialization.SerializerUtils;
import com.evilbird.engine.game.GameService;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.warcraft.object.display.UserInterfaceBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import javax.inject.Inject;
import java.lang.reflect.Type;

/**
 * Instances of this class serialize and deserialize {@link WarcraftState}
 * objects.
 *
 * @author Blair Butterworth
 */
public class WarcraftStateSerializer implements JsonSerializer<WarcraftState>, JsonDeserializer<WarcraftState>
{
    private static final String LEVEL = "level";
    private static final String BEHAVIOUR = "behaviour";

    private LevelLoader levelLoader;
    private WarcraftMusic musicLoader;
    private BehaviourFactory behaviourFactory;
    private UserInterfaceBuilder userInterfaceLoader;

    public WarcraftStateSerializer() {
        GameService service = GameService.getInstance();
        this.userInterfaceLoader = new UserInterfaceBuilder(service.getDevice(), service.getObjectFactory());
        this.levelLoader = new LevelLoader(service.getDevice(), service.getObjectFactory());
        this.behaviourFactory = service.getBehaviourFactory();
        this.musicLoader = new WarcraftMusic(service.getDevice());
    }

    @Inject
    public WarcraftStateSerializer(
        UserInterfaceBuilder userInterfaceLoader,
        LevelLoader levelLoader,
        WarcraftMusic musicLoader,
        BehaviourFactory behaviourFactory)
    {
        this.userInterfaceLoader = userInterfaceLoader;
        this.levelLoader = levelLoader;
        this.musicLoader = musicLoader;
        this.behaviourFactory = behaviourFactory;
    }

    @Override
    public JsonElement serialize(WarcraftState source, Type type, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        serializeContext(source, context, json);
        serializeLevel(source, context, json);
        serializeBehaviour(source, context, json);
        return json;
    }

    private void serializeContext(WarcraftState source, JsonSerializationContext context, JsonObject json) {
        JsonElement serializedContext = context.serialize(source.getContext(), WarcraftContext.class);
        SerializerUtils.merge(serializedContext.getAsJsonObject(), json);
    }

    private void serializeLevel(WarcraftState source, JsonSerializationContext context, JsonObject json) {
        JsonElement serializedLevel = context.serialize(source.getWorld(), GameObjectContainer.class);
        json.add(LEVEL, serializedLevel);
    }

    private void serializeBehaviour(WarcraftState source, JsonSerializationContext context, JsonObject json) {
        JsonElement serializedBehaviour = context.serialize(source.getBehaviour().getIdentifier(), Identifier.class);
        json.add(BEHAVIOUR, serializedBehaviour);
    }

    @Override
    public WarcraftState deserialize(JsonElement element, Type type, JsonDeserializationContext context) {
        JsonObject json = element.getAsJsonObject();
        WarcraftState state = deserializedInstance();
        deserializeContext(json, context, state);
        deserializeBehaviour(json, context, state);
        deserializeLevel(json, context, state);
        return state;
    }

    private WarcraftState deserializedInstance() {
        WarcraftState state = new WarcraftState();
        state.setHud(userInterfaceLoader.get());
        state.setMusic(musicLoader.getMusic());
        return state;
    }

    private void deserializeContext(JsonObject json, JsonDeserializationContext context,  WarcraftState state) {
        WarcraftContext deserializedContext = context.deserialize(json, WarcraftContext.class);
        state.setContext(deserializedContext);
    }

    private void deserializeBehaviour(JsonObject json, JsonDeserializationContext context,  WarcraftState state) {
        Identifier deserializedBehaviour = context.deserialize(json.get(BEHAVIOUR), Identifier.class);
        state.setBehaviour(behaviourFactory.get(deserializedBehaviour));
    }

    private void deserializeLevel(JsonObject json, JsonDeserializationContext context,  WarcraftState state) {
        GameObjectContainer deserializedLevel = deserializeLevel(json.get(LEVEL), context);
        state.setWorld(deserializedLevel);
    }

    private GameObjectContainer deserializeLevel(JsonElement element, JsonDeserializationContext context) {
        JsonObject json = element.getAsJsonObject();
        if (isEmbedded(json)) {
            Level level = context.deserialize(json, Level.class);
            return levelLoader.load(level.getFilePath());
        }
        return context.deserialize(json, GameObjectContainer.class);
    }

    private boolean isEmbedded(JsonObject json) {
        return json.size() == 2;
    }
}
