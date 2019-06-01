/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.behaviour;

import com.evilbird.engine.common.error.UnknownEntityException;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Persisted;

/**
 * Implementors of the factory interface create {@link Behaviour} objects.
 *
 * @author Blair Butterworth
 */
public interface BehaviourFactory extends Persisted
{
    /**
     * Returns an {@link Behaviour} with the given {@link Identifier}.
     *
     * @param identifier    the identifier of the desired behaviour.
     * @return              the desired behaviour.
     * @throws UnknownEntityException   thrown if a behaviour with the given
     *                                  identifier doesn't exist.
     */
    Behaviour newBehaviour(BehaviourIdentifier identifier) throws UnknownEntityException;
}
