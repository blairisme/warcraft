/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

/**
 * Implementors of this interface provide methods that are called when an
 * {@link AttackEvent} instructs an item to attack another.
 *
 * @author Blair Butterworth
 */
public interface AttackObserver
{
    void onAttackStarted(Combatant attacker, Item target);

    void onAttackCompleted(Combatant attacker, Item target);

    void onAttackCancelled(Combatant attacker, Item target);
}
