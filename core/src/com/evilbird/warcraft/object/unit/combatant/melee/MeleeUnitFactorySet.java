/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.melee;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import com.evilbird.warcraft.object.unit.combatant.melee.human.AnduinLotharFactory;
import com.evilbird.warcraft.object.unit.combatant.melee.human.DwarvenDemolitionSquadFactory;
import com.evilbird.warcraft.object.unit.combatant.melee.human.FootmanFactory;
import com.evilbird.warcraft.object.unit.combatant.melee.human.KnightFactory;
import com.evilbird.warcraft.object.unit.combatant.melee.neutral.SkeletonFactory;
import com.evilbird.warcraft.object.unit.combatant.melee.orc.GoblinSappersFactory;
import com.evilbird.warcraft.object.unit.combatant.melee.orc.GruntFactory;
import com.evilbird.warcraft.object.unit.combatant.melee.orc.OgreFactory;

import javax.inject.Inject;

/**
 * Instances of this factory create Human {@link Combatant Combatants}, a
 * {@link Unit} specialization that can move and attack other Units.
 *
 * @author Blair Butterworth
 */
public class MeleeUnitFactorySet extends GameFactorySet<Combatant>
{
    @Inject
    public MeleeUnitFactorySet(
        AnduinLotharFactory anduinLotharFactory,
        DwarvenDemolitionSquadFactory dwarvenDemolitionSquadFactory,
        FootmanFactory footmanFactory,
        GoblinSappersFactory goblinSappersFactory,
        GruntFactory gruntFactory,
        KnightFactory knightFactory,
        OgreFactory ogreFactory,
        SkeletonFactory skeletonFactory)
    {
        addProvider(UnitType.AnduinLothar, anduinLotharFactory);
        addProvider(UnitType.DwarvenDemolitionSquad, dwarvenDemolitionSquadFactory);
        addProvider(UnitType.Footman, footmanFactory);
        addProvider(UnitType.GoblinSappers, goblinSappersFactory);
        addProvider(UnitType.Grunt, gruntFactory);
        addProvider(UnitType.Knight, knightFactory);
        addProvider(UnitType.Ogre, ogreFactory);
        addProvider(UnitType.Skeleton, skeletonFactory);
    }
}
