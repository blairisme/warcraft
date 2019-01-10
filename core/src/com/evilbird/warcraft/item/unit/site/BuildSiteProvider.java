package com.evilbird.warcraft.item.unit.site;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.site.human.BarracksBuildSiteProvider;
import com.evilbird.warcraft.item.unit.site.human.FarmBuildSiteProvider;
import com.evilbird.warcraft.item.unit.site.human.TownHallBuildSiteProvider;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class BuildSiteProvider extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public BuildSiteProvider(
        BarracksBuildSiteProvider barracksBuildSiteProvider,
        FarmBuildSiteProvider farmBuildSiteProvider,
        TownHallBuildSiteProvider townHallBuildSiteProvider)
    {
        addProvider(BuildSiteType.Barracks, barracksBuildSiteProvider);
        addProvider(BuildSiteType.Farm, farmBuildSiteProvider);
        addProvider(BuildSiteType.TownHall, townHallBuildSiteProvider);
    }
}
