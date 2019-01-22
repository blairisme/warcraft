/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.behaviour;

import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.ItemRoot;

import java.util.List;

/**
 * Implementors of this interface define game logic that modifies the state of
 * the game in response to user input, time and other factors.
 *
 * @author Blair Butterworth
 */
public interface Behaviour
{
    void update(ItemRoot world, ItemRoot hud, List<UserInput> input);
}
