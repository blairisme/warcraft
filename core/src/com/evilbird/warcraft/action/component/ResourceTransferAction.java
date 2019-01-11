package com.evilbird.warcraft.action.component;

import com.badlogic.gdx.math.MathUtils;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.warcraft.item.common.capability.ResourceContainer;
import com.evilbird.warcraft.item.common.capability.ResourceIdentifier;

import java.util.Map;

/**
 * Instances of this action apply a given delta to the resources contained in a
 * given resource container.
 *
 * @author Blair Butterworth
 */
public class ResourceTransferAction extends BasicAction
{
    private ResourceContainer container;
    private Map<ResourceIdentifier, Float> deltas;

    public ResourceTransferAction(ResourceContainer container, ResourceIdentifier type, float delta) {
        this(container, Maps.of(type, delta));
    }

    public ResourceTransferAction(ResourceContainer container, Map<ResourceIdentifier, Float> deltas) {
        this.container = container;
        this.deltas = deltas;
    }

    @Override
    public boolean act(float time) {
        for (Map.Entry<ResourceIdentifier, Float> delta: deltas.entrySet()) {
            float oldValue = container.getResource(delta.getKey());
            float newValue = MathUtils.clamp(oldValue + delta.getValue(), 0f, Float.MAX_VALUE);
            container.setResource(delta.getKey(), newValue);
        }
        return true;
    }
}
