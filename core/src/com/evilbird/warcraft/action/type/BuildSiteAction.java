package com.evilbird.warcraft.action.type;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.warcraft.item.hud.building.BuildSiteType;

public enum BuildSiteAction implements ActionIdentifier
{
    BuildBarracksSite   (BuildSiteType.BarracksBuildSite),
    BuildFarmSite       (BuildSiteType.FarmBuildSite),
    BuildTownHallSite   (BuildSiteType.TownHallBuildSite);

    private BuildSiteType buildSiteType;

    private BuildSiteAction(BuildSiteType buildSiteType) {
        this.buildSiteType = buildSiteType;
    }

    public BuildSiteType getBuildSiteType() {
        return buildSiteType;
    }
}
