/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.state.StateFactory;
import com.evilbird.engine.state.StateIdentifier;
import com.evilbird.warcraft.state.campaign.CampaignProvider;
import com.evilbird.warcraft.state.hud.HudProvider;
import com.evilbird.warcraft.state.scenario.ScenarioProvider;

import javax.inject.Inject;

public class WarcraftStateFactory implements StateFactory
{
    private IdentifiedAssetProviderSet<ItemRoot> providers;

    @Inject
    public WarcraftStateFactory(
        HudProvider hudProvider,
        CampaignProvider campaignProvider,
        ScenarioProvider scenarioProvider)
    {
        providers = new IdentifiedAssetProviderSet<>();
        providers.addProvider(hudProvider);
        providers.addProvider(campaignProvider);
        providers.addProvider(scenarioProvider);
    }

    @Override
    public void load()
    {
        providers.load();
    }

    @Override
    public ItemRoot get(StateIdentifier identifier)
    {
        return providers.get(identifier);
    }
}
