package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.common.DisableAction;
import com.evilbird.engine.action.framework.DelayedAction;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.action.framework.RepeatedAction;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.action.framework.TimeDuration;
import com.evilbird.engine.action.common.AnimateAction;
import com.evilbird.engine.action.common.AudibleAction;
import com.evilbird.engine.action.common.SelectAction;
import com.evilbird.engine.common.collection.Collections;
import com.evilbird.engine.common.function.Comparator;
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.item.specialized.animated.Animated;
import com.evilbird.engine.item.specialized.animated.AnimationIdentifier;
import com.evilbird.engine.item.specialized.animated.Audible;
import com.evilbird.engine.item.specialized.animated.SoundIdentifier;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.action.common.MoveAction;
import com.evilbird.warcraft.item.common.capability.Movable;
import com.evilbird.warcraft.item.common.capability.ResourceContainer;
import com.evilbird.warcraft.action.common.ResourceTransferAction;
import com.evilbird.warcraft.item.unit.resource.ResourceType;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;

import java.util.Collection;

import javax.inject.Inject;

import static com.evilbird.engine.item.ItemComparators.closestItem;
import static com.evilbird.engine.item.ItemPredicates.itemWithType;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class GatherSequence implements ActionProvider
{
    @Inject
    public GatherSequence()
    {
    }

    @Override
    public Action get(ActionType action, Item gatherer, Item resource, UserInput input)
    {
        Item depot = findDepot(gatherer);
        Item player = findPlayer(gatherer);
        ResourceType type = getResourceProperty(resource);
        Action gather = gather(gatherer, resource, type, depot, player);
        return new RepeatedAction(gather);
    }

    private Action gather(Item gatherer, Item resource, ResourceType type, Item depot, Item player)
    {
        Action moveToResource = move(gatherer, resource);
        Action obtainResource = obtain(gatherer, resource, type);
        Action moveToDepot = move(gatherer, depot);
        Action depositResource = deposit(gatherer, player, type);
        return new SequenceAction(moveToResource, obtainResource, moveToDepot, depositResource);
    }

    private Action move(Item target, Item destination)
    {
        Action animate = new AnimateAction((Animated)target, UnitAnimation.Move);
        Action reposition = new MoveAction((Movable)target, destination);
        Action idle = new AnimateAction((Animated)target, UnitAnimation.Idle);
        return new SequenceAction(animate, reposition, idle);
    }

    private Action obtain(Item gatherer, Item resource, ResourceType type)
    {
        Action deselect = new SelectAction(gatherer, false);
        Action disable = new DisableAction(gatherer, false);
        Action meh = new ParallelAction(deselect, disable);

        Action setAnimation = obtainAnimation(gatherer, resource, type);
        Action transfer = obtainAction(gatherer, resource, type);
        Action resetAnimation = resetAnimation(gatherer, resource);
        return new SequenceAction(meh, setAnimation, transfer, resetAnimation);
    }

    private Action obtainAnimation(Item gatherer, Item resource, ResourceType type)
    {
        AnimationIdentifier obtainAnimationId = getObtainAnimation(type);
        Action gathererAnimation = new AnimateAction((Animated)gatherer, obtainAnimationId);
        Action resourceAnimation = new AnimateAction((Animated)resource, obtainAnimationId);
        return new ParallelAction(gathererAnimation, resourceAnimation);
    }

    private Action obtainAction(Item gatherer, Item resource, ResourceType type)
    {
        SoundIdentifier obtainSoundId = getObtainSound(type);
        Action gathererSound = repeatedSound(gatherer, obtainSoundId);
        Action resourceSound = repeatedSound(resource, obtainSoundId);

        Action transferAction = new ResourceTransferAction((ResourceContainer)resource, (ResourceContainer)gatherer, type, 10f);
        Action transferDelay = new DelayedAction(transferAction, new TimeDuration(10f));

        return new ParallelAction(transferDelay, gathererSound, resourceSound);
    }

    private Action resetAnimation(Item gatherer, Item resource)
    {
        Action gathererIdle = new AnimateAction((Animated)gatherer, UnitAnimation.Idle);
        Action resourceIdle = new AnimateAction((Animated)resource, UnitAnimation.Idle);
        return new ParallelAction(gathererIdle, resourceIdle);
    }

    private Action deposit(Item gatherer, Item player, ResourceType type)
    {
        AnimationIdentifier depositAnimation = getDepositAnimation(type);
        SoundIdentifier depositSound = getDepositSound(type);

        Action gathererAnimation = new AnimateAction((Animated)gatherer, depositAnimation);
        //Action playerAnimation = new AnimateAction((Animated)player, depositAnimation);
        Action gathererSound = repeatedSound(gatherer, depositSound);
        //Action playerSound = repeatedSound(player, depositSound);
        Action setup = new ParallelAction(gathererAnimation, gathererSound);

        Action transfer = new ResourceTransferAction((ResourceContainer)gatherer, (ResourceContainer)player, type, 10f);
        Action transferWait = new DelayedAction(transfer, new TimeDuration(10f));

        return new ParallelAction(setup, transferWait);
    }

    private Action repeatedSound(Item item, SoundIdentifier soundId)
    {
        Action sound = new AudibleAction((Audible)item, soundId);
        Action soundBuffer = new DelayedAction(sound, new TimeDuration(1f));
        return new RepeatedAction(soundBuffer, 10);
    }

    private Item findDepot(Item item)
    {
        ItemGroup player = item.getParent();
        Predicate<Item> ownedDepots = itemWithType(new NamedIdentifier("TownHall"));
        Comparator<Item> closestDepot = closestItem(item);
        Collection<Item> depots = player.findAll(ownedDepots);
        return Collections.min(depots, closestDepot);
    }

    private Item findPlayer(Item item)
    {
        return item.getParent();
    }

    private ResourceType getResourceProperty(Item resource)
    {
        return ResourceType.valueOf(resource.getType().toString());
    }

    private AnimationIdentifier getObtainAnimation(ResourceType resource)
    {
        return UnitAnimation.valueOf("Gather" + resource.toString());
    }

    private SoundIdentifier getObtainSound(ResourceType resource)
    {
        return UnitSound.valueOf("Gather" + resource.toString());
    }

    private AnimationIdentifier getDepositAnimation(ResourceType resource)
    {
        return UnitAnimation.valueOf("Deposit" + resource.toString());
    }

    private SoundIdentifier getDepositSound(ResourceType resource)
    {
        return UnitSound.valueOf("Deposit" + resource.toString());
    }
}
