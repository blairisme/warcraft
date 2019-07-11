/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.scenario;

import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.behaviour.BehaviourIdentifier;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.game.GameService;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.ItemType;
import com.evilbird.warcraft.state.map.WarcraftLevel;
import com.evilbird.warcraft.state.map.WarcraftLevelLoader;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import javax.inject.Inject;
import java.lang.reflect.Type;

/**
 * Instances of this class serialize and deserialize {@link WarcraftScenarioState}
 * objects.
 *
 * @author Blair Butterworth
 */
public class WarcraftScenarioAdapter
    implements JsonSerializer<WarcraftScenarioState>, JsonDeserializer<WarcraftScenarioState>
{
    private static final String MAP = "map";
    private static final String HUD = "hud";
    private static final String BEHAVIOUR = "behaviour";

    private DeviceDisplay display;
    private ItemFactory itemFactory;
    private BehaviourFactory behaviourFactory;
    private WarcraftLevelLoader stateFileLoader;

    public WarcraftScenarioAdapter() {
        GameService service = GameService.getInstance();
        this.display = service.getDevice().getDeviceDisplay();
        this.itemFactory = service.getItemFactory();
        this.behaviourFactory = service.getBehaviourFactory();
        this.stateFileLoader = new WarcraftLevelLoader(itemFactory);
    }

    @Inject
    public WarcraftScenarioAdapter(
        Device device,
        ItemFactory itemFactory,
        BehaviourFactory behaviourFactory,
        WarcraftLevelLoader assetLoader)
    {
        this.display = device.getDeviceDisplay();
        this.itemFactory = itemFactory;
        this.stateFileLoader = assetLoader;
        this.behaviourFactory = behaviourFactory;
    }

    protected JsonObject newSerializedInstance() {
        return new JsonObject();
    }

    @Override
    public JsonElement serialize(WarcraftScenarioState source, Type type, JsonSerializationContext context) {
        JsonObject result = newSerializedInstance();
        serialize(source, result, context);
        return result;
    }

    protected void serialize(WarcraftScenarioState state, JsonObject json, JsonSerializationContext context) {
        serializeHud(json, context, state.getHud());
        serializeBehaviour(json, context, state.getBehaviour());
        serializeWorld(json, context, state.getWorld());
    }

    private void serializeWorld(JsonObject json, JsonSerializationContext context, ItemRoot world) {
        json.add(MAP, context.serialize(world, ItemRoot.class));
    }

    private void serializeHud(JsonObject json, JsonSerializationContext context, ItemRoot hud) {
        Identifier id = hud.getIdentifier();
        json.add(HUD, context.serialize(id, Identifier.class));
    }

    private void serializeBehaviour(JsonObject json, JsonSerializationContext context, Behaviour behaviour) {
        Identifier id = behaviour.getIdentifier();
        json.add(BEHAVIOUR, context.serialize(id, Identifier.class));
    }

    protected WarcraftScenarioState newDeserializedInstance() {
        return new WarcraftScenarioState();
    }

    @Override
    public WarcraftScenarioState deserialize(JsonElement element, Type type, JsonDeserializationContext context) {
        WarcraftScenarioState result = newDeserializedInstance();
        deserialize(result, element.getAsJsonObject(), context);
        return result;
    }

    protected void deserialize(WarcraftScenarioState state, JsonObject json, JsonDeserializationContext context) {
        state.setWorld(deserializeWorld(json, context));
        state.setHud(deserializeHud(json, context));
        state.setBehaviour(deserializeBehaviour(json, context));
    }

    private ItemRoot deserializeWorld(JsonObject json, JsonDeserializationContext context) {
        JsonObject world = json.get(MAP).getAsJsonObject();
        if (world.has("enum")) {
            WarcraftLevel identifier = context.deserialize(world, Identifier.class);
            return stateFileLoader.load(identifier);
        }
        return context.deserialize(world, ItemRoot.class);
    }

    private ItemRoot deserializeHud(JsonObject json, JsonDeserializationContext context) {
        ItemType identifier = context.deserialize(json.get(HUD), Identifier.class);

        ScreenViewport viewport = new ScreenViewport();
        viewport.setUnitsPerPixel(display.getPixelUnits());

        ItemRoot result = new ItemRoot();
        result.setViewport(viewport);
        result.setIdentifier(identifier);
        result.addItem(itemFactory.get(identifier));
        return result;
    }

    private Behaviour deserializeBehaviour(JsonObject json, JsonDeserializationContext context) {
        Identifier identifier = context.deserialize(json.get(BEHAVIOUR), Identifier.class);
        return behaviourFactory.newBehaviour((BehaviourIdentifier)identifier);
    }
}
