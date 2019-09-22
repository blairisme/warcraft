/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.transfer;

import com.evilbird.engine.action.common.ActionRecipient;
import com.evilbird.engine.action.common.ActionUtils;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceQuantity;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Supplier;

/**
 * Instances of this action apply a given delta to the resources contained in a
 * given resource container.
 *
 * @author Blair Butterworth
 */
public class TransferAction extends BasicAction
{

    private ActionRecipient from;
    private ActionRecipient to;
    private ResourceTransfer transferer;
    private Supplier<Collection<ResourceQuantity>> resources;

    @Inject
    public TransferAction(ResourceTransfer transferer) {
        this.transferer = transferer;
        this.resources = Collections::emptyList;
    }

    public void setAmount(Supplier<Collection<ResourceQuantity>> supplier) {
        resources = supplier;
    }

    public void setParties(ActionRecipient from, ActionRecipient to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean act(float time) {
        for (ResourceQuantity quantity: resources.get()){
            setResources(quantity);
        }
        return true;
    }

    private void setResources(ResourceQuantity quantity) {
        if (from != null) {
            ResourceContainer supplier = (ResourceContainer)ActionUtils.getRecipient(this, from);
            transferer.setResources(supplier, quantity.negate());
        }
        if (to != null) {
            ResourceContainer recipient = (ResourceContainer)ActionUtils.getRecipient(this, to);
            transferer.setResources(recipient, quantity);
        }
    }
}
