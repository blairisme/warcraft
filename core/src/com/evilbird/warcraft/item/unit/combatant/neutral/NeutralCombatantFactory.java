/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant.neutral;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;

/**
 * Instances of this factory create neutral {@link Combatant Combatants}, a
 * {@link Unit} specialization that can move and attack other Units.
 *
 * @author Blair Butterworth
 */
public class NeutralCombatantFactory extends GameFactorySet<Combatant>
{
    @Inject
    public NeutralCombatantFactory(
        DaemonFactory daemonFactory,
        SkeletonFactory skeletonFactory)
    {
        addProvider(UnitType.Daemon, daemonFactory);
        addProvider(UnitType.Skeleton, skeletonFactory);
    }
}
