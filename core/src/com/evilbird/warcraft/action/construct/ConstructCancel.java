/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.common.ActionTarget;
import com.evilbird.engine.action.common.ReplacementAction;
import com.evilbird.engine.action.common.VisibleAction;
import com.evilbird.engine.action.framework.DelegateAction;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.attack.DeathAction;
import com.evilbird.warcraft.action.common.resource.ResourceTransferAction;
import com.evilbird.warcraft.item.common.resource.ResourceIdentifier;
import com.evilbird.warcraft.item.common.resource.ResourceUtils;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;
import java.util.Map;

/**
 * Instances of this class stop the construction of a building.
 *
 * @author Blair Butterworth
 */
public class ConstructCancel extends DelegateAction
{
    private VisibleAction restoreBuilder;
    private ResourceTransferAction restoreResources;

    @Inject
    public ConstructCancel() {
        restoreResources = new ResourceTransferAction(ActionTarget.Player);
        restoreBuilder = new VisibleAction(true);
        Action removeBuilding = new DeathAction();
        Action sequence = new ParallelAction(restoreResources, restoreBuilder, removeBuilding);
        delegate = new ReplacementAction(sequence);
    }

    public void setBuildCost(Map<ResourceIdentifier, Float> resources) {
        this.restoreResources.setResourceDeltas(resources);
    }

    @Override
    public void setItem(Item item) {
        super.setItem(item);
        Building building = (Building)item;
        restoreBuilder.setItem(building.getBuilder());
    }

    @Override
    public boolean act(float delta) {
        Building building = (Building)getItem();
        Map<ResourceIdentifier, Float> fullCosts = restoreResources.getResourceDeltas();
        Map<ResourceIdentifier, Float> scaledCosts = ResourceUtils.scale(fullCosts, 1 - building.getProgress());
        restoreResources.setResourceDeltas(scaledCosts);
        return super.act(delta);
    }
}
