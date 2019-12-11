/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.warcraft.object.unit.building.BuildingFactory;
import com.evilbird.warcraft.object.unit.combatant.CombatantFactorySet;
import com.evilbird.warcraft.object.unit.conjured.ConjuredFactory;
import com.evilbird.warcraft.object.unit.critter.CritterFactory;
import com.evilbird.warcraft.object.unit.resource.ResourceFactory;

import javax.inject.Inject;

/**
 * Instances of this factory produce {@link Unit Units}, game objects that the
 * user can control and interact with.
 *
 * @author Blair Butterworth
 */
public class UnitFactory extends GameFactorySet<Unit>
{
    @Inject
    public UnitFactory(
        BuildingFactory buildingFactory,
        CombatantFactorySet combatantFactory,
        ConjuredFactory conjuredFactory,
        CritterFactory critterFactory,
        ResourceFactory resourceFactory)
    {
        addProvider(buildingFactory);
        addProvider(conjuredFactory);
        addProvider(combatantFactory);
        addProvider(critterFactory);
        addProvider(resourceFactory);
    }
}
