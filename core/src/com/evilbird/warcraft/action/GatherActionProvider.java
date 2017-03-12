package com.evilbird.warcraft.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ModifyAction;
import com.evilbird.engine.action.ParallelAction;
import com.evilbird.engine.action.RepeatedAction;
import com.evilbird.engine.action.SequenceAction;
import com.evilbird.engine.action.duration.ActionDuration;
import com.evilbird.engine.action.duration.TimeDuration;
import com.evilbird.engine.action.modifier.ActionModifier;
import com.evilbird.engine.action.modifier.DeltaModifier;
import com.evilbird.engine.action.modifier.DeltaType;
import com.evilbird.engine.common.collection.Collections;
import com.evilbird.engine.common.function.Comparator;
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemProperty;
import com.evilbird.warcraft.item.world.unit.UnitProperties;

import java.util.Collection;

import javax.inject.Inject;

import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.engine.item.ItemComparators.closestItem;
import static com.evilbird.engine.item.ItemPredicates.itemWithProperty;
import static com.evilbird.engine.item.ItemPredicates.itemWithType;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class GatherActionProvider implements ActionProvider
{
    private MoveActionProvider moveActionProvider;
    private AudioActionProvider audioActionProvider;
    private AnimateActionProvider animateActionProvider;
    private SelectionActionProvider selectionActionProvider;

    @Inject
    public GatherActionProvider(
        MoveActionProvider moveActionProvider,
        AudioActionProvider audioActionProvider,
        AnimateActionProvider animateActionProvider,
        SelectionActionProvider selectionActionProvider)
    {
        this.moveActionProvider = moveActionProvider;
        this.audioActionProvider = audioActionProvider;
        this.animateActionProvider = animateActionProvider;
        this.selectionActionProvider = selectionActionProvider;
    }

    @Override
    public Action get(Item item, Item resource, UserInput input)
    {
        Item depot = findDepot(item);
        Item player = findPlayer(item);
        ItemProperty resourceProperty = getResourceProperty(resource);
        Identifier gatherAnimation = getGatherAnimation(resourceProperty);
        Identifier gatherSound = getGatherSound(resourceProperty);
        Identifier depositAnimation = getDepositAnimation(resourceProperty);
        return gather(item, resource, player, depot, resourceProperty, gatherAnimation, gatherSound, depositAnimation);
    }

    private Action gather(Item gatherer, Item resource, Item player, Item depot, ItemProperty property,
                          Identifier gatherAnimation, Identifier gatherSound, Identifier depositAnimation)
    {
        Action moveToResource = moveActionProvider.get(gatherer, resource);
        Action transfer = resourceTransfer(resource, gatherer, property, gatherAnimation, gatherSound);
        Action moveToDepot = moveActionProvider.get(gatherer, depot);
        Action deposit = resourceTransfer(gatherer, player, property, depositAnimation, null);
        Action gather = new SequenceAction(moveToResource, transfer, moveToDepot, deposit);
        return new RepeatedAction(gather);
    }

    private Action resourceTransfer(Item source, Item destination, ItemProperty resource, Identifier animation, Identifier sound)
    {
        Action deselect = selectionActionProvider.get(source, false);
        Action resourceTake = resourceTakeAnimated(source, resource, animation, sound);
        Action resourceReceive = resourceReceiveAnimated(destination, resource, animation);
        return new ParallelAction(deselect, resourceTake, resourceReceive);
    }

    private Action resourceTakeAnimated(Item item, ItemProperty resourceProperty, Identifier animation, Identifier sound)
    {
        Action animateBefore = animateActionProvider.get(item, animation);
        Action soundBefore = audioActionProvider.get(item, sound);
        Action gather = resourceTake(item, resourceProperty);
        Action animateAfter = animateActionProvider.get(item, new Identifier("Idle"));
        return new SequenceAction(animateBefore, soundBefore, gather, animateAfter);
    }

    private Action resourceReceiveAnimated(Item item, ItemProperty resourceProperty, Identifier animation)
    {
        Action animateBefore = animateActionProvider.get(item, animation);
        Action gather = resourceReceive(item, resourceProperty);
        Action animateAfter = animateActionProvider.get(item, new Identifier("Idle"));
        return new SequenceAction(animateBefore, gather, animateAfter);
    }

    private Action resourceTake(Item item, ItemProperty resourceProperty)
    {
        ActionModifier modifier = new DeltaModifier(-10f, DeltaType.PerSecond);
        ActionDuration duration = new TimeDuration(10f);
        return new ModifyAction(item, resourceProperty, modifier, duration);
    }

    private Action resourceReceive(Item item, ItemProperty resourceProperty)
    {
        ActionModifier modifier = new DeltaModifier(1f, DeltaType.PerSecond);
        ActionDuration duration = new TimeDuration(10f);
        return new ModifyAction(item, resourceProperty, modifier, duration);
    }

    private Item findDepot(Item item)
    {
        Predicate<Item> townHall = itemWithType(new Identifier("TownHall"));
        Predicate<Item> ownedByPlayer = itemWithProperty(UnitProperties.Owner, new Identifier("Player1")); //TODO
        Predicate<Item> ownedDepots = both(townHall, ownedByPlayer);
        Comparator<Item> closestDepot = closestItem(item);
        Collection<Item> depots = item.getRoot().findAll(ownedDepots);
        return Collections.min(depots, closestDepot);
    }

    private Item findPlayer(Item item)
    {
        return item.getRoot().find(itemWithType(new Identifier("CurrentPlayer")));
    }

    private ItemProperty getResourceProperty(Item resource)
    {
        return UnitProperties.valueOf(resource.getType().toString());
    }

    private Identifier getGatherAnimation(ItemProperty resourceProperty)
    {
        return new Identifier("Gather" + resourceProperty.toString());
    }

    private Identifier getGatherSound(ItemProperty resourceProperty)
    {
        return new Identifier("Gather" + resourceProperty.toString());
    }

    private Identifier getDepositAnimation(ItemProperty resourceProperty)
    {
        return new Identifier("Deposit" + resourceProperty.toString());
    }
}
