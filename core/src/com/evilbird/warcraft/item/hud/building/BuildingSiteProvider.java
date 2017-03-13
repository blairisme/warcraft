package com.evilbird.warcraft.item.hud.building;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.hud.building.human.BarracksBuildingSiteProvider;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class BuildingSiteProvider extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public BuildingSiteProvider(
        BarracksBuildingSiteProvider barracksBuildingSiteProvider)
    {
        addProvider(BuildingSiteType.BarracksBuildingSite, barracksBuildingSiteProvider);
    }
}
