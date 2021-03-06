/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.naval;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import com.evilbird.warcraft.object.unit.combatant.naval.human.BattleshipFactory;
import com.evilbird.warcraft.object.unit.combatant.naval.human.ElvenDestroyerFactory;
import com.evilbird.warcraft.object.unit.combatant.naval.human.GnomishSubmarineFactory;
import com.evilbird.warcraft.object.unit.combatant.naval.human.TransportFactory;
import com.evilbird.warcraft.object.unit.combatant.naval.orc.FerryFactory;
import com.evilbird.warcraft.object.unit.combatant.naval.orc.GiantTurtleFactory;
import com.evilbird.warcraft.object.unit.combatant.naval.orc.OgreJuggernaughtFactory;
import com.evilbird.warcraft.object.unit.combatant.naval.orc.TrollDestroyerFactory;
import com.evilbird.warcraft.object.unit.combatant.siege.human.BallistaFactory;

import javax.inject.Inject;

/**
 * Instances of this factory create naval units, a
 * {@link Unit} specialization that can move and attack other Units.
 *
 * @author Blair Butterworth
 */
public class NavalUnitFactorySet extends GameFactorySet<Combatant>
{
    @Inject
    public NavalUnitFactorySet(
        BallistaFactory ballistaFactory,
        BattleshipFactory battleshipFactory,
        ElvenDestroyerFactory elvenDestroyerFactory,
        FerryFactory ferryFactory,
        GiantTurtleFactory giantTurtleFactory,
        GnomishSubmarineFactory gnomishSubmarineFactory,
        OgreJuggernaughtFactory ogreJuggernaughtFactory,
        TransportFactory transportFactory,
        TrollDestroyerFactory trollDestroyerFactory)
    {
        addProvider(UnitType.Ballista, ballistaFactory);
        addProvider(UnitType.Battleship, battleshipFactory);
        addProvider(UnitType.ElvenDestroyer, elvenDestroyerFactory);
        addProvider(UnitType.Ferry, ferryFactory);
        addProvider(UnitType.GiantTurtle, giantTurtleFactory);
        addProvider(UnitType.GnomishSubmarine, gnomishSubmarineFactory);
        addProvider(UnitType.OgreJuggernaught, ogreJuggernaughtFactory);
        addProvider(UnitType.Transport, transportFactory);
        addProvider(UnitType.TrollDestroyer, trollDestroyerFactory);
    }
}