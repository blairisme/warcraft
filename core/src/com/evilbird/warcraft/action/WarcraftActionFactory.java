package com.evilbird.warcraft.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.ClearAction;
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
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemIdentifier;
import com.evilbird.engine.item.ItemProperties;
import com.evilbird.engine.item.ItemProperty;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.control.AnimationProperties;
import com.evilbird.warcraft.item.data.CameraProperties;
import com.evilbird.warcraft.item.unit.UnitProperties;
import com.evilbird.warcraft.item.unit.UnitType;

import java.util.Arrays;

import javax.inject.Inject;

import static com.evilbird.engine.item.ItemPredicates.itemWithId;

//TODO: Split actions into separate classes
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
    //public Action newAction(ActionIdentifier action, Item item, Object value)
    public Action newAction(ActionIdentifier action, Item item, Item target, UserInput input)
    {
        if (Objects.equals(action, ActionType.Select)) return toggleSelection(item);
        if (Objects.equals(action, ActionType.Move)) return move(item, input);
        if (Objects.equals(action, ActionType.Pan)) return pan(item, input);
        if (Objects.equals(action, ActionType.Zoom)) return zoom(item, input);
        if (Objects.equals(action, ActionType.GatherGold)) return gatherGold(item, target);
        if (Objects.equals(action, ActionType.GatherWood)) return gatherWood(item, target);
        //if (Objects.equals(action, ActionType.BuildFarm)) return buildFarm(item, (Vector2)value);
        //if (Objects.equals(action, ActionType.BuildBarracks)) return buildBarracks(item, (Vector2)value);
        if (Objects.equals(action, ActionType.Attack)) return attack(item, target);
        if (Objects.equals(action, ActionType.Stop)) return stop(item);

        if (Objects.equals(action, ActionType.CreateBarracks)) return createBarracks(target, item);
        if (Objects.equals(action, ActionType.CreateBarracksPrototype)) return createBarracksPrototype(item);

        if (Objects.equals(action, ActionType.Drag)) return drag(item, input);

        throw new IllegalArgumentException(action.toString());
    }

    private Action setAnimation(Item item, Identifier animation)
    {
        ItemProperty property = AnimationProperties.Animation;
        ActionModifier modifier = new ConstantModifier(animation);
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(item, property, modifier, duration);
    }

    private Action setAnimation(ItemRoot stage, Identifier item, Identifier animation)
    {
        ActionValue value = new ItemReferenceValue(stage, item, AnimationProperties.Animation);
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
        ItemProperty position = ItemProperties.Position;
        ActionModifier modifier = new MoveModifier(destination, 64f);
        ActionDuration duration = new PredicateDuration(item, position, destination);
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

    private Action move(Item item, UserInput input)
    {
        ItemRoot root = item.getRoot();
        Vector2 position = root.unproject(input.getPosition());
        return move(item, position);
    }


    private Action pan(Item item, UserInput input)
    {
        ItemProperty position = ItemProperties.Position;
        ActionModifier modifier = getPanModifier(item, input.getDelta());
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(item, position, modifier, duration);
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
        ActionValue value = new ItemValue(item, CameraProperties.OriginalZoom);
        ActionModifier modifier = new ConstantModifier(new ItemValue(item, CameraProperties.Zoom));
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(value, modifier, duration);
    }

    private Action resetZoom(Item item)
    {
        ActionValue value = new ItemValue(item, CameraProperties.Zoom);
        ActionModifier modifier = new ConstantModifier(new ItemValue(item, CameraProperties.OriginalZoom));
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(value, modifier, duration);
    }

    private Action updateZoom(Item item, UserInput input)
    {
        ActionValue zoom = new ItemValue(item, CameraProperties.Zoom);
        ActionModifier modifier = new ScaleModifier(input.getDelta().x, 0.25f, 1.5f);
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(zoom, modifier, duration);
    }

    private Action toggleSelection(Item item)
    {
        return select(item, !item.getSelected());
    }

    private Action select(Item item, boolean selected)
    {
        Action result = setSelected(item, selected);
        if (selected)
        {
            Action sound = playSound(item, new Identifier("Selected"));
            result = new ParallelAction(result, sound);
        }
        return result;
    }

    private Action setSelected(Item item, boolean selected)
    {
        ItemProperty property = ItemProperties.Selected;
        ActionModifier modifier = new ConstantModifier(selected);
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(item, property, modifier, duration);
    }

    private Action playSound(Item item, Identifier sound)
    {
        ItemProperty property = AnimationProperties.Sound;
        ActionModifier modifier = new ConstantModifier(sound);
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(item, property, modifier, duration);
    }

    private Action resourceReceive(Item item, ItemProperty resourceProperty)
    {
        ActionModifier modifier = new DeltaModifier(1f, DeltaType.PerSecond);
        ActionDuration duration = new TimeDuration(10f);
        return new ModifyAction(item, resourceProperty, modifier, duration);
    }

    private Action resourceReceiveAnimated(Item item, ItemProperty resourceProperty, Identifier animation)
    {
        Action animateBefore = setAnimation(item, animation);
        Action gather = resourceReceive(item, resourceProperty);
        Action animateAfter = setAnimation(item, new Identifier("Idle"));
        return new com.evilbird.engine.action.SequenceAction(animateBefore, gather, animateAfter);
    }

    private Action resourceTake(Item item, ItemProperty resourceProperty)
    {
        ActionModifier modifier = new DeltaModifier(-10f, DeltaType.PerSecond);
        ActionDuration duration = new TimeDuration(10f);
        return new ModifyAction(item, resourceProperty, modifier, duration);
    }

    private Action resourceTakeAnimated(Item item, ItemProperty resourceProperty, Identifier animation)
    {
        Action animateBefore = setAnimation(item, animation);
        Action gather = resourceTake(item, resourceProperty);
        Action animateAfter = setAnimation(item, new Identifier("Idle"));
        return new com.evilbird.engine.action.SequenceAction(animateBefore, gather, animateAfter);
    }

    private Action resourceTransfer(Item source, Item destination, ItemProperty resource, Identifier takeAnimation, Identifier receiveAnimation)
    {
        Action deselect = setSelected(source, false);
        Action resourceTake = resourceTakeAnimated(source, resource, takeAnimation);
        Action resourceReceive = resourceReceiveAnimated(destination, resource, receiveAnimation);
        return new com.evilbird.engine.action.ParallelAction(deselect, resourceTake, resourceReceive);
    }

    private Action gather(Item gatherer, Item resource, Item player, Item depot, ItemProperty property, Identifier gatherAnimation, Identifier depositAnimation)
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
        ItemProperty property = UnitProperties.Gold;
        Identifier gatherAnimation = new Identifier("GatherGold");
        Identifier depositAnimation = new Identifier("DepositGold");
        return gather(item, resource, player, depot, property, gatherAnimation, depositAnimation);
    }

    private Action gatherWood(Item item, Item resource)
    {
        ItemRoot root = item.getRoot();
        Item depot = root.find(itemWithId(new Identifier("TownHall1"))); //TODO
        Item player = root.find(itemWithId(new Identifier("Player1"))); //TODO
        ItemProperty property = UnitProperties.Wood;
        Identifier gatherAnimation = new Identifier("GatherWood");
        Identifier depositAnimation = new Identifier("DepositWood");
        return gather(item, resource, player, depot, property, gatherAnimation, depositAnimation);
    }

    private Action create(ItemRoot stage, ItemIdentifier type, Identifier id, Vector2 position)
    {
        return new CreateAction(stage, type, itemFactory, id, position);
    }

    /*
    private Action setTouchable(ItemRoot stage, Identifier item, Touchable touchable)
    {
        ActionValue value = new ItemReferenceValue(stage, item, new Identifier("Enabled"));
        ActionModifier modifier = new ConstantModifier(enabled);
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(value, modifier, duration);
    }
*/
    private Action setTouchable(Item item, boolean touchable)
    {
        ActionValue value = new ItemValue(item, ItemProperties.Touchable);
        ActionModifier modifier = new ConstantModifier(touchable ? Touchable.enabled : Touchable.disabled);
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(value, modifier, duration);
    }


    private Action build(Item builder, Identifier building, ItemRoot stage)
    {
        Action soundBefore = playSound(builder, new Identifier("Construct"));
        Action animateBuilderBefore = setAnimation(builder, new Identifier("Build"));
        Action animateBuildingBefore = setAnimation(stage, building, new Identifier("Construct"));
        Action before = new ParallelAction(animateBuilderBefore, animateBuildingBefore, soundBefore);

        ActionValue value = new ItemReferenceValue(stage, building, UnitProperties.Health);
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
       // Action disableFarm = setEnabled(itemRoot, building, false);
        Action buildFarm = build(builder, building, itemRoot);
       // Action enableFarm = setEnabled(itemRoot, building, true);
        Action selectBuilder = setSelected(builder, true);

        return new SequenceAction(acknowledge, moveToSite, deselectBuilder, createFarm, buildFarm, selectBuilder);
        //return new SequenceAction(acknowledge, moveToSite, deselectBuilder, createFarm, disableFarm, buildFarm, enableFarm, selectBuilder);
    }

    private Action buildFarm(Item builder, Vector2 location)
    {
        return build(builder, UnitType.Farm, location);
    }




    private Action createBarracks(Item prototype, Item peasant)
    {
        Action remove =  new RemoveAction(prototype);
        Action build = build(peasant, UnitType.Barracks, prototype.getPosition());
        return new SequenceAction(remove, build);
    }

    private Action createBarracksPrototype(Item item)
    {
        ItemRoot itemRoot = item.getRoot();
        Vector2 screenCenter = new Vector2(512, 384);
        Vector2 location = itemRoot.unproject(screenCenter);
        Action createPrototype = create(itemRoot, UnitType.BarracksPrototype, new Identifier("BarracksPrototype"), location);
        return createPrototype;
    }



    private Action reduceHealth(Item target)
    {
        ItemProperty health = UnitProperties.Health;
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
        Action disable = setTouchable(target, false);
        Action idleAnimation = setAnimation(attacker, new Identifier("Idle"));
        Action die = new ParallelAction(deadAnimation, deselect, disable, idleAnimation);

        Action decompose = setAnimation(target, new Identifier("Decompose"), 10f);
        Action remove = new RemoveAction(target.getRoot(), target);
        Action clean = new SequenceAction(decompose, remove);

        return new SequenceAction(move, attack, die, clean);
    }

    public Action stop(Item item)
    {
        Action clearAction = new ClearAction(item);
        Action idleAnimation = setAnimation(item, new Identifier("Idle"));
        return new SequenceAction(clearAction, idleAnimation);
    }

    public Action drag(Item item, UserInput input)
    {
        Vector2 inputDelta = input.getDelta();
        Vector2 dragDelta = new Vector2(inputDelta.x * -1, inputDelta.y * -1);

        ItemProperty property = ItemProperties.Position;
        ActionModifier modifier = new DeltaModifier(dragDelta, DeltaType.PerUpdate);
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(item, property, modifier, duration);
    }
}
