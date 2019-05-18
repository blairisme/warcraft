/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.resource;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.common.ActionRecipient;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceQuantity;
import com.evilbird.warcraft.item.common.resource.ResourceQuantum;
import com.evilbird.warcraft.item.common.resource.ResourceType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;

import static com.evilbird.engine.action.common.ActionUtils.getRecipient;

/**
 * Instances of this class are used to obtain a list of all resources held in a
 * given {@link ResourceContainer}.
 *
 * @author Blair Butterworth
 */
public class ResourceSupplier implements Supplier<Collection<ResourceQuantity>>
{
    private Action action;
    private ActionRecipient recipient;
    private Collection<ResourceQuantity> resources;

    public ResourceSupplier(Action action, ActionRecipient recipient) {
        this.action = action;
        this.recipient = recipient;
    }

    @Override
    public Collection<ResourceQuantity> get() {
        if (resources == null) {
            resources = new ArrayList<>();
            ResourceContainer container = (ResourceContainer)getRecipient(action, recipient);
            for (ResourceType type : ResourceType.values()) {
                resources.add(new ResourceQuantum(type, container.getResource(type)));
            }
        }
        return resources;
    }
}
