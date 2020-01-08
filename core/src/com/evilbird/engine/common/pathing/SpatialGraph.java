/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.pathing;

import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;

/**
 * Implementors of this interface represent a graph of the game space. The
 * graph consists of {@link SpatialNode SpatialNodes} each connected via
 * {@link SpatialConnection SpatialConnections}.
 *
 * @author Blair Butterworth
 */
public interface SpatialGraph<T extends SpatialNode> extends IndexedGraph<T> {
}
