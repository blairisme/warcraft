/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
