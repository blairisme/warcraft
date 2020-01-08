/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
