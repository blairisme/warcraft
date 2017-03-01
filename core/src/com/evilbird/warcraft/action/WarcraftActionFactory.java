package com.evilbird.warcraft.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ActionContext;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.CreateAction;
import com.evilbird.engine.action.ModifyAction;
import com.evilbird.engine.action.ParallelAction;
import com.evilbird.engine.action.RemoveAction;
import com.evilbird.engine.action.RepeatedAction;
import com.evilbird.engine.action.SequenceAction;
import com.evilbird.engine.action.duration.ActionDuration;
import com.evilbird.engine.action.duration.InstantDuration;
import com.evilbird.engine.action.duration.PredicateDuration;
import com.evilbird.engine.action.duration.TimeDuration;
import com.evilbird.engine.action.modifier.ActionModifier;
import com.evilbird.engine.action.modifier.ConstantModifier;
import com.evilbird.engine.action.modifier.DeltaModifier;
import com.evilbird.engine.action.modifier.DeltaType;
import com.evilbird.engine.action.modifier.MoveModifier;
import com.evilbird.engine.action.modifier.PassiveModifier;
import com.evilbird.engine.action.modifier.ScaleModifier;
import com.evilbird.engine.action.value.ActionValue;
import com.evilbird.engine.action.value.ItemReferenceValue;
import com.evilbird.engine.action.value.ItemValue;
import com.evilbird.engine.action.value.TransientValue;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemIdentifier;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.utility.Identifier;
import com.evilbird.warcraft.item.unit.UnitType;

import java.util.Arrays;
import java.util.Objects;

import javax.inject.Inject;

import static com.evilbird.engine.item.ItemPredicates.itemWithId;

public class WarcraftActionFactory implements ActionFactory
{
    private ItemFactory itemFactory;

    @Inject
    public WarcraftActionFactory(ItemFactory itemFactory)
    {
        this.itemFactory = itemFactory;
    }

    @Override
    public void load()
    {
    }

    @Override
    public Action newAction(ActionContext context)
    {
        return newAction(context.getAction(), context.getTarget(), context.getData());
    }

    @Override
    public Action newAction(ActionIdentifier action, Item item, Object value)
    {
        if (Objects.equals(action, Actions.Select)) return select(item, (Boolean)value);
        if (Objects.equals(action, Actions.Move)) return move(item, (Vector2)value);
        if (Objects.equals(action, Actions.Pan)) return pan(item, (Vector2)value);
        if (Objects.equals(action, Actions.Zoom)) return zoom(item, (UserInput)value);
        if (Objects.equals(action, Actions.GatherGold)) return gatherGold(item, (Item)value);
        if (Objects.equals(action, Actions.GatherWood)) return gatherWood(item, (Item)value);
        if (Objects.equals(action, Actions.BuildFarm)) return buildFarm(item, (Vector2)value);
        if (Objects.equals(action, Actions.BuildBarracks)) return buildBarracks(item, (Vector2)value);
        if (Objects.equals(action, Actions.Attack)) return attack(item, (Item)value);
        throw new IllegalArgumentException();
    }

    private Action setAnimation(Item item, Identifier animation)
    {
        Identifier property = new Identifier("Animation");
        ActionModifier modifier = new ConstantModifier(animation);
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(item, property, modifier, duration);
    }

    private Action setAnimation(ItemRoot stage, Identifier item, Identifier animation)
    {
        ActionValue value = new ItemReferenceValue(stage, item, new Identifier("Animation"));
        ActionModifier modifier = new ConstantModifier(animation);
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(value, modifier, duration);
    }

    private Action setAnimation(Item item, Identifier animation, float time)
    {
        Action animate = setAnimation(item, animation);
        Action wait = wait(time);
        return new ParallelAction(animate, wait);
    }

    private Action wait(float time)
    {
        ActionValue value = new TransientValue();
        ActionModifier modifier = new PassiveModifier();
        ActionDuration duration = new TimeDuration(time);
        return new ModifyAction(value, modifier, duration);
    }

