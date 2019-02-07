/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.train;

import com.evilbird.engine.action.common.ActionTarget;
import com.evilbird.engine.action.common.AlignAction;
import com.evilbird.engine.action.common.CreateAction;
import com.evilbird.engine.action.framework.Action;
import com.evilbird.engine.action.framework.DelegateAction;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.action.framework.duration.TimeDuration;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.warcraft.action.common.ProduceAction;
import com.evilbird.warcraft.action.common.ProducingAction;
import com.evilbird.warcraft.action.common.ProgressAction;
import com.evilbird.warcraft.action.common.ResourceTransferAction;
import com.evilbird.warcraft.item.common.capability.ResourceIdentifier;
import com.evilbird.warcraft.item.common.resource.ResourceUtils;
import com.evilbird.warcraft.item.unit.UnitType;

import javax.inject.Inject;
import java.util.Map;

import static com.evilbird.engine.common.lang.Alignment.BottomLeft;

/**
 * Instances of this action sequence create a new unit, decrementing the
 * players resources and adding delay before the new unit can be used.
 *
 * @author Blair Butterworth
 */
//TODO: Combine create and align actions
public class TrainAction extends DelegateAction
{
    private CreateAction create;
    private ProduceAction produce;
    private ResourceTransferAction purchase;

    @Inject
    public TrainAction(ItemFactory itemFactory) {
        purchase = new ResourceTransferAction(ActionTarget.Player);
        produce = new ProduceAction();
        Action create = createUnit(itemFactory);
        delegate = new SequenceAction(purchase, produce, create);
    }

    @Override
    public boolean act(float delta) {
        return delegate.act(delta);
    }

    public void setTrainType(UnitType type){
        this.create.setType(type);
    }

    public void setTrainCost(Map<ResourceIdentifier, Float> resources) {
        this.purchase.setResourceDeltas(ResourceUtils.negate(resources));
    }

    public void setTrainDuration(TimeDuration duration) {
        this.produce.setDuration(duration);
    }

    private Action createUnit(ItemFactory itemFactory) {
        Action reposition = new AlignAction(BottomLeft);
        create = new CreateAction(itemFactory, reposition);
        return new SequenceAction(create, reposition);
    }
}
