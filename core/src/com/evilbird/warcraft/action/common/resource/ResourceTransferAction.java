/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.resource;

import com.badlogic.gdx.math.MathUtils;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.common.ActionRecipient;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceQuantity;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.data.DataType;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import static com.evilbird.engine.action.common.ActionRecipient.Player;
import static com.evilbird.engine.item.utility.ItemOperations.findAncestorByType;

/**
 * Instances of this action apply a given delta to the resources contained in a
 * given resource container.
 *
 * @author Blair Butterworth
 */
public class ResourceTransferAction extends BasicAction
{
    private float factor;
    private ActionRecipient target;
    private ResourceTransferObserver observer;
    private Collection<ResourceQuantity> quantities;

    private ResourceTransferAction(
        ActionRecipient target,
        ResourceQuantity quantity,
        float factor,
        ResourceTransferObserver observer)
    {
        this(target, Collections.singleton(quantity), factor, observer);
    }

    private ResourceTransferAction(
        ActionRecipient target,
        Collection<ResourceQuantity> quantities,
        float factor,
        ResourceTransferObserver observer)
    {
        this.target = target;
        this.quantities = quantities;
        this.factor = factor;
        this.observer = observer;
    }

    public static Action purchase(
        ResourceQuantity quantity,
        ResourceTransferObserver observer)
    {
        return new ResourceTransferAction(Player, quantity, -1, observer);
    }

    public static Action purchase(
        Set<ResourceQuantity> quantities,
        ResourceTransferObserver observer)
    {
        return new ResourceTransferAction(Player, quantities, -1, observer);
    }

    public static Action deposit(ResourceQuantity quantity, ResourceTransferObserver observer) {
        return new ResourceTransferAction(Player, quantity, 1, observer);
    }

    public static Action deposit(Set<ResourceQuantity> quantities, ResourceTransferObserver observer) {
        return new ResourceTransferAction(Player, quantities, 1, observer);
    }

    public static Action refund(
        ResourceQuantity quantity,
        float proportion,
        ResourceTransferObserver observer)
    {
        return new ResourceTransferAction(Player, quantity, proportion, observer);
    }

    public static Action transfer(
        ActionRecipient from,
        ActionRecipient to,
        ResourceQuantity quantity,
        ResourceTransferObserver observer)
    {
        Action transferFrom = new ResourceTransferAction(from, quantity, -1, observer);
        Action transferTo = new ResourceTransferAction(to, quantity, 1, observer);
        return new ParallelAction(transferFrom, transferTo);
    }

    public static Action transfer(
        ActionRecipient from,
        ActionRecipient to,
        Set<ResourceQuantity> quantities,
        ResourceTransferObserver observer)
    {
        Action transferFrom = new ResourceTransferAction(from, quantities, -1, observer);
        Action transferTo = new ResourceTransferAction(to, quantities, 1, observer);
        return new ParallelAction(transferFrom, transferTo);
    }

    @Override
    public boolean act(float time) {
        for (ResourceQuantity quantity: quantities){
            setResources(quantity);
        }
        return true;
    }

    private void setResources(ResourceQuantity quantity) {
        ResourceType resource = quantity.getResource();
        ResourceContainer container = getContainer();

        float delta = quantity.getValue() * factor;
        float oldValue = container.getResource(resource);
        float newValue = MathUtils.clamp(oldValue + delta, 0f, Float.MAX_VALUE);

        container.setResource(resource, newValue);
        notifyObserver(container, resource, oldValue, newValue);
    }

    private ResourceContainer getContainer() {
        switch (target) {
            case Subject: return (ResourceContainer)getItem();
            case Target: return (ResourceContainer)getTarget();
            case Parent: return (ResourceContainer)getItem().getParent();
            case Player: return (ResourceContainer)findAncestorByType(getItem(), DataType.Player);
            default: throw new UnsupportedOperationException();
        }
    }

    private void notifyObserver(ResourceContainer container, ResourceType resource, float oldValue, float newValue) {
        if (observer != null) {
            observer.onTransfer(container, resource, oldValue, newValue);
        }
    }
}
