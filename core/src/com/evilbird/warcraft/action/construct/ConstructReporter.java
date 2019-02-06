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
import com.evilbird.engine.event.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemType;

import javax.inject.Inject;

public class ConstructReporter implements ConstructObserver
{
    private Events events;

    @Inject
    public ConstructReporter(Events events) {
        this.events = events;
    }

    @Override
    public void onConstruct(Item builder, ItemType type, Vector2 location) {
        events.add(new ConstructEvent(builder, type, location));
    }
}
