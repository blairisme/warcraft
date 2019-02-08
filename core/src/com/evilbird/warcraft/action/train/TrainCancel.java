/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.train;

import com.evilbird.engine.action.common.ActionTarget;
import com.evilbird.engine.action.framework.DelegateAction;
import com.evilbird.warcraft.action.common.resource.ResourceTransferAction;
import com.evilbird.warcraft.item.common.capability.ResourceIdentifier;
import com.evilbird.warcraft.item.common.resource.ResourceUtils;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;
import java.util.Map;

/**
 * Instances of this class stop the training of a unit.
 *
 * @author Blair Butterworth
 */
public class TrainCancel extends DelegateAction
{
    private ResourceTransferAction transfer;

    @Inject
    public TrainCancel() {
        transfer = new ResourceTransferAction(ActionTarget.Player);
        delegate = transfer;
    }

    public void setTrainCost(Map<ResourceIdentifier, Float> resources) {
        transfer.setResourceDeltas(resources);
    }

    @Override
    public boolean act(float delta) {
        Building building = (Building)getItem();
        Map<ResourceIdentifier, Float> fullCosts = transfer.getResourceDeltas();
        Map<ResourceIdentifier, Float> scaledCosts = ResourceUtils.scale(fullCosts, 1 - building.getProgress());
        transfer.setResourceDeltas(scaledCosts);
        return delegate.act(delta);
    }
}