/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.game.GameService;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.state.behaviour.BehaviourStateFactory;
import com.evilbird.warcraft.state.behaviour.BehaviourStateIdentifier;
import com.evilbird.warcraft.state.hud.HudStateFactory;
import com.evilbird.warcraft.state.hud.HudStateIdentifier;
import com.google.gson.*;

import javax.inject.Inject;
import java.lang.reflect.Type;

public class WarcraftStateAdapter implements JsonSerializer<WarcraftState>, JsonDeserializer<WarcraftState>
{
    private static final String WORLD = "world";
    private static final String HUD = "hud";
    private static final String BEHAVIOUR = "behaviour";

    private HudStateFactory hudFactory;
    private BehaviourStateFactory behaviourFactory;

    public WarcraftStateAdapter() {
        GameService service = GameService.getInstance();
        this.hudFactory = new HudStateFactory(service.getItemFactory());
        this.behaviourFactory = new BehaviourStateFactory(service.getBehaviourFactory());
    }

    @Inject
    public WarcraftStateAdapter(BehaviourStateFactory behaviourFactory, HudStateFactory hudFactory) {
        this.hudFactory = hudFactory;
        this.behaviourFactory = behaviourFactory;
    }

    @Override
    public JsonElement serialize(WarcraftState source, Type type, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        serializeHud(result, context, source.getHud());
        serializeBehaviour(result, context, source.getBehaviour());
        serializeWorld(result, context, source.getWorld());
        return null;
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
    public WarcraftState deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject json = element.getAsJsonObject();
        ItemRoot world = deserializeWorld(json, context);
        ItemRoot hud = deserializeHud(json, context);
        Behaviour behaviour = deserializeBehaviour(json, context);
        return new WarcraftState(world, hud, behaviour);
    }

    private ItemRoot deserializeWorld(JsonObject json, JsonDeserializationContext context) {
        return context.deserialize(json.get(WORLD), ItemRoot.class);
    }

    private ItemRoot deserializeHud(JsonObject json, JsonDeserializationContext context) {
        Identifier identifier = context.deserialize(json.get(HUD), Identifier.class);
        return hudFactory.get((HudStateIdentifier)identifier);
    }

    private Behaviour deserializeBehaviour(JsonObject json, JsonDeserializationContext context) {
        Identifier identifier = context.deserialize(json.get(BEHAVIOUR), Identifier.class);
        return behaviourFactory.get((BehaviourStateIdentifier)identifier);
    }
}
