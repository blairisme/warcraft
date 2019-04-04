/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.spatial.ItemNode;
import com.evilbird.warcraft.action.common.create.CreateObserver;
import com.evilbird.warcraft.action.common.resource.ResourceTransferEvent;
import com.evilbird.warcraft.action.common.resource.ResourceTransferObserver;
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.action.move.MoveObserver;
import com.evilbird.warcraft.action.select.SelectEvent;
import com.evilbird.warcraft.action.select.SelectObserver;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceIdentifier;

import javax.inject.Inject;

public class ConstructReporter implements ResourceTransferObserver, MoveObserver, SelectObserver, CreateObserver
{
    private EventQueue events;

    @Inject
    public ConstructReporter(EventQueue events) {
        this.events = events;
    }

    @Override
    public void onTransfer(ResourceContainer recipient, ResourceIdentifier resource, float value) {
        events.add(new ResourceTransferEvent(recipient, resource, value));
    }

    @Override
    public void onMove(Item subject, ItemNode location) {
        events.add(new MoveEvent(subject, location));
    }

    @Override
    public void onSelect(Item item, boolean selected) {
        events.add(new SelectEvent(item, selected));
    }
}
