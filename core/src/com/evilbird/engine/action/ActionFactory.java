/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action;

import com.evilbird.engine.game.GameFactory;

/**
 * Instances of this class provide access to {@link Action Actions}, self
 * contained "bundles" of behaviour, that modify the game state is a meaningful
 * manner.
 *
 * @author Blair Butterworth
 */
public interface ActionFactory extends GameFactory<Action>
{
}
