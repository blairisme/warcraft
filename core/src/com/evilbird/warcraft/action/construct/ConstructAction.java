/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.action.common.*;
import com.evilbird.engine.action.framework.Action;
import com.evilbird.engine.action.framework.DelegateAction;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.action.framework.duration.TimeDuration;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.warcraft.action.common.production.ProgressAction;
import com.evilbird.warcraft.action.common.resource.ResourceTransferAction;
import com.evilbird.warcraft.action.common.resource.ResourceTransferRelay;
import com.evilbird.warcraft.action.move.MoveToItem;
import com.evilbird.warcraft.item.common.capability.ResourceIdentifier;
import com.evilbird.warcraft.item.common.resource.ResourceUtils;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.UnitType;

import javax.inject.Inject;
import java.util.Map;

import static com.evilbird.engine.action.common.ActionTarget.Player;
import static com.evilbird.engine.action.common.ActionTarget.Target;

/**
 * Instances of this class construct a building.
 *
 * @author Blair Butterworth
 */
public class ConstructAction extends DelegateAction
{
   // private ConstructObserver observer;
    private ResourceTransferRelay relay;
    private ResourceTransferAction purchaseBuilding;
    private CreateAction createBuilding;
    private ProgressAction progressAction;

    @Inject
    public ConstructAction(ItemFactory itemFactory) {
        Action initialize = initialize();
        Action construct = construct(itemFactory);
        Action complete = complete();
        delegate = new SequenceAction(initialize, construct, complete);
    }

    @Override
    public void setTarget(Item item) {
        super.setTarget(item);
        if (item != null) {
            createBuilding.setPosition(item.getPosition()); //TODO - remove
        }
    }

    public void setBuildType(UnitType type) {
        createBuilding.setType(type);
    }

    public void setBuildDuration(TimeDuration duration) {
        progressAction.setDuration(duration);
    }

    public void setBuildCost(Map<ResourceIdentifier, Float> resources) {
        this.purchaseBuilding.setResourceDeltas(ResourceUtils.negate(resources));
    }

    public void setObserver(ConstructObserver observer) {
        //this.observer = observer;
        this.relay.setDelegate(observer);
    }

    @Override
    public boolean act(float delta) {
        //observer.onConstruct(getItem(), createBuilding.getType(), createBuilding.getPosition());
        return delegate.act(delta);
    }

    private Action initialize() {
        Action initializeState = initializeState();
        Action initializeBuilder = initializeBuilder();
        return new ParallelAction(initializeState, initializeBuilder);
    }

    private Action initializeState() {
        relay = new ResourceTransferRelay();
        purchaseBuilding = new ResourceTransferAction(Player, relay);
        Action removePlaceholder = new RemoveAction(Target);
        return new ParallelAction(removePlaceholder, purchaseBuilding);
    }

    private Action initializeBuilder() {
        Action reposition = new MoveToItem();
        Action deselect = new SelectAction(false);
        Action invisible = new VisibleAction(false);
        Action hide = new ParallelAction(deselect, invisible);
        return new SequenceAction(reposition, hide);
    }

    private Action construct(ItemFactory itemFactory) {
        createBuilding = new CreateAction(itemFactory);
        Action preConstruction = preConstructionAudioVisual(createBuilding);
        Action constructionProgress = constructionProgress(createBuilding);
        Action postConstruction = postConstructionAudioVisual(createBuilding);
        return new SequenceAction(createBuilding, preConstruction, constructionProgress, postConstruction);
    }

    private Action preConstructionAudioVisual(CreateAction createAction) {
        Action soundBefore = new AudibleAction(UnitSound.Construct);
        Action animateBuilderBefore = new AnimateAction(UnitAnimation.Build);
        Action animateBuildingBefore = new AnimateAction(UnitAnimation.Construct);

        createAction.addDependency(animateBuildingBefore);
        return new ParallelAction(animateBuilderBefore, animateBuildingBefore, soundBefore);
    }

    private Action constructionProgress(CreateAction createAction) {
        progressAction = new ProgressAction();
        Action constructing = new ConstructingAction(true);
        Action idle = new ConstructingAction(false);

        createAction.addDependency(constructing);
        createAction.addDependency(progressAction);
        createAction.addDependency(idle);
        return new SequenceAction(constructing, progressAction, idle);
    }

    private Action postConstructionAudioVisual(CreateAction createAction) {
        Action soundAfter = new AudibleAction(UnitSound.Complete);
        Action animateBuilderAfter = new AnimateAction(UnitAnimation.Idle);
        Action animateBuildingAfter = new AnimateAction(UnitAnimation.Idle);

        createAction.addDependency(animateBuildingAfter);
        return new ParallelAction(animateBuilderAfter, animateBuildingAfter, soundAfter);
    }

    private Action complete() {
        Action reposition = new AlignAction(Alignment.BottomLeft);
        Action show = new VisibleAction(true);
        return new ParallelAction(reposition, show);
    }
}
