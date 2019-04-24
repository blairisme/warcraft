/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.building.BuildingFactory;
import com.evilbird.warcraft.item.unit.combatant.CombatantProvider;
import com.evilbird.warcraft.item.unit.gatherer.GathererProvider;
import com.evilbird.warcraft.item.unit.resource.ResourceProvider;

import javax.inject.Inject;

public class UnitFactory extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public UnitFactory(
        BuildingFactory buildingFactory,
        ResourceProvider resourceProvider,
        CombatantProvider combatantProvider,
        GathererProvider gathererProvider)
    {
        addProvider(buildingFactory);
        addProvider(resourceProvider);
        addProvider(combatantProvider);
        addProvider(gathererProvider);
    }
}
