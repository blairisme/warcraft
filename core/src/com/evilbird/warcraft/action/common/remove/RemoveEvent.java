/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.remove;

import com.evilbird.engine.events.Event;
import com.evilbird.engine.item.Item;

public class RemoveEvent implements Event
{
    @Override
    public Item getSubject() {
        return null;
    }
}
