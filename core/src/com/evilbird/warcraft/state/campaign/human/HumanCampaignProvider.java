package com.evilbird.warcraft.state.campaign.human;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.ItemRoot;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class HumanCampaignProvider extends IdentifiedAssetProviderSet<ItemRoot>
{
    @Inject
    public HumanCampaignProvider(Level1 level1)
    {
        addProvider(HumanCampaign.Level1, level1);
    }
}
