/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.action.framework.ScenarioAction;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.common.resource.ResourceTransferAction.refund;
import static com.evilbird.warcraft.action.construct.ConstructionAction.stopConstructing;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isConstructing;

/**
 * Instances of this class stop the construction of a building.
 *
 * @author Blair Butterworth
 */
public class ConstructCancel extends ScenarioAction<ConstructActions>
{
    private ConstructReporter reporter;

    /**
     * Constructs a new instance of this class given a {@link ConstructReporter}
     * used to report the transfer of resources involved in the refund for the
     * partially complete train operation.
     *
     * @param reporter  a {@code TrainReporter} instance. This parameter
     *                  cannot be {@code null}.
     */
    @Inject
    public ConstructCancel(ConstructReporter reporter) {
        this.reporter = reporter;
    }

    @Override
    protected void steps(ConstructActions action) {
        scenario(action);
        given(isConstructing());
        then(stopConstructing());
        then(refund(action, amount(), reporter));
    }

    private float amount() {
        Building building = (Building)getItem();
        return 1 - building.getConstructionProgress();
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
