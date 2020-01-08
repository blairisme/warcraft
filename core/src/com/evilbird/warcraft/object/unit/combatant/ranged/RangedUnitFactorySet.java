/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.ranged;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import com.evilbird.warcraft.object.unit.combatant.ranged.human.ElvenArcherFactory;
import com.evilbird.warcraft.object.unit.combatant.ranged.human.ElvenRangerFactory;
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
        ElvenArcherFactory elvenArcherFactory,
        ElvenRangerFactory elvenRangerFactory,
        TrollAxethrowerFactory trollAxeThrowerFactory,
        TrollBerserkerFactory trollBerserkerFactory,
        ZuljinFactory zuljinFactory)
    {
        addProvider(UnitType.ElvenArcher, elvenArcherFactory);
        addProvider(UnitType.ElvenRanger, elvenRangerFactory);
        addProvider(UnitType.TrollAxethrower, trollAxeThrowerFactory);
        addProvider(UnitType.TrollBerserker, trollBerserkerFactory);
        addProvider(UnitType.Zuljin, zuljinFactory);
    }
}