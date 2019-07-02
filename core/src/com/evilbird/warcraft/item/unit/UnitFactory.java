/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.building.BuildingFactory;
import com.evilbird.warcraft.item.unit.combatant.CombatantFactory;
import com.evilbird.warcraft.item.unit.critter.CritterFactory;
import com.evilbird.warcraft.item.unit.gatherer.GathererFactory;
import com.evilbird.warcraft.item.unit.resource.ResourceFactory;

import javax.inject.Inject;

/**
 * Instances of this factory produce {@link Unit Units}, game objects that the
 * user can control and interact with.
 *
 * @author Blair Butterworth
 */
public class UnitFactory extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public UnitFactory(
        BuildingFactory buildingFactory,
        CombatantFactory combatantFactory,
        CritterFactory critterFactory,
        GathererFactory gathererFactory,
        ResourceFactory resourceFactory)
    {
        addProvider(buildingFactory);
        addProvider(combatantFactory);
        addProvider(critterFactory);
        addProvider(gathererFactory);
        addProvider(resourceFactory);
    }
}
