/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.scenario;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.game.GameController;
import com.evilbird.engine.game.GameService;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.common.WarcraftAssetSet;
import com.evilbird.warcraft.common.WarcraftContext;
import com.evilbird.warcraft.common.WarcraftFaction;
import com.evilbird.warcraft.item.ui.hud.HudLoader;
import com.evilbird.warcraft.state.map.Level;
import com.evilbird.warcraft.state.map.LevelLoader;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import javax.inject.Inject;
import java.lang.reflect.Type;

/**
 * Instances of this class serialize and deserialize {@link ScenarioState}
 * objects.
 *
 * @author Blair Butterworth
 */
public class ScenarioAdapter
    implements JsonSerializer<ScenarioState>, JsonDeserializer<ScenarioState>
{
    private static final String FACTION = "faction";
    private static final String ASSETS = "assets";
    private static final String LEVEL = "level";
    private static final String BEHAVIOUR = "behaviour";

    private GameController assetLoader;
    private HudLoader hudLoader;
    private LevelLoader levelLoader;
    private BehaviourFactory behaviourFactory;

    public ScenarioAdapter() {
        GameService service = GameService.getInstance();
        this.assetLoader = service.getEngine();
        this.hudLoader = new HudLoader(service.getDevice(), service.getItemFactory());
        this.levelLoader = new LevelLoader(service.getItemFactory());
        this.behaviourFactory = service.getBehaviourFactory();
    }

    @Inject
    public ScenarioAdapter(
        GameController assetLoader,
        HudLoader hudLoader,
        LevelLoader levelLoader,
        BehaviourFactory behaviourFactory)
    {
        this.assetLoader = assetLoader;
        this.hudLoader = hudLoader;
        this.levelLoader = levelLoader;
        this.behaviourFactory = behaviourFactory;
    }

    protected JsonObject newSerializedInstance() {
        return new JsonObject();
    }

    @Override
    public JsonElement serialize(ScenarioState source, Type type, JsonSerializationContext context) {
        JsonObject result = newSerializedInstance();
        serialize(source, result, context);
        return result;
    }

    protected void serialize(ScenarioState state, JsonObject json, JsonSerializationContext context) {
        WarcraftContext assetContext = state.getContext();
        json.add(FACTION, context.serialize(assetContext.getFaction(), Identifier.class));
        json.add(ASSETS, context.serialize(assetContext.getAssetSet(), Identifier.class));
        json.add(LEVEL, context.serialize(state.getWorld(), ItemRoot.class));
        json.add(BEHAVIOUR, context.serialize(state.getBehaviour().getIdentifier(), Identifier.class));
    }

    protected ScenarioState newDeserializedInstance() {
        return new ScenarioState();
    }

    @Override
    public ScenarioState deserialize(JsonElement element, Type type, JsonDeserializationContext context) {
        ScenarioState result = newDeserializedInstance();
        deserialize(result, element.getAsJsonObject(), context);
        return result;
    }

    protected void deserialize(ScenarioState state, JsonObject json, JsonDeserializationContext context) {
        WarcraftContext assetContext = deserializeContext(json, context);
        assetLoader.loadAssets(assetContext);
        state.setContext(assetContext);
        state.setWorld(deserializeWorld(json, context));
        state.setHud(hudLoader.get());
        state.setBehaviour(deserializeBehaviour(json, context));
    }

    private WarcraftContext deserializeContext(JsonObject json, JsonDeserializationContext context) {
        WarcraftFaction faction = context.deserialize(json.get(FACTION), Identifier.class);
        WarcraftAssetSet assets = context.deserialize(json.get(ASSETS), Identifier.class);
        return new WarcraftContext(faction, assets);
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
