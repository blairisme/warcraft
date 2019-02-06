/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.event.Event;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemType;

public class ConstructEvent implements Event
{
    private Item builder;
    private ItemType type;
    private Vector2 location;

    public ConstructEvent(Item builder, ItemType type, Vector2 location) {
        this.builder = builder;
        this.type = type;
        this.location = location;
    }

    @Override
    public Item getSubject() {
        return builder;
    }

    public ItemType getType() {
        return type;
    }

    public Vector2 getLocation() {
        return location;
    }
}
