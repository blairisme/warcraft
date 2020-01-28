/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew.construct;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.evilbird.engine.common.function.Predicates;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.utility.GameObjectOperations;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.Unit;

import javax.inject.Inject;
import java.util.function.Predicate;

/**
 * @author Blair Butterworth
 */
public class SelectBuilder extends LeafTask<ConstructData>
{
    private static final transient Predicate<GameObject> ELIGIBLE_BUILDER = Predicates.all(
        UnitOperations::isGatherer,
        UnitOperations::isAlive,
        GameObjectOperations::isIdle
    );

    @Inject
    public SelectBuilder() {
    }

    @Override
    public Status execute() {
        ConstructData data = getObject();
        Player player = data.getPlayer();

        Unit builder = (Unit)player.find(ELIGIBLE_BUILDER);
        data.setBuilder(builder);

        return builder != null ? Status.SUCCEEDED : Status.FAILED;
    }

    @Override
    protected Task<ConstructData> copyTo(Task<ConstructData> task) {
        throw new UnsupportedOperationException();
    }
}
