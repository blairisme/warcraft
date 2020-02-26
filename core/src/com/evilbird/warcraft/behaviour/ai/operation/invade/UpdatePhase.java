/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.invade;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.evilbird.warcraft.object.data.player.Player;

import javax.inject.Inject;

/**
 * A {@link LeafTask} that increments the players current phase, an indicator
 * of the current invasion wave.
 *
 * @author Blair Butterworth
 */
public class UpdatePhase extends LeafTask<InvasionData>
{
    @Inject
    public UpdatePhase() {
    }

    @Override
    public Status execute() {
        InvasionData data = getObject();
        Player player = data.getPlayer();
        player.setPhase(player.getPhase() + 1);
        return Status.SUCCEEDED;
    }

    @Override
    protected Task<InvasionData> copyTo(Task<InvasionData> task) {
        return task;
    }
}
