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
import com.evilbird.engine.action.common.ActionTarget;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceIdentifier;
import com.evilbird.warcraft.item.data.DataType;

import java.util.Collections;
import java.util.Map;

import static com.evilbird.engine.item.ItemOperations.findAncestorByType;

/**
 * Instances of this action apply a given delta to the resources contained in a
 * given resource container.
 *
 * @author Blair Butterworth
 */
//TODO: doesnt need to use a map - can be just id and float
public class ResourceTransferAction extends BasicAction
{
    private ActionTarget target;
    private ResourceTransferObserver observer;
    private Map<ResourceIdentifier, Float> deltas;

    public ResourceTransferAction(ActionTarget target) {
        this(target, null);
    }

    public ResourceTransferAction(ActionTarget target, ResourceTransferObserver observer) {
        this(target, Collections.emptyMap(), observer);
    }

    public ResourceTransferAction(ActionTarget target, ResourceIdentifier type, float delta, ResourceTransferObserver observer) {
        this(target, Maps.of(type, delta), observer);
    }

    private ResourceTransferAction(ActionTarget target, Map<ResourceIdentifier, Float> deltas, ResourceTransferObserver observer) {
        this.target = target;
        this.deltas = deltas;
        this.observer = observer;
    }

    public Map<ResourceIdentifier, Float> getResourceDeltas() {
        return deltas;
    }

    public void setResourceDeltas(Map<ResourceIdentifier, Float> deltas) {
        this.deltas = deltas;
    }

    public void setObserver(ResourceTransferObserver observer) {
        this.observer = observer;
    }

    @Override
    public boolean act(float time) {
        ResourceContainer container = getContainer();
        for (Map.Entry<ResourceIdentifier, Float> delta: deltas.entrySet()) {
            ResourceIdentifier resource = delta.getKey();
            float diff = delta.getValue();

            float oldValue = container.getResource(resource);
            float newValue = MathUtils.clamp(oldValue + diff, 0f, Float.MAX_VALUE);

            container.setResource(resource, newValue);
            notifyObserver(container, resource, newValue);
        }
        return true;
    }

    private ResourceContainer getContainer() {
        switch (target) {
            case Item: return (ResourceContainer)getItem();
            case Target: return (ResourceContainer)getTarget();
            case Parent: return (ResourceContainer)getItem().getParent();
            case Player: return (ResourceContainer)findAncestorByType(getItem(), DataType.Player);
            default: throw new UnsupportedOperationException();
        }
    }

    private void notifyObserver(ResourceContainer container, ResourceIdentifier resource, float value) {
        if (observer != null) {
            observer.onTransfer(container, resource, value);
        }
    }
}