    private Action moveAction(Item item, Vector2 destination)
    {
        Identifier position = new Identifier("Position");
        ActionModifier modifier = new MoveModifier(destination, 64f);
        ActionDuration duration = new PredicateDuration((Item)item, position, destination);
        return new ModifyAction(item, position, modifier, duration);
    }

    private Action move(Item item, Vector2 destination)
    {
        Action animateMove = setAnimation(item, new Identifier("Move"));
        Action move = moveAction(item, destination);
        Action animateIdle = setAnimation(item, new Identifier("Idle"));
        return new com.evilbird.engine.action.SequenceAction(Arrays.asList(animateMove, move, animateIdle));
    }

    private Action move(Item item, Item destination)
    {
        return move(item, destination.getPosition());
    }



    private Action pan(Item item, Vector2 delta)
    {
        Identifier property = new Identifier("Position");
        ActionModifier modifier = getPanModifier(item, delta);
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(item, property, modifier, duration);
    }

    private ActionModifier getPanModifier(Item item, Vector2 delta)
    {
        //OrthographicCamera camera = (OrthographicCamera)item.getStage().getCamera();

        //float mapWidth = 1024; //TODO
        //float mapHeight = 1024; //TODO

        //float viewportWidth = camera.viewportWidth * camera.zoom;
        //float viewportHeight = camera.viewportHeight * camera.zoom;

        Vector2 lowerBound = null;
        Vector2 upperBound = null;
/*
        if (mapWidth >= viewportWidth && mapHeight >= viewportHeight)
        {
            float viewportHalfWidth = viewportWidth * .5f;
            float viewportHalfHeight = viewportHeight * .5f;

            lowerBound = new Vector2(viewportHalfWidth, viewportHalfHeight);
            upperBound = new Vector2(mapWidth - viewportHalfWidth, mapHeight - viewportHalfHeight);
        }
*/
        return new DeltaModifier(delta, DeltaType.PerUpdate, lowerBound, upperBound);
    }

    private Action zoom(Item item, UserInput input)
    {
        if (input.getCount() == 1)
        {
            Action storeZoom = storeZoom(item);
            Action updateZoom = updateZoom(item, input);
            return new com.evilbird.engine.action.CompositeAction(storeZoom, updateZoom);
        }
        else
        {
            Action resetZoom = resetZoom(item);
            Action updateZoom = updateZoom(item, input);
            return new com.evilbird.engine.action.CompositeAction(resetZoom, updateZoom);
        }
    }

    private Action storeZoom(Item item)
    {
        ActionValue value = new ItemValue((Item)item, new Identifier("OriginalZoom"));
        ActionModifier modifier = new ConstantModifier(new ItemValue((Item)item, new Identifier("Zoom")));
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(value, modifier, duration);
    }

    private Action resetZoom(Item item)
    {
        ActionValue value = new ItemValue((Item)item, new Identifier("Zoom"));
        ActionModifier modifier = new ConstantModifier(new ItemValue((Item)item, new Identifier("OriginalZoom")));
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(value, modifier, duration);
    }

    private Action updateZoom(Item item, UserInput input)
    {
        ActionValue zoom = new ItemValue((Item)item, new Identifier("Zoom"));
        ActionModifier modifier = new ScaleModifier(input.getDelta().x, 0.25f, 1.5f);
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(zoom, modifier, duration);
    }

    private Action select(Item item, boolean selected)
    {
        Action result = setSelected(item, selected);
        if (selected)
        {
            Action sound = playSound(item, new Identifier("Selected"));
            result = new com.evilbird.engine.action.ParallelAction(result, sound);
        }
        return result;
    }

    private Action setSelected(Item item, boolean selected)
    {
        Identifier property = new Identifier("Selected");
        ActionModifier modifier = new ConstantModifier(selected);
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(item, property, modifier, duration);
    }

