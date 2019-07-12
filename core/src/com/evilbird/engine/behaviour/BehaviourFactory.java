/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.behaviour;

import com.evilbird.engine.game.GameFactory;

/**
 * Implementors of the factory interface create {@link Behaviour} objects.
 *
 * @author Blair Butterworth
 */
public interface BehaviourFactory extends GameFactory<Behaviour>
{
}
