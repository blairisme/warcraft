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
import com.evilbird.engine.game.GameController;
import com.evilbird.engine.menu.MenuIdentifier;
import com.evilbird.engine.state.StateIdentifier;
import com.evilbird.warcraft.item.ui.display.HudLoader;
import com.evilbird.warcraft.state.map.LevelLoader;
import com.evilbird.warcraft.state.scenario.ScenarioAdapter;
import com.evilbird.warcraft.state.scenario.ScenarioState;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import javax.inject.Inject;

/**
 * Instances of this class serialize and deserialize
 * {@link CampaignState WarcraftCampaignStates}.
 *
 * @author Blair Butterworth
 */
public class CampaignAdapter extends ScenarioAdapter
{
    private static final String INTRO = "introduction";
    private static final String NEXT = "next";

    public CampaignAdapter() {
    }

    @Inject
    public CampaignAdapter(
        GameController controller,
        HudLoader hudLoader,
        LevelLoader levelLoader,
        BehaviourFactory behaviourFactory)
    {
        super(controller, hudLoader, levelLoader, behaviourFactory);
    }

    @Override
    protected void serialize(ScenarioState state, JsonObject json, JsonSerializationContext context) {
        super.serialize(state, json, context);
        CampaignState campaign = (CampaignState)state;
        serializeIntro(campaign, json, context);
        serializeNext(campaign, json, context);
    }

    private void serializeIntro(CampaignState state, JsonObject json, JsonSerializationContext context) {
        Identifier intro = state.getIntroductionMenu();
        json.add(INTRO, context.serialize(intro, Identifier.class));
    }

    private void serializeNext(CampaignState state, JsonObject json, JsonSerializationContext context) {
        Identifier next = state.getNextState();
        json.add(NEXT, context.serialize(next, Identifier.class));
    }

    protected ScenarioState newDeserializedInstance() {
        return new CampaignState();
    }

    protected void deserialize(ScenarioState state, JsonObject json, JsonDeserializationContext context) {
        super.deserialize(state, json, context);
        CampaignState campaign = (CampaignState)state;
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
