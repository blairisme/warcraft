package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.common.ClearAction;
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
public abstract class GatherSequence implements ActionProvider
{
    @Override
    public Action get(ActionType action, Item gatherer, Item resource, UserInput input)
    {
        Action gather = gather(gatherer, resource);
        Action reset = new ClearAction(gatherer, gather);
        return new SequenceAction(reset, gather);
    }

    protected Action gather(Item gatherer, Item resource)
    {
        Item depot = findDepot(gatherer);
        Item player = findPlayer(gatherer);
        ResourceType type = getResourceProperty(resource);
        Action gather = gather(gatherer, resource, type, depot, player);
        return new RepeatedAction(gather);
    }

    protected Action gather(Item gatherer, Item resource, ResourceType type, Item depot, Item player)
    {
        Action moveToResource = move(gatherer, resource);
        Action obtainResource = obtain(gatherer, resource, type);
        Action moveToDepot = move(gatherer, depot);
        Action depositResource = deposit(gatherer, depot, player, type);
        return new SequenceAction(moveToResource, obtainResource, moveToDepot, depositResource);
    }

    protected Action move(Item target, Item destination)
    {
        Action animate = new AnimateAction((Animated)target, UnitAnimation.Move);
        Action reposition = new MoveAction((Movable)target, destination);
        Action idle = new AnimateAction((Animated)target, UnitAnimation.Idle);
        return new SequenceAction(animate, reposition, idle);
    }

    protected Action obtain(Item gatherer, Item resource, ResourceType type)
    {
        Action setup = obtainSetup(gatherer, resource, type);
        Action transfer = obtainTransfer(gatherer, resource, type);
        Action teardown = obtainTeardown(gatherer, resource, type);
        return new SequenceAction(setup, transfer, teardown);
    }

    protected Action obtainTransfer(Item gatherer, Item resource, ResourceType type)
    {
        ResourceContainer source = (ResourceContainer)resource;;
        ResourceContainer destination = (ResourceContainer)gatherer;
        Action transferAction = new ResourceTransferAction(source, destination, type, 10f);
        return new DelayedAction(transferAction, new TimeDuration(10f));
    }

    protected abstract Action obtainSetup(Item gatherer, Item resource, ResourceType type);

    protected abstract Action obtainTeardown(Item gatherer, Item resource, ResourceType type);

    private Action deposit(Item gatherer, Item depot, Item player, ResourceType type)
    {
        Action setup = depositSetup(gatherer, depot, player, type);
        Action transfer = depositTransfer(gatherer, depot, player, type);
        Action teardown = depositTeardown(gatherer, depot, player, type);
        return new SequenceAction(setup, transfer, teardown);
    }

    protected Action depositTransfer(Item gatherer, Item depot, Item player, ResourceType type)
    {
        ResourceContainer source = (ResourceContainer)gatherer;
        ResourceContainer destination = (ResourceContainer)player;
        Action transferAction = new ResourceTransferAction(source, destination, type, 10f);
        return new DelayedAction(transferAction, new TimeDuration(10f));
    }

    protected abstract Action depositSetup(Item gatherer, Item depot, Item Player, ResourceType type);

    protected abstract Action depositTeardown(Item gatherer, Item depot, Item Player, ResourceType type);

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
}
