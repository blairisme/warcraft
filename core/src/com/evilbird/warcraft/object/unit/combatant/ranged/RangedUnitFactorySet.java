/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.ranged;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import com.evilbird.warcraft.object.unit.combatant.ranged.human.BallistaFactory;
import com.evilbird.warcraft.object.unit.combatant.ranged.human.ElvenArcherFactory;
import com.evilbird.warcraft.object.unit.combatant.ranged.human.ElvenRangerFactory;
import com.evilbird.warcraft.object.unit.combatant.ranged.orc.CatapultFactory;
import com.evilbird.warcraft.object.unit.combatant.ranged.orc.TrollAxethrowerFactory;
import com.evilbird.warcraft.object.unit.combatant.ranged.orc.TrollBerserkerFactory;
import com.evilbird.warcraft.object.unit.combatant.ranged.orc.ZuljinFactory;

import javax.inject.Inject;

/**
 * Instances of this factory create Human {@link Combatant Combatants}, a
 * {@link Unit} specialization that can move and attack other Units.
 *
 * @author Blair Butterworth
 */
public class RangedUnitFactorySet extends GameFactorySet<Combatant>
{
    @Inject
    public RangedUnitFactorySet(
        BallistaFactory ballistaFactory,
        CatapultFactory catapultFactory,
        ElvenArcherFactory elvenArcherFactory,
        ElvenRangerFactory elvenRangerFactory,
        TrollAxethrowerFactory trollAxeThrowerFactory,
        TrollBerserkerFactory trollBerserkerFactory,
        ZuljinFactory zuljinFactory)
    {
        addProvider(UnitType.Ballista, ballistaFactory);
        addProvider(UnitType.Catapult, catapultFactory);
        addProvider(UnitType.ElvenArcher, elvenArcherFactory);
        addProvider(UnitType.ElvenRanger, elvenRangerFactory);
        addProvider(UnitType.TrollAxethrower, trollAxeThrowerFactory);
        addProvider(UnitType.TrollBerserker, trollBerserkerFactory);
        addProvider(UnitType.Zuljin, zuljinFactory);
    }
}