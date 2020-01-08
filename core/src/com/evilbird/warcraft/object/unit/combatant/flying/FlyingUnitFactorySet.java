/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.flying;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import com.evilbird.warcraft.object.unit.combatant.flying.human.GnomishFlyingMachineFactory;
import com.evilbird.warcraft.object.unit.combatant.flying.human.GryphonRiderFactory;
import com.evilbird.warcraft.object.unit.combatant.flying.neutral.DaemonFactory;
import com.evilbird.warcraft.object.unit.combatant.flying.neutral.EyeOfKilroggFactory;
import com.evilbird.warcraft.object.unit.combatant.flying.orc.DragonFactory;
import com.evilbird.warcraft.object.unit.combatant.flying.orc.GoblinZeppelinFactory;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link FlyingUnit FlyingUnits}, a
 * {@link Combatant} specialization that can move and attack other Units.
 *
 * @author Blair Butterworth
 */
public class FlyingUnitFactorySet extends GameFactorySet<FlyingUnit>
{
    @Inject
    public FlyingUnitFactorySet(
        DaemonFactory daemonFactory,
        DragonFactory dragonFactory,
        EyeOfKilroggFactory eyeOfKilroggFactory,
        GoblinZeppelinFactory goblinZeppelinFactory,
        GnomishFlyingMachineFactory gnomishFlyingMachineFactory,
        GryphonRiderFactory gryphonRiderFactory)
    {
        addProvider(UnitType.Daemon, daemonFactory);
        addProvider(UnitType.Dragon, dragonFactory);
        addProvider(UnitType.EyeOfKilrogg, eyeOfKilroggFactory);
        addProvider(UnitType.GoblinZeppelin, goblinZeppelinFactory);
        addProvider(UnitType.GnomishFlyingMachine, gnomishFlyingMachineFactory);
        addProvider(UnitType.GryphonRider, gryphonRiderFactory);
    }
}
