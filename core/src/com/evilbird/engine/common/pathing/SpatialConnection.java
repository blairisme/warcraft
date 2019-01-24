/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.pathing;

import com.badlogic.gdx.ai.pfa.Connection;

/**
 * Implementors of this interface represent a connection between
 * {@link SpatialNode SpatialNodes}.
 *
 * @author Blair Butterworth
 */
public interface SpatialConnection <T extends SpatialNode> extends Connection<T> {
}
