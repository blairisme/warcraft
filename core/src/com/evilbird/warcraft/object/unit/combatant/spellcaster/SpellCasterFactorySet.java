/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.spellcaster;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import com.evilbird.warcraft.object.unit.combatant.spellcaster.human.MageFactory;
import com.evilbird.warcraft.object.unit.combatant.spellcaster.human.PaladinFactory;
import com.evilbird.warcraft.object.unit.combatant.spellcaster.orc.ChogallFactory;
import com.evilbird.warcraft.object.unit.combatant.spellcaster.orc.DeathKnightFactory;
import com.evilbird.warcraft.object.unit.combatant.spellcaster.orc.OgreMageFactory;

import javax.inject.Inject;

/**
 * Instances of this factory create Human {@link Combatant Combatants}, a
 * {@link Unit} specialization that can move and attack other Units.
 *
 * @author Blair Butterworth
 */
public class SpellCasterFactorySet extends GameFactorySet<Combatant>
{
    @Inject
    public SpellCasterFactorySet(
        ChogallFactory chogallFactory,
        DeathKnightFactory deathKnightFactory,
        MageFactory mageFactory,
        OgreMageFactory ogreMageFactory,
        PaladinFactory paladinFactory)
    {
        addProvider(UnitType.Chogall, chogallFactory);
        addProvider(UnitType.DeathKnight, deathKnightFactory);
        addProvider(UnitType.Mage, mageFactory);
        addProvider(UnitType.OgreMage, ogreMageFactory);
        addProvider(UnitType.Paladin, paladinFactory);
    }
}