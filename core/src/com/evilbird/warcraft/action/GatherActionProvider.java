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
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemProperty;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.item.world.unit.UnitProperties;

import javax.inject.Inject;

import static com.evilbird.engine.item.ItemPredicates.itemWithId;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class GatherActionProvider implements ActionProvider
{
    private MoveActionProvider moveActionProvider;
    private AnimateActionProvider animateActionProvider;
    private SelectionActionProvider selectionActionProvider;

    @Inject
    public GatherActionProvider(
        MoveActionProvider moveActionProvider,
        AnimateActionProvider animateActionProvider,
        SelectionActionProvider selectionActionProvider)
    {
        this.moveActionProvider = moveActionProvider;
        this.animateActionProvider = animateActionProvider;
        this.selectionActionProvider = selectionActionProvider;
    }

    @Override
    public Action get(Item item, Item resource, UserInput input)
    {
        ItemRoot root = item.getRoot();
        Item depot = root.find(itemWithId(new Identifier("TownHall1"))); //TODO
        Item player = root.find(itemWithId(new Identifier("Player1"))); //TODO

        if (Objects.equals(resource.getType(), new Identifier("Gold"))) //TODO
        {
            ItemProperty property = UnitProperties.Gold;
            Identifier gatherAnimation = new Identifier("GatherGold");
            Identifier depositAnimation = new Identifier("DepositGold");
            return gather(item, resource, player, depot, property, gatherAnimation, depositAnimation);
        }
        if (Objects.equals(resource.getType(), new Identifier("Wood"))) //TODO
        {
            ItemProperty property = UnitProperties.Wood;
            Identifier gatherAnimation = new Identifier("GatherWood");
            Identifier depositAnimation = new Identifier("DepositWood");
            return gather(item, resource, player, depot, property, gatherAnimation, depositAnimation);
        }
        throw new UnsupportedOperationException();
    }

    private Action gather(Item gatherer, Item resource, Item player, Item depot, ItemProperty property, Identifier gatherAnimation, Identifier depositAnimation)
    {
        Action moveToResource = moveActionProvider.get(gatherer, resource);
        Action transfer = resourceTransfer(resource, gatherer, property, gatherAnimation, gatherAnimation);
        Action moveToDepot = moveActionProvider.get(gatherer, depot);
        Action deposit = resourceTransfer(gatherer, player, property, depositAnimation, depositAnimation);
        Action gather = new SequenceAction(moveToResource, transfer, moveToDepot, deposit);
        return new RepeatedAction(gather);
    }

    private Action resourceTransfer(Item source, Item destination, ItemProperty resource, Identifier takeAnimation, Identifier receiveAnimation)
    {
        Action deselect = selectionActionProvider.get(source, false);
        Action resourceTake = resourceTakeAnimated(source, resource, takeAnimation);
        Action resourceReceive = resourceReceiveAnimated(destination, resource, receiveAnimation);
        return new ParallelAction(deselect, resourceTake, resourceReceive);
    }

    private Action resourceReceive(Item item, ItemProperty resourceProperty)
    {
        ActionModifier modifier = new DeltaModifier(1f, DeltaType.PerSecond);
        ActionDuration duration = new TimeDuration(10f);
        return new ModifyAction(item, resourceProperty, modifier, duration);
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

    private Action resourceTakeAnimated(Item item, ItemProperty resourceProperty, Identifier animation)
    {
        Action animateBefore = animateActionProvider.get(item, animation);
        Action gather = resourceTake(item, resourceProperty);
        Action animateAfter = animateActionProvider.get(item, new Identifier("Idle"));
        return new SequenceAction(animateBefore, gather, animateAfter);
    }
}
