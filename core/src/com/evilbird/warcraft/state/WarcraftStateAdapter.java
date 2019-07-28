/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.game.GameService;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.item.ui.display.HudLoader;
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
public class WarcraftStateAdapter implements JsonSerializer<WarcraftState>, JsonDeserializer<WarcraftState>
{
    private static final String LEVEL = "level";
    private static final String BEHAVIOUR = "behaviour";

    private HudLoader hudLoader;
    private LevelLoader levelLoader;
    private BehaviourFactory behaviourFactory;

    public WarcraftStateAdapter() {
        GameService service = GameService.getInstance();
        this.hudLoader = new HudLoader(service.getDevice(), service.getItemFactory());
        this.levelLoader = new LevelLoader(service.getDevice(), service.getItemFactory());
        this.behaviourFactory = service.getBehaviourFactory();
    }

    @Inject
    public WarcraftStateAdapter(
        HudLoader hudLoader,
        LevelLoader levelLoader,
        BehaviourFactory behaviourFactory)
    {
        this.hudLoader = hudLoader;
        this.levelLoader = levelLoader;
        this.behaviourFactory = behaviourFactory;
    }

    @Override
    public JsonElement serialize(WarcraftState source, Type type, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        json.add(LEVEL, context.serialize(source.getWorld(), ItemRoot.class));
        json.add(BEHAVIOUR, context.serialize(source.getBehaviour().getIdentifier(), Identifier.class));
        return json;
    }

    @Override
    public WarcraftState deserialize(JsonElement element, Type type, JsonDeserializationContext context) {
        JsonObject json = element.getAsJsonObject();
        WarcraftState state = new WarcraftState();
        state.setHud(hudLoader.get());
        state.setBehaviour(deserializeBehaviour(json, context));
        state.setWorld(deserializeWorld(json, context));
        return state;
    }

    private ItemRoot deserializeWorld(JsonObject json, JsonDeserializationContext context) {
        JsonObject world = json.get(LEVEL).getAsJsonObject();
        if (world.size() == 2) {
            Level identifier = context.deserialize(world, Identifier.class);
            return levelLoader.load(identifier);
        }
        return context.deserialize(world, ItemRoot.class);
    }

    private Behaviour deserializeBehaviour(JsonObject json, JsonDeserializationContext context) {
        Identifier identifier = context.deserialize(json.get(BEHAVIOUR), Identifier.class);
        return behaviourFactory.get(identifier);
    }
}
