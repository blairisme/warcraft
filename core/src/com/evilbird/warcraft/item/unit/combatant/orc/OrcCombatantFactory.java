/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant.orc;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;

/**
 * Instances of this factory create Orc {@link Combatant Combatants}, a
 * {@link Unit} specialization that can move and attack other Units.
 *
 * @author Blair Butterworth
 */
public class OrcCombatantFactory extends GameFactorySet<Combatant>
{
    @Inject
    public OrcCombatantFactory(
        CatapultFactory catapultFactory,
        DeathKnightFactory deathKnightFactory,
        DragonFactory dragonFactory,
        FerryFactory ferryFactory,
        GiantTurtleFactory giantTurtleFactory,
        GoblinSappersFactory goblinSappersFactory,
        GoblinZeppelinFactory goblinZeppelinFactory,
        GruntFactory gruntFactory,
        OgreFactory ogreFactory,
        OgreJuggernaughtFactory ogreJuggernaughtFactory,
        OgreMageFactory ogreMageFactory,
        TrollAxethrowerFactory trollAxeThrowerFactory,
        TrollBerserkerFactory trollBerserkerFactory,
        TrollDestroyerFactory trollDestroyerFactory,
        ZuljinFactory zuljinFactory)
    {
        addProvider(UnitType.Catapult, catapultFactory);
        addProvider(UnitType.DeathKnight, deathKnightFactory);
        addProvider(UnitType.Dragon, dragonFactory);
        addProvider(UnitType.Ferry, ferryFactory);
        addProvider(UnitType.GiantTurtle, giantTurtleFactory);
        addProvider(UnitType.GoblinSappers, goblinSappersFactory);
        addProvider(UnitType.GoblinZeppelin, goblinZeppelinFactory);
        addProvider(UnitType.Grunt, gruntFactory);
        addProvider(UnitType.Ogre, ogreFactory);
        addProvider(UnitType.OgreJuggernaught, ogreJuggernaughtFactory);
        addProvider(UnitType.OgreMage, ogreMageFactory);
        addProvider(UnitType.TrollAxethrower, trollAxeThrowerFactory);
        addProvider(UnitType.TrollBerserker, trollBerserkerFactory);
        addProvider(UnitType.TrollDestroyer, trollDestroyerFactory);
        addProvider(UnitType.Zuljin, zuljinFactory);
    }
}
