/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant.human;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;

/**
 * Instances of this factory create Human {@link Combatant Combatants}, a
 * {@link Unit} specialization that can move and attack other Units.
 *
 * @author Blair Butterworth
 */
public class HumanCombatantFactory extends GameFactorySet<Combatant>
{
    @Inject
    public HumanCombatantFactory(
        AnduinLotharFactory anduinLotharFactory,
        BallistaFactory ballistaFactory,
        BattleshipFactory battleshipFactory,
        DwarvenDemolitionSquadFactory dwarvenDemolitionSquadFactory,
        ElvenArcherFactory elvenArcherFactory,
        ElvenDestroyerFactory elvenDestroyerFactory,
        ElvenRangerFactory elvenRangerFactory,
        FootmanFactory footmanFactory,
        GnomishFlyingMachineFactory gnomishFlyingMachineFactory,
        GnomishSubmarineFactory gnomishSubmarineFactory,
        GryphonRiderFactory gryphonRiderFactory,
        KnightFactory knightFactory,
        MageFactory mageFactory,
        PaladinFactory paladinFactory,
        TransportFactory transportFactory)
    {
        addProvider(UnitType.AnduinLothar, anduinLotharFactory);
        addProvider(UnitType.Ballista, ballistaFactory);
        addProvider(UnitType.Battleship, battleshipFactory);
        addProvider(UnitType.DwarvenDemolitionSquad, dwarvenDemolitionSquadFactory);
        addProvider(UnitType.ElvenArcher, elvenArcherFactory);
        addProvider(UnitType.ElvenDestroyer,  elvenDestroyerFactory);
        addProvider(UnitType.ElvenRanger, elvenRangerFactory);
        addProvider(UnitType.Footman, footmanFactory);
        addProvider(UnitType.GnomishFlyingMachine, gnomishFlyingMachineFactory);
        addProvider(UnitType.GnomishSubmarine, gnomishSubmarineFactory);
        addProvider(UnitType.GryphonRider, gryphonRiderFactory);
        addProvider(UnitType.Knight, knightFactory);
        addProvider(UnitType.Mage, mageFactory);
        addProvider(UnitType.Paladin, paladinFactory);
        addProvider(UnitType.Transport, transportFactory);
    }
}
