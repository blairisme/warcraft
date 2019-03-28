/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.evilbird.engine.events.Event;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.spatial.ItemNode;

/**
 * Instances of this {@link Event} are produced when an {@link Item} moves from
 * one {@link ItemNode} to another.
 *
 * @author Blair Butterworth
 */
public class MoveEvent implements Event
{
    private Item subject;
    private ItemNode location;

    public MoveEvent(Item subject, ItemNode location) {
        this.subject = subject;
        this.location = location;
    }

    @Override
    public Item getSubject() {
        return subject;
    }

    public ItemNode getLocation() {
        return location;
    }
}
