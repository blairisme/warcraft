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
import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectComposite;
import com.evilbird.warcraft.object.data.player.Player;

import javax.inject.Inject;
import java.util.Collection;

import static com.badlogic.gdx.ai.btree.Task.Status.FAILED;
import static com.badlogic.gdx.ai.btree.Task.Status.SUCCEEDED;
import static com.evilbird.warcraft.behaviour.ai.operation.invade.InvasionQueries.EnemyPlayers;

public class SelectEnemy extends LeafTask<InvasionData>
{
    @Inject
    public SelectEnemy() {
    }

    @Override
    public Status execute() {
        InvasionData data = getObject();
        Player enemy = getEnemy(data);
        data.setEnemy(enemy);
        return enemy == null ? FAILED : SUCCEEDED;
    }

    private Player getEnemy(InvasionData data) {
        Player player = data.getPlayer();
        GameObjectComposite world = data.getWorld();
        Collection<GameObject> enemies = world.findAll(EnemyPlayers(player));
        return (Player)CollectionUtils.first(enemies);
    }

    @Override
    protected Task<InvasionData> copyTo(Task<InvasionData> task) {
        return task;
    }
}
