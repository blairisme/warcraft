/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew.reorient;

import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.evilbird.warcraft.behaviour.ainew.PlayerData;
import com.evilbird.warcraft.behaviour.ainew.common.guard.RandomWait;

import javax.inject.Inject;

/**
 * A behaviour tree that randomly re-orients idle units. Unit reorientation is
 * preceded by a random delay.
 *
 * @author Blair Butterworth
 */
public class ReorientTree extends Sequence<PlayerData>
{
    private static final float DELAY_MIN = 1f;
    private static final float DELAY_MAX = 2f;

    @Inject
    public ReorientTree(ReorientTask reorientTask) {
        super(new RandomWait<>(DELAY_MIN, DELAY_MAX), reorientTask);
    }
}
