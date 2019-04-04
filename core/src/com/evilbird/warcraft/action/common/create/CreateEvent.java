/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.create;

import com.evilbird.engine.events.Event;
import com.evilbird.engine.item.Item;

public class CreateEvent implements Event
{
    @Override
    public Item getSubject() {
        return null;
    }
}
