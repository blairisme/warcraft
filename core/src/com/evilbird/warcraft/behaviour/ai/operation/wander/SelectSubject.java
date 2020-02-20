/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.wander;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.capability.MovableObject;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import org.apache.commons.lang3.RandomUtils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * A {@link LeafTask} that selects a random target that will be moved to a
 * random destination by subsequent tasks.
 *
 * @author Blair Butterworth
 */
public class SelectSubject extends LeafTask<WanderData>
{
    private List<GameObject> subjects;

    @Inject
    public SelectSubject() {
    }

    @Override
    public void start() {
        if (subjects == null) {
            WanderData data = getObject();
            Player player = data.getPlayer();
            subjects = new ArrayList<>(player.findAll(UnitOperations::isMovable));
        }
    }

    @Override
    public Status execute() {
        if (!subjects.isEmpty()) {
            int index = RandomUtils.nextInt(0, subjects.size());
            GameObject critter = subjects.get(index);

            WanderData data = getObject();
            data.setSubject((MovableObject)critter);

            return Status.SUCCEEDED;
        }
        return Status.FAILED;
    }

    @Override
    protected Task<WanderData> copyTo(Task<WanderData> task) {
        return task;
    }
}
