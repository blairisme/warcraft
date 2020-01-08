/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.action.Action;
import com.evilbird.warcraft.action.move.MoveToItemAction;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;
import com.evilbird.warcraft.object.common.capability.PerishableObject;

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
    public MeleeAttack(MoveToItemAction move, ProximityAttack attack) {
        super(move, attack);
    }
}
