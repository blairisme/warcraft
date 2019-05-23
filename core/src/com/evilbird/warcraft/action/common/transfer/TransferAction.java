/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.transfer;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.common.ActionRecipient;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceQuantity;
import com.evilbird.warcraft.item.common.resource.ResourceType;

import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import static com.evilbird.engine.action.common.ActionRecipient.Player;
import static com.evilbird.engine.action.common.ActionUtils.getRecipient;
import static com.evilbird.engine.common.function.Suppliers.constant;
import static com.evilbird.engine.common.function.Suppliers.decrement;
import static com.evilbird.engine.common.function.Suppliers.increment;

/**
 * Instances of this action apply a given delta to the resources contained in a
 * given resource container.
 *
 * @author Blair Butterworth
 */
public class TransferAction extends BasicAction
{
    private ActionRecipient target;
    private TransferObserver observer;
    private BiFunction<Float, Float, Float> modifier;
    private Supplier<Collection<ResourceQuantity>> resources;

    private TransferAction(
        ActionRecipient target,
        Supplier<Collection<ResourceQuantity>> resources,
        BiFunction<Float, Float, Float> modifier,
        TransferObserver observer)
    {
        this.target = target;
        this.resources = resources;
        this.modifier = modifier;
        this.observer = observer;
    }

    public static Action purchase(Collection<ResourceQuantity> quantities, TransferObserver observer) {
        return new TransferAction(Player, constant(quantities), decrement(), observer);
    }

    public static Action deposit(Collection<ResourceQuantity> quantities, TransferObserver observer) {
        return new TransferAction(Player, constant(quantities), increment(), observer);
    }

    public static Action transfer(ActionRecipient from, ActionRecipient to, TransferObserver observer) {
        ParallelAction result = new ParallelAction();
        ResourceSupplier resourceSupplier = new ResourceSupplier(result, from);
        result.add(new TransferAction(from, resourceSupplier, decrement(), observer));
        result.add(new TransferAction(to, resourceSupplier, increment(), observer));
        return result;
    }

    public static Action transfer(
        ActionRecipient from,
        ActionRecipient to,
        ResourceQuantity quantity,
        TransferObserver observer)
    {
        Action transferFrom = new TransferAction(from, constant(quantity), decrement(), observer);
        Action transferTo = new TransferAction(to, constant(quantity), increment(), observer);
        return new ParallelAction(transferFrom, transferTo);
    }

    public static Action transfer(
        ActionRecipient from,
        ActionRecipient to,
        Collection<ResourceQuantity> quantities,
        TransferObserver observer)
    {
        Action transferFrom = new TransferAction(from, constant(quantities), decrement(), observer);
        Action transferTo = new TransferAction(to, constant(quantities), increment(), observer);
        return new ParallelAction(transferFrom, transferTo);
    }

    @Override
    public boolean act(float time) {
        for (ResourceQuantity quantity: resources.get()){
            setResources(quantity);
        }
        return true;
    }

    private void setResources(ResourceQuantity quantity) {
        ResourceType resource = quantity.getResource();
        ResourceContainer container = (ResourceContainer)getRecipient(this, target);

        float delta = quantity.getValue();
        float oldValue = container.getResource(resource);
        float newValue = modifier.apply(oldValue, delta);

        container.setResource(resource, newValue);
        notifyObserver(container, resource, oldValue, newValue);
    }

    private void notifyObserver(ResourceContainer container, ResourceType resource, float oldValue, float newValue) {
        if (observer != null) {
            observer.onTransfer(container, resource, oldValue, newValue);
        }
    }
}
