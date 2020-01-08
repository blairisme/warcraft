/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.object.spatial;

import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectGroup;
import com.evilbird.engine.object.GameObjectGroupObserver;

/**
 * Instances of this class observe {@link GameObjectGroup} events and use these to
 * apply the occupancy of an {@link GameObjectGraph}.
 *
 * @author Blair Butterworth
 */
public class GameObjectGraphUpdater implements GameObjectGroupObserver
{
    private GameObjectGraph graph;

    public GameObjectGraphUpdater() {
    }

    public void setGraph(GameObjectGraph graph) {
        this.graph = graph;
    }

    @Override
    public void objectAdded(GameObject gameObject) {
        if (gameObject instanceof GameObjectGroup) {
            GameObjectGroup group = (GameObjectGroup) gameObject;
            group.addObserver(this);
            addOccupants(group);
        }
        else {
            addOccupants(gameObject);
        }
    }

    private void addOccupants(GameObject gameObject) {
        if (graph != null) {
            graph.addOccupants(gameObject);
        }
    }

    private void addOccupants(GameObjectGroup group) {
        if (graph != null) {
            graph.addOccupants(group);
            graph.addOccupants(group.getObjects());
        }
    }

    @Override
    public void objectRemoved(GameObject gameObject) {
        if (gameObject instanceof GameObjectGroup) {
            GameObjectGroup group = (GameObjectGroup) gameObject;
            group.removeObserver(this);
            removeOccupants(group);
        }
        else {
            removeOccupants(gameObject);
        }
    }

    private void removeOccupants(GameObject gameObject) {
        if (graph != null) {
            graph.removeOccupants(gameObject);
        }
    }

    private void removeOccupants(GameObjectGroup group) {
        if (graph != null) {
            graph.removeOccupants(group);
            graph.removeOccupants(group.getObjects());
        }
    }

    @Override
    public void objectsCleared() {
        if (graph != null) {
            graph.clearOccupants();
        }
    }
}
