/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.action.events.Event;
import com.evilbird.engine.item.Item;

/**
 * Instances of this {@link Event} are generated when one {@link Item} is
 * instructed to attack another.
 *
 * @author Blair Butterworth
 */
public class AttackEvent implements Event
{
    private Item subject;
    private Item target;

    public AttackEvent(Item subject, Item target) {
        this.subject = subject;
        this.target = target;
    }

    @Override
    public Item getSubject() {
        return subject;
    }

    public Item getTarget() {
        return target;
    }
}
