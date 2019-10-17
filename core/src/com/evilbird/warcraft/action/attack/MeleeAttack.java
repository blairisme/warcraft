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
import com.evilbird.warcraft.action.death.DeathAction;
import com.evilbird.warcraft.action.move.MoveToItemAction;
import com.evilbird.warcraft.item.common.state.OffensiveObject;
import com.evilbird.warcraft.item.common.state.PerishableObject;

import javax.inject.Inject;

/**
 * An {@link Action} that causes a given {@link OffensiveObject} to attack a
 * {@link PerishableObject} after first moving adjacent to it.
 *
 * @author Blair Butterworth
 */
public class MeleeAttack extends AttackSequence
{
    @Inject
    public MeleeAttack(AttackEvents events, MoveToItemAction move, ProximityAttack attack, DeathAction death) {
        super(events, move, attack, death);
    }
}
