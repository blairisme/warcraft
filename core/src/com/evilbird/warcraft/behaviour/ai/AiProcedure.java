/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ai;

import com.evilbird.engine.item.ItemRoot;

/**
 * Implementors of this interface provide a method that updates the game state
 * on behalf of non-human players.
 *
 * @author Blair Butterworth
 */
public interface AiProcedure
{
    void update(ItemRoot gameState);
}
