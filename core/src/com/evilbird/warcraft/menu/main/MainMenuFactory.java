/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.main;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.menu.Menu;

import javax.inject.Inject;

public class MainMenuFactory extends IdentifiedAssetProviderSet<Menu>
{
    @Inject
    public MainMenuFactory(
        HomeMenuFactory homeMenuFactory,
        CampaignMenuFactory campaignMenuFactory,
        NewCampaignMenuFactory newCampaignMenuFactory)
    {
        addProvider(MainMenuType.Home, homeMenuFactory);
        addProvider(MainMenuType.Campaign, campaignMenuFactory);
        addProvider(MainMenuType.CampaignNew, newCampaignMenuFactory);
    }
}
