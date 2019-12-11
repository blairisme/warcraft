/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
