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
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.UnitType;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import static com.badlogic.gdx.ai.btree.Task.Status.FAILED;
import static com.badlogic.gdx.ai.btree.Task.Status.SUCCEEDED;
import static com.evilbird.warcraft.behaviour.ai.operation.invade.InvasionQueries.IdleUnits;
import static com.evilbird.warcraft.behaviour.ai.operation.invade.InvasionQueries.PotentialAttackers;

/**
 * A {@link LeafTask} implementation that selects an enemy player that will be
 * invaded by the next invasion wave.
 *
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

        if (wave != null) {
            Collection<GameObject> movableAttackers = player.findAll(PotentialAttackers);
            Collection<GameObject> idleAttackers = CollectionUtils.filter(movableAttackers, IdleUnits);

            Map<UnitType, Integer> requirements = wave.getParticipantTypes();
            Collection<GameObject> attackers = filter(idleAttackers, requirements);

            if (meetsRequirements(attackers, requirements)) {
                data.setAttackers(attackers);
                return SUCCEEDED;
            }
        }

        return FAILED;
    }

    protected Collection<GameObject> filter(Collection<GameObject> objects, Map<UnitType, Integer> requirements) {
        Map<UnitType, Collection<GameObject>> categorized = new HashMap<>(requirements.size());

        for (GameObject object: objects) {
            UnitType type = (UnitType)object.getType();

            if (requirements.containsKey(type)) {
                Collection<GameObject> category = Maps.getOrDefaultSupplied(categorized, type, ArrayList::new);

                if (category.size() < requirements.get(type)) {
                    category.add(object);
                }
                categorized.put(type, category);
            }
        }
        return CollectionUtils.flatten(categorized.values());
    }

    protected boolean meetsRequirements(Collection<GameObject> categorized, Map<UnitType, Integer> requirements) {
        int total = 0;
        for (Entry<UnitType, Integer> category: requirements.entrySet()) {
            total += category.getValue();
        }
        return categorized.size() == total;
    }

    @Override
    protected Task<InvasionData> copyTo(Task<InvasionData> task) {
        return task;
    }
}
