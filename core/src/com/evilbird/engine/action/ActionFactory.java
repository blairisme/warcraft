/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action;

import com.evilbird.engine.common.error.UnknownEntityException;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.persistence.Persisted;

/**
 * Instances of this class provide access to {@link Action Actions}, self
 * contained "bundles" of behaviour, that modify the game state is a meaningful
 * manner.
 *
 * @author Blair Butterworth
 */
public interface ActionFactory extends Persisted
{
    /**
     * Returns an {@link Action} with the given {@link Identifier}.
     *
     * @param identifier    the identifier of the desired action.
     * @return              the desired action.
     * @throws UnknownEntityException   thrown if an action with the given
     *                                  identifier doesn't exist.
     */
    Action newAction(ActionIdentifier identifier) throws UnknownEntityException;
}
