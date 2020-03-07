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
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectComposite;
import com.evilbird.warcraft.object.layer.terrain.Terrain;

import javax.inject.Inject;

import static com.badlogic.gdx.ai.btree.Task.Status.FAILED;
import static com.badlogic.gdx.ai.btree.Task.Status.SUCCEEDED;
import static com.evilbird.warcraft.behaviour.ai.operation.invade.InvasionQueries.ShoreLine;

/**
 * A {@link LeafTask} implementation that selects shore locations where
 * invading units will enter and exit the transport.
 *
 * @author Blair Butterworth
 */
public class SelectLandingLocations extends LeafTask<SeaInvasionData>
{
    @Inject
    public SelectLandingLocations() {
    }

    @Override
    public Status execute() {
        SeaInvasionData data = getObject();
        GameObjectComposite world = data.getWorld();
        Terrain shoreLine = (Terrain)world.find(ShoreLine);

        GameObject transport = data.getTransport();
        GameObject embarkationPoint = findClosest(shoreLine, transport);
        data.setEmbarkationPoint(embarkationPoint);

        GameObject target = data.getTarget();
        GameObject disembarkationPoint = findClosest(shoreLine, target);
        data.setDisembarkationPoint(disembarkationPoint);

        return embarkationPoint == null || disembarkationPoint == null ? FAILED : SUCCEEDED;
    }

    protected GameObject findClosest(Terrain terrain, GameObject target) {
        Vector2 targetPosition = target.getPosition();
        return terrain.getNearestHit(targetPosition, false);
    }

    @Override
    protected Task<SeaInvasionData> copyTo(Task<SeaInvasionData> task) {
        return task;
    }
}
