/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew.reorient;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.utility.GameObjectOperations;
import com.evilbird.warcraft.behaviour.ainew.PlayerData;
import com.evilbird.warcraft.object.common.capability.MovableObject;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import org.apache.commons.lang3.RandomUtils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * @author Blair Butterworth
 */
public class SelectSubject extends LeafTask<PlayerData>
{
    private static final int REORIENT_MIN = 2;
    private static final int REORIENT_MAX = 4;

    @Inject
    public SelectSubject() {
    }

    @Override
    public Status execute() {
        PlayerData data = getObject();
        Player player = data.getPlayer();
        List<GameObject> movables = new ArrayList<>(player.findAll(UnitOperations::isMovable));

        for (int i = 0; i < RandomUtils.nextInt(REORIENT_MIN, REORIENT_MAX); i++) {
            MovableObject target = (MovableObject)movables.get(random.nextInt(movables.size()));
            if (UnitOperations.isAlive(target) && GameObjectOperations.isIdle(target)) {
                target.setDirection(getRandomDirection());
            }
        }
        return Status.SUCCEEDED;
    }

    private Vector2 getRandomDirection() {
        Vector2 result = new Vector2(1, 1);
        result.rotate(random.nextInt(360));
        return result;
    }

    @Override
    protected Task<PlayerData> copyTo(Task<PlayerData> task) {
        return null;
    }
}
