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

import java.util.Collection;

/**
 * Implementors of this interface represent a game object that occupies space
 * in the game world, and thus marks it as belonging to an {@link GameObjectGraph}.
 *
 * @author Blair Butterworth
 */
public interface SpatialObject extends GameObject
{
    default Collection<GameObjectNode> getNodes(GameObjectGraph graph) {
        return graph.getNodes(getPosition(), getSize());
    }
}
