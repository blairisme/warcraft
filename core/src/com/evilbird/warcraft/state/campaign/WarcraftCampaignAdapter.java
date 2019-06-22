/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.campaign;

import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.menu.MenuIdentifier;
import com.evilbird.engine.state.StateIdentifier;
import com.evilbird.warcraft.state.map.WarcraftLevelLoader;
import com.evilbird.warcraft.state.scenario.WarcraftScenarioAdapter;
import com.evilbird.warcraft.state.scenario.WarcraftScenarioState;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import javax.inject.Inject;

/**
 * Instances of this class serialize and deserialize
 * {@link WarcraftCampaignState WarcraftCampaignStates}.
 *
 * @author Blair Butterworth
 */
public class WarcraftCampaignAdapter extends WarcraftScenarioAdapter
{
    private static final String INTRO = "introduction";
    private static final String NEXT = "next";

    public WarcraftCampaignAdapter() {
    }

    @Inject
    public WarcraftCampaignAdapter(
        Device device,
        ItemFactory itemFactory,
        BehaviourFactory behaviourFactory,
        WarcraftLevelLoader assetLoader)
    {
        super(device, itemFactory, behaviourFactory, assetLoader);
    }

    @Override
    protected void serialize(WarcraftScenarioState state, JsonObject json, JsonSerializationContext context) {
        super.serialize(state, json, context);
        WarcraftCampaignState campaign = (WarcraftCampaignState)state;
        serializeIntro(campaign, json, context);
        serializeNext(campaign, json, context);
    }

    private void serializeIntro(WarcraftCampaignState state, JsonObject json, JsonSerializationContext context) {
        Identifier intro = state.getIntroductionMenu();
        json.add(INTRO, context.serialize(intro, Identifier.class));
    }

    private void serializeNext(WarcraftCampaignState state, JsonObject json, JsonSerializationContext context) {
        Identifier next = state.getNextState();
        json.add(NEXT, context.serialize(next, Identifier.class));
    }

    protected WarcraftScenarioState newDeserializedInstance() {
        return new WarcraftCampaignState();
    }

    protected void deserialize(WarcraftScenarioState state, JsonObject json, JsonDeserializationContext context) {
        super.deserialize(state, json, context);
        WarcraftCampaignState campaign = (WarcraftCampaignState)state;
        campaign.setIntroductionMenu(deserializeIntro(json, context));
        campaign.setNextState(deserializeNext(json, context));
    }

    private MenuIdentifier deserializeIntro(JsonObject json, JsonDeserializationContext context) {
        return context.deserialize(json.get(INTRO), Identifier.class);
    }

    private StateIdentifier deserializeNext(JsonObject json, JsonDeserializationContext context) {
        if (json.has(NEXT)) {
            return context.deserialize(json.get(NEXT), Identifier.class);
        }
        return null;
    }
}
