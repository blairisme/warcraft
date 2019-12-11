/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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