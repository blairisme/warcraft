/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew.wander;

import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.evilbird.warcraft.behaviour.ainew.common.framework.RandomExecutor;
import com.evilbird.warcraft.behaviour.ainew.common.framework.RandomWait;

import javax.inject.Inject;

/**
 * A {@link Sequence} implementation representing the steps required by the
 * wander behaviour. Namely to select a movable object belonging the player
 * specified in the {@link WanderSequence#getObject() blackboard}, moving it
 * to a random location. The wander sequence includes a guard causing it to
 * randomly fail and another delaying its execution by a random amount.
 *
 * @author Blair Butterworth
 */
public class WanderSequence extends Sequence<WanderData>
{
    private static final float DELAY_MIN = 1f;
    private static final float DELAY_MAX = 3f;

    @Inject
    public WanderSequence(
        SelectSubject selectSubject,
        SelectDestination selectDestination,
        WanderTask wanderAction)
    {
        super(new RandomExecutor<>(),
              new RandomWait<>(DELAY_MIN, DELAY_MAX),
              selectSubject,
              selectDestination,
              wanderAction);
    }
}