    private Action playSound(Item item, Identifier sound)
    {
        Identifier property = new Identifier("Sound");
        ActionModifier modifier = new ConstantModifier(sound);
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(item, property, modifier, duration);
    }

    private Action resourceReceive(Item item, Identifier resource)
    {
        ActionModifier modifier = new DeltaModifier(1f, DeltaType.PerSecond);
        ActionDuration duration = new TimeDuration(10f);
        return new ModifyAction(item, resource, modifier, duration);
    }

    private Action resourceReceiveAnimated(Item item, Identifier resource, Identifier animation)
    {
        Action animateBefore = setAnimation(item, animation);
        Action gather = resourceReceive(item, resource);
        Action animateAfter = setAnimation(item, new Identifier("Idle"));
        return new com.evilbird.engine.action.SequenceAction(animateBefore, gather, animateAfter);
    }

    private Action resourceTake(Item item, Identifier resource)
    {
        ActionModifier modifier = new DeltaModifier(-10f, DeltaType.PerSecond);
        ActionDuration duration = new TimeDuration(10f);
        return new ModifyAction(item, resource, modifier, duration);
    }

    private Action resourceTakeAnimated(Item item, Identifier resource, Identifier animation)
    {
        Action animateBefore = setAnimation(item, animation);
        Action gather = resourceTake(item, resource);
        Action animateAfter = setAnimation(item, new Identifier("Idle"));
        return new com.evilbird.engine.action.SequenceAction(animateBefore, gather, animateAfter);
    }

    private Action resourceTransfer(Item source, Item destination, Identifier resource, Identifier takeAnimation, Identifier receiveAnimation)
    {
        Action deselect = setSelected(source, false);
        Action resourceTake = resourceTakeAnimated(source, resource, takeAnimation);
        Action resourceReceive = resourceReceiveAnimated(destination, resource, receiveAnimation);
        return new com.evilbird.engine.action.ParallelAction(deselect, resourceTake, resourceReceive);
    }

    private Action gather(Item gatherer, Item resource, Item player, Item depot, Identifier property, Identifier gatherAnimation, Identifier depositAnimation)
    {
        Action moveToResource = move(gatherer, resource);
        Action transfer = resourceTransfer(resource, gatherer, property, gatherAnimation, gatherAnimation);
        Action moveToDepot = move(gatherer, depot);
        Action deposit = resourceTransfer(gatherer, player, property, depositAnimation, depositAnimation);
        Action gather = new com.evilbird.engine.action.SequenceAction(moveToResource, transfer, moveToDepot, deposit);
        return new RepeatedAction(gather);
    }

    private Action gatherGold(Item item, Item resource)
    {
        ItemRoot root = item.getRoot();
        Item depot = root.find(itemWithId(new Identifier("TownHall1"))); //TODO
        Item player = root.find(itemWithId(new Identifier("Player1"))); //TODO
        Identifier property = new Identifier("Gold");
        Identifier gatherAnimation = new Identifier("GatherGold");
        Identifier depositAnimation = new Identifier("DepositGold");
        return gather(item, resource, player, depot, property, gatherAnimation, depositAnimation);
    }

    private Action gatherWood(Item item, Item resource)
    {
        ItemRoot root = item.getRoot();
        Item depot = root.find(itemWithId(new Identifier("TownHall1"))); //TODO
        Item player = root.find(itemWithId(new Identifier("Player1"))); //TODO
        Identifier property = new Identifier("Wood");
        Identifier gatherAnimation = new Identifier("GatherWood");
        Identifier depositAnimation = new Identifier("DepositWood");
        return gather(item, resource, player, depot, property, gatherAnimation, depositAnimation);
    }

    private Action create(ItemRoot stage, ItemIdentifier type, Identifier id, Vector2 position)
    {
        return new CreateAction(stage, type, itemFactory, id, position);
    }

