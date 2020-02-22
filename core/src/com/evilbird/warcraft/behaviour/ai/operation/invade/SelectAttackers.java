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
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.data.player.Player;

import javax.inject.Inject;
import java.util.Collection;

import static com.badlogic.gdx.ai.btree.Task.Status.FAILED;
import static com.badlogic.gdx.ai.btree.Task.Status.SUCCEEDED;
import static com.evilbird.engine.common.collection.CollectionUtils.filter;
import static com.evilbird.warcraft.behaviour.ai.operation.invade.InvasionQueries.IdleAttackers;
import static com.evilbird.warcraft.behaviour.ai.operation.invade.InvasionQueries.MovableAttackers;

/**
 * @author Blair Butterworth
 */
public class SelectAttackers extends LeafTask<InvasionData>
{
    @Inject
    public SelectAttackers() {
    }

    @Override
    public Status execute() {
        InvasionData data = getObject();
        Player player = data.getPlayer();

        InvasionOrder order = data.getOrder();
        InvasionWave wave = order.getNextWave(player);



        //if (wave.requirements)

        Collection<GameObject> movableAttackers = player.findAll(MovableAttackers);
        Collection<GameObject> attackers = filter(movableAttackers, IdleAttackers);

        data.setAttackers(attackers);
        return attackers.isEmpty() ? FAILED : SUCCEEDED;
    }

    @Override
    protected Task<InvasionData> copyTo(Task<InvasionData> task) {
        return task;
    }
}
