/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.transfer;

import com.evilbird.engine.events.Events;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceType;

/**
 * Helper class for generating transfer events.
 *
 * @author Blair Butterworth
 */
public class TransferEvents
{
    /**
     * Disable construction of static helper class.
     */
    private TransferEvents() {
    }

    public static void notifyTransfer(
        Events events,
        ResourceContainer subject,
        ResourceType resource,
        float oldValue,
        float newValue)
    {
        events.add(new TransferEvent(subject, resource, oldValue, newValue));
    }
}
