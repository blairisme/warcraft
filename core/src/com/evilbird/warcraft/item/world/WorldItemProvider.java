package com.evilbird.warcraft.item.world;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.world.building.BuildingProvider;
import com.evilbird.warcraft.item.world.resource.ResourceProvider;
import com.evilbird.warcraft.item.world.unit.UnitProvider;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class WorldItemProvider extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public WorldItemProvider(
        BuildingProvider buildingProvider,
        ResourceProvider resourceProvider,
        UnitProvider unitProvider)
    {
        addProvider(buildingProvider);
        addProvider(resourceProvider);
        addProvider(unitProvider);
    }
}
