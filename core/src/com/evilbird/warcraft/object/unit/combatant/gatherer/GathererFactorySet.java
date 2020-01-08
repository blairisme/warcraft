/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.gatherer;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import com.evilbird.warcraft.object.unit.combatant.gatherer.human.OilTankerFactory;
import com.evilbird.warcraft.object.unit.combatant.gatherer.human.PeasantFactory;
import com.evilbird.warcraft.object.unit.combatant.gatherer.orc.PeonFactory;
import com.evilbird.warcraft.object.unit.combatant.gatherer.orc.TrollTankerFactory;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link Gatherer Gatherers}, a
 * {@link Combatant} specialization that can both fight and collect resources.
 *
 * @author Blair Butterworth
 */
public class GathererFactorySet extends GameFactorySet<Gatherer>
{
    @Inject
    public GathererFactorySet(
        OilTankerFactory oilTankerFactory,
        PeasantFactory peasantFactory,
        PeonFactory peonFactory,
        TrollTankerFactory trollTankerFactory)
    {
        addProvider(UnitType.OilTanker, oilTankerFactory);
        addProvider(UnitType.Peasant, peasantFactory);
        addProvider(UnitType.Peon, peonFactory);
        addProvider(UnitType.TrollTanker, trollTankerFactory);
    }
}
