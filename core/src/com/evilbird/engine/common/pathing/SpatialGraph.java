/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
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
