/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.invade.sea;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.common.collection.Lists;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.data.player.Player;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

import static com.badlogic.gdx.ai.btree.Task.Status.FAILED;
import static com.badlogic.gdx.ai.btree.Task.Status.SUCCEEDED;
import static com.evilbird.engine.object.utility.GameObjectComparators.closestItem;
import static com.evilbird.warcraft.behaviour.ai.operation.invade.InvasionQueries.IdleUnits;
import static com.evilbird.warcraft.behaviour.ai.operation.invade.InvasionQueries.PotentialTransports;

/**
 * A {@link LeafTask} implementation that selects a transport that will be used
 * to transports attackers in the next invasion wave.
 *
 * @author Blair Butterworth
 */
public class SelectTransport extends LeafTask<SeaInvasionData>
{
    @Inject
    public SelectTransport() {
    }

    @Override
    public Status execute() {
        SeaInvasionData data = getObject();
        GameObject transport = getTransport(data);
        data.setTransport(transport);
        return transport != null ? SUCCEEDED : FAILED;
    }

    protected GameObject getTransport(SeaInvasionData data) {
        Player player = data.getPlayer();
        Collection<GameObject> potentials = player.findAll(PotentialTransports);
        Collection<GameObject> transports = CollectionUtils.filter(potentials, IdleUnits);

        Collection<GameObject> attackers = data.getAttackers();
        GameObject attacker = CollectionUtils.first(attackers);

        List<GameObject> closestTransports = Lists.toList(transports);
        Lists.sort(closestTransports, closestItem(attacker));

        return CollectionUtils.first(closestTransports);
    }

    @Override
    protected Task<SeaInvasionData> copyTo(Task<SeaInvasionData> task) {
        return task;
    }
}
