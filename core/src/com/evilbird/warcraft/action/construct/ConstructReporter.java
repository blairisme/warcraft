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
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemType;
import com.evilbird.warcraft.action.common.resource.ResourceTransferEvent;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceIdentifier;

import javax.inject.Inject;

public class ConstructReporter implements ConstructObserver
{
    private EventQueue events;

    @Inject
    public ConstructReporter(EventQueue events) {
        this.events = events;
    }

    @Override
    public void onConstruct(Item builder, ItemType type, Vector2 location) {
        events.add(new ConstructEvent(builder, type, location));
    }

    @Override
    public void onTransfer(ResourceContainer recipient, ResourceIdentifier resource, float value) {
        events.add(new ResourceTransferEvent(recipient, resource, value));
    }
}
