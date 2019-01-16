/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action;

import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;

public interface ActionContext
{
    Item getItem();

    Item getTarget();

    UserInput getInput();
}
