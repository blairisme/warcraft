/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.combatant.human.HumanCombatantFactory;
import com.evilbird.warcraft.item.unit.combatant.orc.OrcCombatantFactory;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link Combatant Combatants}, a
 * {@link Unit} specialization that can move and attack other Units.
 *
 * @author Blair Butterworth
 */
public class CombatantFactory extends GameFactorySet<Combatant>
{
    @Inject
    public CombatantFactory(
        HumanCombatantFactory humanCombatantFactory,
        OrcCombatantFactory orcCombatantFactory)
    {
        addProvider(humanCombatantFactory);
        addProvider(orcCombatantFactory);
    }
}
