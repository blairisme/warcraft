/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.campaign;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.state.campaign.human.HumanCampaign;
import com.evilbird.warcraft.state.campaign.human.HumanLevel1;

import javax.inject.Inject;

public class CampaignProvider extends IdentifiedAssetProviderSet<ItemRoot>
{
    @Inject
    public CampaignProvider(HumanLevel1 humanLevel1) {
        addProvider(HumanCampaign.Level1, humanLevel1);
    }
}
