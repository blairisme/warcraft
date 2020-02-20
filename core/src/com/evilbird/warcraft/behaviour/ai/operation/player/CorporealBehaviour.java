/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.player;

import com.badlogic.gdx.ai.btree.branch.Parallel;
import com.evilbird.warcraft.behaviour.ai.operation.attack.AttackBehaviour;
import com.evilbird.warcraft.behaviour.ai.operation.reorient.ReorientBehaviour;

import javax.inject.Inject;

/**
 * A {@link PlayerBehaviour} specialization specifying the background behaviour
 * of the corporeal player.
 *
 * @author Blair Butterworth
 */
public class CorporealBehaviour extends PlayerBehaviour
{
    @Inject
    public CorporealBehaviour(AttackBehaviour attack, ReorientBehaviour reorient) {
        super(new Parallel<>(attack, reorient));
    }
}
