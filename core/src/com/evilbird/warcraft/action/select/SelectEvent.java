/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.select;

import com.evilbird.engine.event.Event;
import com.evilbird.engine.item.Item;

public class SelectEvent implements Event
{
    private Item subject;
    private boolean selected;

    public SelectEvent(Item subject, boolean selected) {
        this.subject = subject;
        this.selected = selected;
    }

    @Override
    public Item getSubject() {
        return subject;
    }

    public boolean getSelected() {
        return selected;
    }
}
