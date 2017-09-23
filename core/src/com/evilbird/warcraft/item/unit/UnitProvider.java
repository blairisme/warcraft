package com.evilbird.warcraft.item.unit;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.building.BuildingProvider;
import com.evilbird.warcraft.item.unit.combatant.CombatantProvider;
import com.evilbird.warcraft.item.unit.gatherer.GathererProvider;
import com.evilbird.warcraft.item.unit.resource.ResourceProvider;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class UnitProvider extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public UnitProvider(
        BuildingProvider buildingProvider,
        ResourceProvider resourceProvider,
        CombatantProvider combatantProvider,
        GathererProvider gathererProvider)
    {
        addProvider(buildingProvider);
        addProvider(resourceProvider);
        addProvider(combatantProvider);
        addProvider(gathererProvider);
    }
}
