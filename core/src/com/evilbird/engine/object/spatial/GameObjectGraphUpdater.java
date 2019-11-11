/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