    private Action setEnabled(ItemRoot stage, Identifier item, boolean enabled)
    {
        ActionValue value = new ItemReferenceValue(stage, item, new Identifier("Enabled"));
        ActionModifier modifier = new ConstantModifier(enabled);
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(value, modifier, duration);
    }

    private Action setEnabled(Item item, boolean enabled)
    {
        ActionValue value = new ItemValue(item, new Identifier("Enabled"));
        ActionModifier modifier = new ConstantModifier(enabled);
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(value, modifier, duration);
    }

    private Action build(Item builder, Identifier building, ItemRoot stage)
    {
        Action soundBefore = playSound(builder, new Identifier("Construct"));
        Action animateBuilderBefore = setAnimation(builder, new Identifier("Build"));
        Action animateBuildingBefore = setAnimation(stage, building, new Identifier("Construct"));
        Action before = new ParallelAction(animateBuilderBefore, animateBuildingBefore, soundBefore);

        ActionValue value = new ItemReferenceValue(stage, building, new Identifier("Completion"));
        ActionModifier modifier = new DeltaModifier(100f, DeltaType.PerSecond);
        ActionDuration duration = new TimeDuration(10f);
        Action build = new ModifyAction(value, modifier, duration);

        Action soundAfter = playSound(builder, new Identifier("Complete"));
        Action animateBuilderAfter = setAnimation(builder, new Identifier("Idle"));
        Action animateBuildingAfter = setAnimation(stage, building, new Identifier("Idle"));
        Action after = new ParallelAction(animateBuilderAfter, animateBuildingAfter, soundAfter);

        return new SequenceAction(before, build, after);
    }

    private Action build(Item builder, ItemIdentifier type, Vector2 location)
    {
        ItemRoot itemRoot = builder.getRoot();
        Identifier building = new Identifier();

        Action acknowledge = playSound(builder, new Identifier("Acknowledge"));
        Action moveToSite = move(builder, location);
        Action deselectBuilder = setSelected(builder, false);
        Action createFarm = create(itemRoot, type, building, location);
        Action disableFarm = setEnabled(itemRoot, building, false);
        Action buildFarm = build(builder, building, itemRoot);
        Action enableFarm = setEnabled(itemRoot, building, true);
        Action selectBuilder = setSelected(builder, true);

        return new SequenceAction(acknowledge, moveToSite, deselectBuilder, createFarm, disableFarm, buildFarm, enableFarm, selectBuilder);
    }

    private Action buildFarm(Item builder, Vector2 location)
    {
        return build(builder, UnitType.Farm, location);
    }

    private Action buildBarracks(Item builder, Vector2 location)
    {
        return build(builder, UnitType.Barracks, location);
    }

    private Action reduceHealth(Item target)
    {
        Identifier health = new Identifier("Health");
        ActionValue value = new ItemValue(target, health);
        ActionModifier modifier = new DeltaModifier(-10f, DeltaType.PerSecond, 0f, 100f);
        ActionDuration duration = new PredicateDuration(target, health, 0f);
        return new ModifyAction(value, modifier, duration);
    }

    private Action attack(Item attacker, Item target)
    {
        Action move = move(attacker, target);

        Action attackAnimation = setAnimation(attacker, new Identifier("Attack"));
        Action reduceHealth = reduceHealth(target);
        Action attack = new ParallelAction(attackAnimation, reduceHealth);

        Action deadAnimation = setAnimation(target, new Identifier("Die"), 0.5f);
        Action deselect = setSelected(target, false);
        Action disable = setEnabled(target, false);
        Action idleAnimation = setAnimation(attacker, new Identifier("Idle"));
        Action die = new ParallelAction(deadAnimation, deselect, disable, idleAnimation);

        Action decompose = setAnimation(target, new Identifier("Decompose"), 10f);
        Action remove = new RemoveAction(target.getRoot(), target);
        Action clean = new SequenceAction(decompose, remove);

        return new SequenceAction(move, attack, die, clean);
    }
}
