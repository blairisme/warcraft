/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.action.Action;
import com.evilbird.warcraft.action.move.MoveToItemAction;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;
import com.evilbird.warcraft.object.common.capability.PerishableObject;

import javax.inject.Inject;

/**
 * An {@link Action} that causes a given {@link OffensiveObject demolition
 * combatant} to attack a {@link PerishableObject} after first moving adjacent
 * to it and detonating itself, dealing all damage instantly.
 *
 * @author Blair Butterworth
 */
public class DemolitionAttack extends AttackSequence
{
    @Inject
    public DemolitionAttack(MoveToItemAction move, ExplosiveAttack attack) {
        super(move, attack);
    }
}
