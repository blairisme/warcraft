/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.behaviour.BehaviourIdentifier;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.serialization.SerializedConstructor;
import com.evilbird.engine.game.GameService;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.ItemType;
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
    private static final String WORLD = "world";
    private static final String HUD = "hud";
    private static final String BEHAVIOUR = "behaviour";

    private ItemFactory itemFactory;
    private BehaviourFactory behaviourFactory;
    private WarcraftStateFileLoader assetLoader;

    @SerializedConstructor
    public WarcraftStateAdapter() {
        GameService service = GameService.getInstance();
        this.itemFactory = service.getItemFactory();
        this.behaviourFactory = service.getBehaviourFactory();
        this.assetLoader = new WarcraftStateFileLoader(itemFactory);
    }

    @Inject
    public WarcraftStateAdapter(
        ItemFactory itemFactory,
        BehaviourFactory behaviourFactory,
        WarcraftStateFileLoader assetLoader)
    {
        this.itemFactory = itemFactory;
        this.assetLoader = assetLoader;
        this.behaviourFactory = behaviourFactory;
    }

    @Override
    public JsonElement serialize(WarcraftState source, Type type, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        serializeHud(result, context, source.getHud());
        serializeBehaviour(result, context, source.getBehaviour());
        serializeWorld(result, context, source.getWorld());
        return result;
    }

    private void serializeWorld(JsonObject json, JsonSerializationContext context, ItemRoot world) {
        json.add(WORLD, context.serialize(world, ItemRoot.class));
    }

    private void serializeHud(JsonObject json, JsonSerializationContext context, ItemRoot hud) {
        Identifier id = hud.getIdentifier();
        json.add(HUD, context.serialize(id, Identifier.class));
    }

    private void serializeBehaviour(JsonObject json, JsonSerializationContext context, Behaviour behaviour) {
        Identifier id = behaviour.getIdentifier();
        json.add(BEHAVIOUR, context.serialize(id, Identifier.class));
    }

    @Override
    public WarcraftState deserialize(JsonElement element, Type type, JsonDeserializationContext context) {
        JsonObject json = element.getAsJsonObject();
        ItemRoot world = deserializeWorld(json, context);
        ItemRoot hud = deserializeHud(json, context);
        Behaviour behaviour = deserializeBehaviour(json, context);
        return new WarcraftState(world, hud, behaviour);
    }

    private ItemRoot deserializeWorld(JsonObject json, JsonDeserializationContext context) {
        JsonObject world = json.get(WORLD).getAsJsonObject();
        if (world.has("enum")) {
            WarcraftStateAsset identifier = context.deserialize(world, Identifier.class);
            return assetLoader.load(identifier);
        }
        return context.deserialize(world, ItemRoot.class);
    }

    private ItemRoot deserializeHud(JsonObject json, JsonDeserializationContext context) {
        Identifier identifier = context.deserialize(json.get(HUD), Identifier.class);

        ScreenViewport viewport = new ScreenViewport();
        viewport.setUnitsPerPixel(1);
//        viewport.setUnitsPerPixel(0.5f);

//        System.out.println(Gdx.graphics.getPpcX());
//        System.out.println(Gdx.graphics.getDensity());

        ItemRoot result = new ItemRoot();
        result.setViewport(viewport);
        result.setIdentifier(identifier);
        result.addItem(itemFactory.newItem((ItemType)identifier));
        return result;
    }

    private Behaviour deserializeBehaviour(JsonObject json, JsonDeserializationContext context) {
        Identifier identifier = context.deserialize(json.get(BEHAVIOUR), Identifier.class);
        return behaviourFactory.newBehaviour((BehaviourIdentifier)identifier);
    }
}
