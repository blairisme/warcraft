package com.evilbird.warcraft.state.campaign;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.state.campaign.human.HumanCampaignProvider;
import com.evilbird.warcraft.state.campaign.orc.OrcCampaignProvider;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class CampaignProvider extends IdentifiedAssetProviderSet<ItemRoot>
{
    @Inject
    public CampaignProvider(
        HumanCampaignProvider humanCampaign,
        OrcCampaignProvider orcCampaign)
    {
        addProvider(humanCampaign);
        addProvider(orcCampaign);
    }
}
