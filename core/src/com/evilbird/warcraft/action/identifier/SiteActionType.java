/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.identifier;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.warcraft.item.site.BuildSiteType;

public enum SiteActionType implements ActionIdentifier
{
    BuildBarracksSite   (BuildSiteType.Barracks),
    BuildFarmSite       (BuildSiteType.Farm),
    BuildTownHallSite   (BuildSiteType.TownHall);

    private BuildSiteType buildSiteType;

    private SiteActionType(BuildSiteType buildSiteType) {
        this.buildSiteType = buildSiteType;
    }

    public BuildSiteType getBuildSiteType() {
        return buildSiteType;
    }
}
