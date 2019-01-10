package com.evilbird.warcraft.action.identifier;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.warcraft.item.site.BuildSiteType;

public enum BuildSiteAction implements ActionIdentifier
{
    BuildBarracksSite   (BuildSiteType.Barracks),
    BuildFarmSite       (BuildSiteType.Farm),
    BuildTownHallSite   (BuildSiteType.TownHall);

    private BuildSiteType buildSiteType;

    private BuildSiteAction(BuildSiteType buildSiteType) {
        this.buildSiteType = buildSiteType;
    }

    public BuildSiteType getBuildSiteType() {
        return buildSiteType;
    }
}
