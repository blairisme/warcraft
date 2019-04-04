/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.action.framework.DelegateAction;

import javax.inject.Inject;

/**
 * Instances of this class stop the construction of a building.
 *
 * @author Blair Butterworth
 */
public class ConstructCancel extends DelegateAction
{
    @Inject
    public ConstructCancel() {
    }

//    private VisibleAction restoreBuilder;
//    private ResourceTransferAction restoreResources;
//
//    @Inject
//    public ConstructCancel() {
//        restoreResources = new ResourceTransferAction(ActionTarget.Player);
//        restoreBuilder = new VisibleAction(true);
//        //Action removeBuilding = new DeathAction();
//        delegate = new ParallelAction(restoreResources, restoreBuilder/*, removeBuilding*/);
//    }
//
//    public void setBuildCost(Map<ResourceIdentifier, Float> resources) {
//        this.restoreResources.setResourceDeltas(resources);
//    }
//
//    @Override
//    public void setItem(Item item) {
//        super.setItem(item);
//        Building building = (Building)item;
//        restoreBuilder.setItem(building.getConstructor());
//    }
//
//    @Override
//    public boolean act(float delta) {
//        Building building = (Building)getItem();
//        Map<ResourceIdentifier, Float> fullCosts = restoreResources.getResourceDeltas();
//        Map<ResourceIdentifier, Float> scaledCosts = ResourceUtils.scale(fullCosts, 1 - building.getConstructionProgress());
//        restoreResources.setResourceDeltas(scaledCosts);
//        return super.act(delta);
//    }
}
