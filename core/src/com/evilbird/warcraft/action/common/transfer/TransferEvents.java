/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.common.transfer;

import com.evilbird.engine.events.Events;
import com.evilbird.warcraft.data.resource.ResourceContainer;
import com.evilbird.warcraft.data.resource.ResourceType;

import javax.inject.Inject;

/**
 * Notifies the system event observers about resource transfers.
 *
 * @author Blair Butterworth
 */
public class TransferEvents
{
    private Events events;

    @Inject
    public TransferEvents(Events events) {
        this.events = events;
    }

    public void notifyTransfer(ResourceContainer subject, ResourceType resource, float value) {
        events.add(new TransferEvent(subject, resource, value));
    }
}
