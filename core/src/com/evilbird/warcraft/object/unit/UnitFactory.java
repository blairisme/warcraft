/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
