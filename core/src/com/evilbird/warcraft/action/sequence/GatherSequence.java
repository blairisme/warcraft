package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.common.AnimateAction;
import com.evilbird.engine.action.common.DisableAction;
import com.evilbird.engine.action.common.SelectAction;
import com.evilbird.engine.action.common.VisibleAction;
import com.evilbird.engine.action.framework.*;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.item.specialized.animated.Animated;
import com.evilbird.engine.item.specialized.animated.AnimationIdentifier;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.action.common.AnimationAliasAction;
import com.evilbird.warcraft.action.common.ReplacementAction;
import com.evilbird.warcraft.action.common.ResourceTransferAction;
import com.evilbird.warcraft.item.common.capability.Destructible;
import com.evilbird.warcraft.item.common.capability.ResourceContainer;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.resource.Resource;
import com.evilbird.warcraft.item.unit.resource.ResourceType;
import com.evilbird.engine.common.function.Supplier;

import static com.evilbird.engine.item.ItemOperations.findClosest;
import static com.evilbird.engine.item.ItemSuppliers.isAlive;

/**
 * @author Blair Butterworth
 */
//TODO: Cope with leaving mid gather and returning to Depot
//TODO: Cope with other types of depot. E.g., lumbermill
public abstract class GatherSequence implements ActionProvider
{
    private MoveSequence moveSequence;

    public GatherSequence(MoveSequence moveSequence) {
        this.moveSequence = moveSequence;
    }

    @Override
    public Action get(ActionType action, Item gatherer, Item resource, UserInput input)
    {
        Item player = gatherer.getParent();
        Item depot = findClosest((ItemGroup)player, UnitType.TownHall, gatherer);

        Action depositHeldResources = depositHeldResources(gatherer, depot, player);
        Action gatherMoreResources = gather(gatherer, resource, depot, player);
        Action gatherResources = new SequenceAction(depositHeldResources, gatherMoreResources);

        return new ReplacementAction(gatherer, gatherResources);
    }

    protected Action depositHeldResources(Item gatherer, Item depot, Item player)
    {
        Action depositHeldGold = depositHeldResource(gatherer, depot, player, ResourceType.Gold);
        Action depositHeldWood = depositHeldResource(gatherer, depot, player, ResourceType.Wood);
        return new SequenceAction(depositHeldGold, depositHeldWood);
    }

    protected Action depositHeldResource(Item gatherer, Item depot, Item player, ResourceType type)
    {
        ResourceContainer gathererResources = (ResourceContainer)gatherer;
        if (gathererResources.getResource(type) > 0) {
            return deposit(gatherer, depot, player, type);
        }
        return new EmptyAction();
    }

    protected Action gather(final Item gatherer, final Item resource, final Item depot, final Item player)
    {
        Supplier<Action> supplier = new Supplier<Action>() {
            public Action get() {
                Item closestDepot = findClosest(depot);
                Item closestResource = findClosest(resource);
                ResourceType type = ((Resource)resource).getResourceType();

                Action obtain = obtain(gatherer, closestResource, type);
                Action deposit = deposit(gatherer, closestDepot, player, type);
                return new SequenceAction(obtain, deposit);
            }
        };
        Action gather = new SuppliedAction(supplier);
        return new RepeatedAction(gather);
    }

    protected Action obtain(Item gatherer, Item resource, ResourceType type)
    {
        Action move = moveSequence.get(gatherer, resource);
        Action preObtain = preObtainAction(gatherer, resource, type);
        Action obtain = obtainAction(gatherer, resource, type);
        Action postObtain = postObtainAction(gatherer, resource, type);
        return new SequenceAction(move, preObtain, obtain, postObtain);
    }

    protected Action obtainAction(Item gatherer, Item resource, ResourceType type)
    {
        float transferAmount = getGatherCapacity(gatherer);
        Action transferAction = new ResourceTransferAction((ResourceContainer)resource, (ResourceContainer)gatherer, type, transferAmount);
        Action transferDelay = new DelayedAction(getGatherSpeed(gatherer));
        return new SequenceAction(transferDelay, transferAction);
    }

    protected Action preObtainAction(Item gatherer, Item resource, ResourceType type)
    {
        AnimationIdentifier obtainAnimationId = UnitAnimation.getGatherAnimation(type);
        Action gathererAnimation = new AnimateAction((Animated)gatherer, obtainAnimationId);
        Action resourceAnimation = new AnimateAction((Animated)resource, obtainAnimationId);
        return new ParallelAction(gathererAnimation, resourceAnimation);
    }

    protected Action postObtainAction(Item gatherer, Item resource, ResourceType type)
    {
        Action gatherMovement = new AnimationAliasAction((Animated)gatherer, UnitAnimation.Move, UnitAnimation.getMoveAnimation(type));
        Action gathererIdle = new AnimationAliasAction((Animated)gatherer, UnitAnimation.Idle, UnitAnimation.getIdleAnimation(type));

        Action deselect = new SelectAction(resource, false);
        Action disable = new DisableAction(resource, false);
        Action deadAnimation = new AnimateAction((Animated)resource, UnitAnimation.Dead);
        Action deadAction = new ParallelAction(deselect, disable, deadAnimation);

        Action aliveAction = new AnimateAction((Animated)resource, UnitAnimation.Idle);
        Action resourceAnimation = new BranchAction(isAlive((Destructible)resource), aliveAction, deadAction);

        return new ParallelAction(gatherMovement, gathererIdle, resourceAnimation);
    }

    protected Action deposit(Item gatherer, Item depot, Item player, ResourceType type)
    {
        Action move = moveSequence.get(gatherer, depot);
        Action preDeposit = preDepositAction(gatherer, type);
        Action deposit = depositAction(gatherer, player, type);
        Action postDeposit = postDepositAction(gatherer);
        return new SequenceAction(move, preDeposit, deposit, postDeposit);
    }

    protected Action depositAction(Item gatherer, Item player, ResourceType type)
    {
        Action transfer = new ResourceTransferAction((ResourceContainer)gatherer, (ResourceContainer)player, type, getGatherCapacity(gatherer));
        Action transferDelay = new DelayedAction(new TimeDuration(5f));
        return new SequenceAction(transfer, transferDelay);
    }

    protected Action preDepositAction(Item gatherer, ResourceType type)
    {
        Action deselect = new SelectAction(gatherer, false);
        Action disable = new DisableAction(gatherer, false);
        Action hide = new VisibleAction(gatherer, false);
        return new ParallelAction(deselect, disable, hide);
    }

    private Action postDepositAction(Item gatherer)
    {
        Action resetMove = new AnimationAliasAction((Animated)gatherer, UnitAnimation.Move, UnitAnimation.MoveBasic);
        Action animate = new AnimationAliasAction((Animated)gatherer, UnitAnimation.Idle, UnitAnimation.IdleBasic);
        Action enable = new DisableAction(gatherer, true);
        Action show = new VisibleAction(gatherer, true);
        return new ParallelAction(resetMove, animate, enable, show);
    }

    protected abstract float getGatherCapacity(Item gatherer);

    protected abstract float getGatherSpeed(Item gatherer);
}
