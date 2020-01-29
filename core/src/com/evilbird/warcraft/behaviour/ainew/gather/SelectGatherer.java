/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew.gather;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.evilbird.engine.common.function.Predicates;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.utility.GameObjectOperations;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;

import javax.inject.Inject;
import java.util.function.Predicate;

/**
 * @author Blair Butterworth
 */
public class SelectGatherer extends LeafTask<GatherData>
{
    private static final transient Predicate<GameObject> ELIGIBLE_GATHERER = Predicates.all(
        UnitOperations::isGatherer,
        UnitOperations::isAlive,
        GameObjectOperations::isIdle
    );

    @Inject
    public SelectGatherer() {
    }

    @Override
    public Status execute() {
        GatherData data = getObject();
        Player player = data.getPlayer();

        Gatherer gatherer = (Gatherer)player.find(ELIGIBLE_GATHERER);
        data.setGatherer(gatherer);

        return gatherer != null ? Status.SUCCEEDED : Status.FAILED;
    }

    @Override
    protected Task<GatherData> copyTo(Task<GatherData> task) {
        throw new UnsupportedOperationException();
    }
}
