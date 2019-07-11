/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

import com.evilbird.engine.game.GameFactory;

/**
 * Implementors of factory interface provide methods that create {@link Item}
 * instances.
 *
 * @author Blair Butterworth
 */
public interface ItemFactory extends GameFactory<Item>
{
}
