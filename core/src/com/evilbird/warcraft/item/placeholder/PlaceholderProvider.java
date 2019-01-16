/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.placeholder;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.placeholder.human.BarracksPlaceholderProvider;
import com.evilbird.warcraft.item.placeholder.human.FarmPlaceholderProvider;
import com.evilbird.warcraft.item.placeholder.human.TownHallPlaceholderProvider;

import javax.inject.Inject;

public class PlaceholderProvider extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public PlaceholderProvider(
        BarracksPlaceholderProvider barracksBuildSiteProvider,
        FarmPlaceholderProvider farmBuildSiteProvider,
        TownHallPlaceholderProvider townHallBuildSiteProvider)
    {
        addProvider(PlaceholderType.BarracksPlaceholder, barracksBuildSiteProvider);
        addProvider(PlaceholderType.FarmPlaceholder, farmBuildSiteProvider);
        addProvider(PlaceholderType.TownHallPlaceholder, townHallBuildSiteProvider);
    }
}
