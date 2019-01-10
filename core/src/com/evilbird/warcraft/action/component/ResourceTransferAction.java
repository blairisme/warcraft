package com.evilbird.warcraft.action.component;

import com.badlogic.gdx.math.MathUtils;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.warcraft.item.common.capability.ResourceContainer;
import com.evilbird.warcraft.item.common.capability.ResourceIdentifier;

/**
 * Instances of this action apply a given delta to the resources contained in a
 * given resource container.
 *
 * @author Blair Butterworth
 */
public class ResourceTransferAction extends BasicAction
{
    private float delta;
    private ResourceContainer container;
    private ResourceIdentifier type;

    public ResourceTransferAction(ResourceContainer container, ResourceIdentifier type, float delta) {
        this.container = container;
        this.type = type;
        this.delta = delta;
    }

    @Override
    public boolean act(float time) {
        float oldValue = container.getResource(type);
        float newValue = MathUtils.clamp(oldValue + delta, 0f, Float.MAX_VALUE);
        container.setResource(type, newValue);
        return true;
    }
}
