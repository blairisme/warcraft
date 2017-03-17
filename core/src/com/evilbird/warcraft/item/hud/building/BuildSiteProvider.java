package com.evilbird.warcraft.item.hud.building;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.hud.building.human.BarracksBuildSiteProvider;
import com.evilbird.warcraft.item.hud.building.human.FarmBuildSiteProvider;
import com.evilbird.warcraft.item.hud.building.human.TownHallBuildSiteProvider;

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
        addProvider(BuildSiteType.BarracksBuildSite, barracksBuildSiteProvider);
        addProvider(BuildSiteType.FarmBuildSite, farmBuildSiteProvider);
        addProvider(BuildSiteType.TownHallBuildSite, townHallBuildSiteProvider);
    }
}
