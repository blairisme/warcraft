package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.common.*;
import com.evilbird.engine.action.framework.DelayedAction;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.action.framework.RepeatedAction;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.action.framework.TimeDuration;
import com.evilbird.engine.common.collection.Collections;
import com.evilbird.engine.common.function.Comparator;
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.item.ItemOperations;
import com.evilbird.engine.item.specialized.animated.Animated;
import com.evilbird.engine.item.specialized.animated.AnimationIdentifier;
import com.evilbird.engine.item.specialized.animated.Audible;
import com.evilbird.engine.item.specialized.animated.SoundIdentifier;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.action.common.AnimationAliasAction;
import com.evilbird.warcraft.action.common.MoveAction;
import com.evilbird.warcraft.action.subsequence.MoveSubsequence;
import com.evilbird.warcraft.item.common.capability.Movable;
import com.evilbird.warcraft.item.common.capability.ResourceContainer;
import com.evilbird.warcraft.action.common.ResourceTransferAction;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.resource.ResourceType;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;

import java.util.Collection;

import javax.inject.Inject;

import static com.evilbird.engine.item.ItemComparators.closestItem;
import static com.evilbird.engine.item.ItemOperations.findClosest;
import static com.evilbird.engine.item.ItemPredicates.itemWithType;

/**
 * @author Blair Butterworth
 */
public class GatherSequence implements ActionProvider
{
    private AudibleSequence audibleSequence;
    private MoveSubsequence moveSubsequence;

    @Inject
    public GatherSequence(
        AudibleSequence audibleSequence,
        MoveSubsequence moveSubsequence)
    {
        this.audibleSequence = audibleSequence;
        this.moveSubsequence = moveSubsequence;
    }

    @Override
    public Action get(ActionType action, Item gatherer, Item resource, UserInput input)
    {
        Item player = gatherer.getParent();
        Item depot = findClosest((ItemGroup)player, UnitType.TownHall, gatherer);
        ResourceType type = ResourceType.valueOf(resource.getType().toString()); //TODO: replace with resource.getType()

        Action depositHeldResources = depositHeldResources(gatherer, depot, player);
        Action gatherMoreResources = gather(gatherer, resource, type, depot, player);
        return new SequenceAction(depositHeldResources, gatherMoreResources);
    }

    private Action depositHeldResources(Item gatherer, Item depot, Item player)
    {
        Action depositHeldGold = depositHeldResource(gatherer, depot, player, ResourceType.Gold);
        Action depositHeldWood = depositHeldResource(gatherer, depot, player, ResourceType.Wood);
        return new SequenceAction(depositHeldGold, depositHeldWood);
    }

    private Action depositHeldResource(Item gatherer, Item depot, Item player, ResourceType type)
    {
        ResourceContainer gathererResources = (ResourceContainer)gatherer;
        if (gathererResources.getResource(type) > 0) {
            return deposit(gatherer, depot, player, type);
        }
        return new PlaceholderAction();
    }

    private Action gather(Item gatherer, Item resource, ResourceType type, Item depot, Item player)
    {
        Action obtain = obtain(gatherer, resource, type);
        Action deposit = deposit(gatherer, depot, player, type);
        Action gather = new SequenceAction(obtain, deposit);
        return new RepeatedAction(gather);
    }

    private Action obtain(Item gatherer, Item resource, ResourceType type)
    {
        Action move = moveSubsequence.get(gatherer, resource);
        Action deselect = new SelectAction(gatherer, false);
        Action disable = new DisableAction(gatherer, false);
        Action preAnimation = preObtainAnimation(gatherer, resource, type);
        Action obtain = obtainAction(gatherer, resource, type);
        Action postAnimation = postObtainAnimation(gatherer, resource, type);
        Action enable = new DisableAction(gatherer, true);
        return new SequenceAction(move, deselect, disable, preAnimation, obtain, postAnimation, enable);
    }

    private Action obtainAction(Item gatherer, Item resource, ResourceType type)
    {
        SoundIdentifier obtainSoundId = UnitSound.getGatherSound(type);
        Action gathererSound = audibleSequence.get(gatherer, obtainSoundId, 10, 1f);
        Action resourceSound = audibleSequence.get(resource, obtainSoundId, 10, 1f);
        Action transferAction = new ResourceTransferAction((ResourceContainer)resource, (ResourceContainer)gatherer, type, 10f);
        Action transferDelay = new DelayedAction(transferAction, new TimeDuration(10f));
        return new ParallelAction(transferDelay, gathererSound, resourceSound);
    }

    private Action preObtainAnimation(Item gatherer, Item resource, ResourceType type)
    {
        AnimationIdentifier obtainAnimationId = UnitAnimation.getGatherAnimation(type);
        Action gathererAnimation = new AnimateAction((Animated)gatherer, obtainAnimationId);
        Action resourceAnimation = new AnimateAction((Animated)resource, obtainAnimationId);
        return new ParallelAction(gathererAnimation, resourceAnimation);
    }

    private Action postObtainAnimation(Item gatherer, Item resource, ResourceType type)
    {
        Action gatherMovement = new AnimationAliasAction((Animated)gatherer, UnitAnimation.Move, UnitAnimation.getMoveAnimation(type));
        Action gathererIdle = new AnimateAction((Animated)gatherer, UnitAnimation.Idle);
        Action resourceIdle = new AnimateAction((Animated)resource, UnitAnimation.Idle);
        return new ParallelAction(gatherMovement, gathererIdle, resourceIdle);
    }

    private Action deposit(Item gatherer, Item depot, Item player, ResourceType type)
    {
        Action move = moveSubsequence.get(gatherer, depot);
        Action deselect = new SelectAction(gatherer, false);
        Action disable = new DisableAction(gatherer, false);
        Action preAnimation = preDepositAnimation(gatherer, type);
        Action deposit = depositAction(gatherer, player, type);
        Action postAnimation = postDepositAnimation(gatherer);
        Action enable = new DisableAction(gatherer, true);
        return new SequenceAction(move, deselect, disable, preAnimation, deposit, postAnimation, enable);
    }

    private Action depositAction(Item gatherer, Item player, ResourceType type)
    {
        Action transfer = new ResourceTransferAction((ResourceContainer)gatherer, (ResourceContainer)player, type, 10f);
        Action transferDelay = new DelayedAction(transfer, new TimeDuration(10f));
        return transferDelay;
    }

    private Action preDepositAnimation(Item gatherer, ResourceType type)
    {
        AnimationIdentifier animationId = UnitAnimation.getDepositAnimation(type);
        Action gathererAnimation = new AnimateAction((Animated)gatherer, animationId);
        return new ParallelAction(gathererAnimation);
    }

    private Action postDepositAnimation(Item gatherer)
    {
        Action gatherMovement = new AnimationAliasAction((Animated)gatherer, UnitAnimation.Move, UnitAnimation.MoveBasic);
        Action gathererIdle = new AnimateAction((Animated)gatherer, UnitAnimation.Idle);
        return new ParallelAction(gatherMovement, gathererIdle);
    }
}
