/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.identifier;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.warcraft.item.unit.resource.ResourceType;

/**
 * Defines options of specifying gather action varieties.
 *
 * @author Blair Butterworth
 */
public enum GatherActions implements ActionIdentifier
{
    GatherGold  (ResourceType.Gold, 100f, 5f),
    GatherOil   (ResourceType.Oil, 100f, 5f),
    GatherWood  (ResourceType.Wood, 100f, 40f);

    private float gatherAmount;
    private float gatherSpeed;
    private ResourceType resourceType;

    GatherActions(ResourceType resourceType, float gatherAmount, float gatherSpeed) {
        this.gatherAmount = gatherAmount;
        this.gatherSpeed = gatherSpeed;
        this.resourceType = resourceType;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public float getGatherAmount() {
        return gatherAmount;
    }

    public float getGatherSpeed() {
        return gatherSpeed;
    }
}