/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.common.*;
import com.evilbird.engine.action.framework.*;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.engine.item.ItemType;
import com.evilbird.warcraft.action.common.animation.AnimationAliasAction;
import com.evilbird.warcraft.action.common.resource.ResourceTransferAction;
import com.evilbird.warcraft.action.common.resource.ResourceTransferRelay;
import com.evilbird.warcraft.action.move.MoveToItem;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.resource.ResourceType;

import static com.evilbird.engine.item.ItemOperations.findClosest;
import static com.evilbird.warcraft.item.common.query.UnitOperations.getHumanPlayer;
import static com.evilbird.warcraft.item.unit.UnitAnimation.getGatherAnimation;

/**
 * Instances of this class provide common gathering functionality.
 *
 * @author Blair Butterworth
 */
//TODO: Cope with the resource dieing during action
//TODO: Cope with leaving mid gather and returning to Depot
//TODO: Cope with other types of depot. E.g., lumbermill
//TODO: Cope with multiple gathers using single resource - trees
//TODO: Cope with no more resources
//TODO: Cope with no route to resource
//TODO: Only choose next resource if it can be accessed - trees on the edge of forests, not within
//TODO: Need to "kill" resource when empty.
public abstract class GatherAction extends DelegateAction
{
    private Action obtainSequence;
    private Action depositSequence;
    private ResourceTransferRelay relay;

    public GatherAction() {
        relay = new ResourceTransferRelay();
        obtainSequence = obtainSequence();
        depositSequence = depositSequence();

        //Action deposit = new OptionalAction(targetHasResources(getResourceVariety()), depositSequence);
        Action sequence = new SequenceAction(obtainSequence, depositSequence);
        Action repeated = new RepeatedAction(sequence);
        delegate = new ReplacementAction(repeated);
    }

    @Override
    public void setTarget(Item target) {
        super.setTarget(target);
        if (target != null) {
            ItemComposite humanPlayer = getHumanPlayer(target);
            Item depot = findClosest(humanPlayer, getDepotType(), target);
            depositSequence.setTarget(depot);

            ItemComposite resourcePlayer = target.getParent();
            Item resource = findClosest(resourcePlayer, getResourceType(), target);
            obtainSequence.setTarget(resource);
        }
    }

    public void setObserver(GatherObserver observer) {
        this.relay.setDelegate(observer);
    }

    protected Action obtainSequence() {
        Action move = new MoveToItem();
        Action preObtain = preObtainAnimation();
        Action obtain = obtainResources();
        Action postObtain = postObtainAnimation();
        return new SequenceAction(move, preObtain, obtain, postObtain);
    }

    protected Action obtainResources() {
        Action transferFrom = new ResourceTransferAction(ActionTarget.Target, getResourceVariety(), getGatherCapacity() * -1, relay);
        Action transferTo = new ResourceTransferAction(ActionTarget.Item, getResourceVariety(), getGatherCapacity(), relay);
        Action transferAction = new ParallelAction(transferFrom, transferTo);
        Action transferDelay = new DelayedAction(getGatherSpeed());
        return new SequenceAction(transferDelay, transferAction);
    }

    protected Action preObtainAnimation() {
        Action gathererAnimation = new AnimateAction(ActionTarget.Item, getGatherAnimation(getResourceVariety()));
        //Action resourceAnimation = new AnimateAction(ActionTarget.Target, UnitAnimation.Gathering); //TODO
        return new ParallelAction(gathererAnimation/*, resourceAnimation*/);
    }

    protected Action postObtainAnimation() {
        Action gatherMovement = new AnimationAliasAction(UnitAnimation.Move, UnitAnimation.getMoveAnimation(getResourceVariety()));
        Action gathererIdle = new AnimationAliasAction(UnitAnimation.Idle, UnitAnimation.getIdleAnimation(getResourceVariety()));

        //Action deselect = new SelectAction(ActionTarget.Target, false);
        //Action disable = new DisableAction(ActionTarget.Target, false);
        //Action deadAnimation = new AnimateAction(ActionTarget.Target, UnitAnimation.Dead);
        //Action deadAction = new ParallelAction(deselect, disable, deadAnimation);
        //Action aliveAction = new AnimateAction(ActionTarget.Target, UnitAnimation.Idle);
        //Action resourceAnimation = aliveAction; //= new BranchAction(targetHasResources(getResourceVariety()), aliveAction, deadAction);

        return new ParallelAction(gatherMovement, gathererIdle/*, resourceAnimation*/);
    }

    protected Action depositSequence() {
        Action move = new MoveToItem();
        Action preDeposit = preDepositAction();
        Action deposit = depositAction();
        Action postDeposit = postDepositAction();
        return new SequenceAction(move, preDeposit, deposit, postDeposit);
    }

    protected Action depositAction() {
        Action transferFrom = new ResourceTransferAction(ActionTarget.Item, getResourceVariety(), getGatherCapacity() * -1, relay);
        Action transferTo = new ResourceTransferAction(ActionTarget.Player, getResourceVariety(), getGatherCapacity(), relay);
        Action transfer = new ParallelAction(transferFrom, transferTo);
        Action transferDelay = new DelayedAction(5f);
        return new SequenceAction(transfer, transferDelay);
    }

    protected Action preDepositAction() {
        Action deselect = new SelectAction(false);
        Action disable = new DisableAction(false);
        Action hide = new VisibleAction(false);
        return new ParallelAction(deselect, disable, hide);
    }

    protected Action postDepositAction() {
        Action resetMove = new AnimationAliasAction(UnitAnimation.Move, UnitAnimation.MoveBasic);
        Action animate = new AnimationAliasAction(UnitAnimation.Idle, UnitAnimation.IdleBasic);
        Action enable = new DisableAction(true);
        Action show = new VisibleAction(true);
        return new ParallelAction(resetMove, animate, enable, show);
    }

    protected abstract ItemType getDepotType();

    protected abstract ItemType getResourceType();

    protected abstract ResourceType getResourceVariety();

    protected abstract float getGatherCapacity();

    protected abstract float getGatherSpeed();
}
