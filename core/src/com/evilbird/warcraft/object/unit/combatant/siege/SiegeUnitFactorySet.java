/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.siege;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import com.evilbird.warcraft.object.unit.combatant.siege.human.BallistaFactory;
import com.evilbird.warcraft.object.unit.combatant.siege.orc.CatapultFactory;

import javax.inject.Inject;

/**
 * Instances of this factory create siege {@link Combatant Combatants}, a
 * {@link Combatant} specialization that can move and attack using siege
 * ordinance.
 *
 * @author Blair Butterworth
 */
public class SiegeUnitFactorySet extends GameFactorySet<Combatant>
{
    @Inject
    public SiegeUnitFactorySet(
        BallistaFactory ballistaFactory,
        CatapultFactory catapultFactory)
    {
        addProvider(UnitType.Ballista, ballistaFactory);
        addProvider(UnitType.Catapult, catapultFactory);
    }
}