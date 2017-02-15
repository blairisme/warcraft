package com.evilbird.warcraft.action;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.evilbird.engine.action.ActionFactory;
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
import com.evilbird.engine.utility.Identifier;

import java.util.Arrays;
import java.util.Objects;

import javax.inject.Inject;

import static com.evilbird.engine.item.ItemUtils.findById;
import static com.evilbird.engine.item.ItemUtils.findByType;

public class WarcraftActionFactory implements ActionFactory
{
    private static final Identifier SELECT_ACTION = new Identifier("Select");
    private static final Identifier MOVE_ACTION = new Identifier("Move");
    private static final Identifier PAN_ACTION = new Identifier("Pan");
    private static final Identifier ZOOM_ACTION = new Identifier("Zoom");
    private static final Identifier GATHER_GOLD_ACTION = new Identifier("GatherGold");
    private static final Identifier GATHER_WOOD_ACTION = new Identifier("GatherWood");
    private static final Identifier BUILD_FARM_ACTION = new Identifier("BuildFarm");
    private static final Identifier BUILD_BARRACKS_ACTION = new Identifier("BuildBarracks");
    private static final Identifier ATTACK_ACTION = new Identifier("Attack");

    private ItemFactory itemFactory;

    @Inject
    public WarcraftActionFactory(ItemFactory itemFactory)
    {
        this.itemFactory = itemFactory;
    }

    public void load()
    {
    }

    public Action newAction(Identifier action, Actor actor, Object value)
    {
        if (Objects.equals(action, SELECT_ACTION)) return select(actor, (Boolean)value);
        if (Objects.equals(action, MOVE_ACTION)) return move(actor, (Vector2)value);
        if (Objects.equals(action, PAN_ACTION)) return pan(actor, (Vector2)value);
        if (Objects.equals(action, ZOOM_ACTION)) return zoom(actor, (UserInput)value);
        if (Objects.equals(action, GATHER_GOLD_ACTION)) return gatherGold(actor, (Actor)value);
        if (Objects.equals(action, GATHER_WOOD_ACTION)) return gatherWood(actor, (Actor)value);
        if (Objects.equals(action, BUILD_FARM_ACTION)) return buildFarm(actor, (Vector2)value);
        if (Objects.equals(action, BUILD_BARRACKS_ACTION)) return buildBarracks(actor, (Vector2)value);
        if (Objects.equals(action, ATTACK_ACTION)) return attack(actor, (Actor)value);
        throw new IllegalArgumentException();
    }

    private Action setAnimation(Actor actor, Identifier animation)
    {
        Identifier property = new Identifier("Animation");
        ActionModifier modifier = new ConstantModifier(animation);
        ActionDuration duration = new InstantDuration();
        return new com.evilbird.engine.action.ModifyAction(actor, property, modifier, duration);
    }

    private Action setAnimation(Stage stage, Identifier item, Identifier animation)
    {
        ActionValue value = new ItemReferenceValue(stage, item, new Identifier("Animation"));
        ActionModifier modifier = new ConstantModifier(animation);
        ActionDuration duration = new InstantDuration();
        return new com.evilbird.engine.action.ModifyAction(value, modifier, duration);
    }

    private Action setAnimation(Actor actor, Identifier animation, float time)
    {
        Action animate = setAnimation(actor, animation);
        Action wait = wait(time);
        return new com.evilbird.engine.action.ParallelAction(animate, wait);
    }

    private Action wait(float time)
    {
        ActionValue value = new TransientValue();
        ActionModifier modifier = new PassiveModifier();
        ActionDuration duration = new TimeDuration(time);
        return new com.evilbird.engine.action.ModifyAction(value, modifier, duration);
    }

    private Action moveAction(Actor actor, Vector2 destination)
    {
        Identifier position = new Identifier("Position");
        ActionModifier modifier = new MoveModifier(destination, 64f);
        ActionDuration duration = new PredicateDuration(actor, position, destination);
        return new com.evilbird.engine.action.ModifyAction(actor, position, modifier, duration);
    }

    private Action move(Actor actor, Vector2 destination)
    {
        Action animateMove = setAnimation(actor, new Identifier("Move"));
        Action move = moveAction(actor, destination);
        Action animateIdle = setAnimation(actor, new Identifier("Idle"));
        return new com.evilbird.engine.action.SequenceAction(Arrays.asList(animateMove, move, animateIdle));
    }

    private Action move(Actor actor, Actor destination)
    {
        return move(actor, new Vector2(destination.getX(), destination.getY()));
    }



    private Action pan(Actor actor, Vector2 delta)
    {
        Identifier property = new Identifier("Position");
        ActionModifier modifier = getPanModifier(actor, delta);
        ActionDuration duration = new InstantDuration();
        return new com.evilbird.engine.action.ModifyAction(actor, property, modifier, duration);
    }

    private ActionModifier getPanModifier(Actor actor, Vector2 delta)
    {
        OrthographicCamera camera = (OrthographicCamera)actor.getStage().getCamera();

        float mapWidth = 1024; //TODO
        float mapHeight = 1024; //TODO

        float viewportWidth = camera.viewportWidth * camera.zoom;
        float viewportHeight = camera.viewportHeight * camera.zoom;

        Vector2 lowerBound = null;
        Vector2 upperBound = null;

        if (mapWidth >= viewportWidth && mapHeight >= viewportHeight)
        {
            float viewportHalfWidth = viewportWidth * .5f;
            float viewportHalfHeight = viewportHeight * .5f;

            lowerBound = new Vector2(viewportHalfWidth, viewportHalfHeight);
            upperBound = new Vector2(mapWidth - viewportHalfWidth, mapHeight - viewportHalfHeight);
        }
        return new DeltaModifier(delta, DeltaType.PerUpdate, lowerBound, upperBound);
    }

    private Action zoom(Actor actor, UserInput input)
    {
        if (input.getCount() == 1)
        {
            Action storeZoom = storeZoom(actor);
            Action updateZoom = updateZoom(actor, input);
            return new com.evilbird.engine.action.CompositeAction(storeZoom, updateZoom);
        }
        else
        {
            Action resetZoom = resetZoom(actor);
            Action updateZoom = updateZoom(actor, input);
            return new com.evilbird.engine.action.CompositeAction(resetZoom, updateZoom);
        }
    }

    private Action storeZoom(Actor actor)
    {
        ActionValue value = new ItemValue((Item)actor, new Identifier("OriginalZoom"));
        ActionModifier modifier = new ConstantModifier(new ItemValue((Item)actor, new Identifier("Zoom")));
        ActionDuration duration = new InstantDuration();
        return new com.evilbird.engine.action.ModifyAction(value, modifier, duration);
    }

    private Action resetZoom(Actor actor)
    {
        ActionValue value = new ItemValue((Item)actor, new Identifier("Zoom"));
        ActionModifier modifier = new ConstantModifier(new ItemValue((Item)actor, new Identifier("OriginalZoom")));
        ActionDuration duration = new InstantDuration();
        return new com.evilbird.engine.action.ModifyAction(value, modifier, duration);
    }

    private Action updateZoom(Actor actor, UserInput input)
    {
        ActionValue zoom = new ItemValue((Item)actor, new Identifier("Zoom"));
        ActionModifier modifier = new ScaleModifier(input.getDelta().x, 0.25f, 1.5f);
        ActionDuration duration = new InstantDuration();
        return new com.evilbird.engine.action.ModifyAction(zoom, modifier, duration);
    }

    private Action select(Actor actor, boolean selected)
    {
        Action result = setSelected(actor, selected);
        if (selected)
        {
            Action sound = playSound(actor, new Identifier("Selected"));
            result = new com.evilbird.engine.action.ParallelAction(result, sound);
        }
        return result;
    }

    private Action setSelected(Actor actor, boolean selected)
    {
        Identifier property = new Identifier("Selected");
        ActionModifier modifier = new ConstantModifier(selected);
        ActionDuration duration = new InstantDuration();
        return new com.evilbird.engine.action.ModifyAction(actor, property, modifier, duration);
    }

    private Action playSound(Actor actor, Identifier sound)
    {
        Identifier property = new Identifier("Sound");
        ActionModifier modifier = new ConstantModifier(sound);
        ActionDuration duration = new InstantDuration();
        return new com.evilbird.engine.action.ModifyAction(actor, property, modifier, duration);
    }

    private Action resourceReceive(Actor actor, Identifier resource)
    {
        ActionModifier modifier = new DeltaModifier(1f, DeltaType.PerSecond);
        ActionDuration duration = new TimeDuration(10f);
        return new com.evilbird.engine.action.ModifyAction(actor, resource, modifier, duration);
    }

    private Action resourceReceiveAnimated(Actor actor, Identifier resource, Identifier animation)
    {
        Action animateBefore = setAnimation(actor, animation);
        Action gather = resourceReceive(actor, resource);
        Action animateAfter = setAnimation(actor, new Identifier("Idle"));
        return new com.evilbird.engine.action.SequenceAction(animateBefore, gather, animateAfter);
    }

    private Action resourceTake(Actor actor, Identifier resource)
    {
        ActionModifier modifier = new DeltaModifier(-10f, DeltaType.PerSecond);
        ActionDuration duration = new TimeDuration(10f);
        return new com.evilbird.engine.action.ModifyAction(actor, resource, modifier, duration);
    }

    private Action resourceTakeAnimated(Actor actor, Identifier resource, Identifier animation)
    {
        Action animateBefore = setAnimation(actor, animation);
        Action gather = resourceTake(actor, resource);
        Action animateAfter = setAnimation(actor, new Identifier("Idle"));
        return new com.evilbird.engine.action.SequenceAction(animateBefore, gather, animateAfter);
    }

    private Action resourceTransfer(Actor source, Actor destination, Identifier resource, Identifier takeAnimation, Identifier receiveAnimation)
    {
        Action deselect = setSelected(source, false);
        Action resourceTake = resourceTakeAnimated(source, resource, takeAnimation);
        Action resourceReceive = resourceReceiveAnimated(destination, resource, receiveAnimation);
        return new com.evilbird.engine.action.ParallelAction(deselect, resourceTake, resourceReceive);
    }

    private Action gather(Actor gatherer, Actor resource, Actor player, Actor depot, Identifier property, Identifier gatherAnimation, Identifier depositAnimation)
    {
        Action moveToResource = move(gatherer, resource);
        Action transfer = resourceTransfer(resource, gatherer, property, gatherAnimation, gatherAnimation);
        Action moveToDepot = move(gatherer, depot);
        Action deposit = resourceTransfer(gatherer, player, property, depositAnimation, depositAnimation);
        Action gather = new com.evilbird.engine.action.SequenceAction(moveToResource, transfer, moveToDepot, deposit);
        return new RepeatedAction(gather);
    }

    private Action gatherGold(Actor actor, Actor resource)
    {
        Actor depot = findByType(actor.getStage(), new Identifier("TownHall"));
        Actor player = findById(actor.getStage(), new Identifier("Player1")); //TODO
        Identifier property = new Identifier("Gold");
        Identifier gatherAnimation = new Identifier("GatherGold");
        Identifier depositAnimation = new Identifier("DepositGold");
        return gather(actor, resource, player, depot, property, gatherAnimation, depositAnimation);
    }

    private Action gatherWood(Actor actor, Actor resource)
    {
        Actor depot = findByType(actor.getStage(), new Identifier("TownHall"));
        Actor player = findById(actor.getStage(), new Identifier("Player1")); //TODO
        Identifier property = new Identifier("Wood");
        Identifier gatherAnimation = new Identifier("GatherWood");
        Identifier depositAnimation = new Identifier("DepositWood");
        return gather(actor, resource, player, depot, property, gatherAnimation, depositAnimation);
    }

    private Action create(Stage stage, Identifier type, Identifier id, Vector2 position)
    {
        return new CreateAction(stage, type, itemFactory, id, position);
    }

    private Action setEnabled(Stage stage, Identifier actor, boolean enabled)
    {
        ActionValue value = new ItemReferenceValue(stage, actor, new Identifier("Enabled"));
        ActionModifier modifier = new ConstantModifier(enabled);
        ActionDuration duration = new InstantDuration();
        return new com.evilbird.engine.action.ModifyAction(value, modifier, duration);
    }

    private Action setEnabled(Actor actor, boolean enabled)
    {
        ActionValue value = new ItemValue((Item)actor, new Identifier("Enabled"));
        ActionModifier modifier = new ConstantModifier(enabled);
        ActionDuration duration = new InstantDuration();
        return new com.evilbird.engine.action.ModifyAction(value, modifier, duration);
    }

    private Action build(Actor builder, Identifier building, Stage stage)
    {
        Action soundBefore = playSound(builder, new Identifier("Construct"));
        Action animateBuilderBefore = setAnimation(builder, new Identifier("Build"));
        Action animateBuildingBefore = setAnimation(stage, building, new Identifier("Construct"));
        Action before = new com.evilbird.engine.action.ParallelAction(animateBuilderBefore, animateBuildingBefore, soundBefore);

        ActionValue value = new ItemReferenceValue(stage, building, new Identifier("Completion"));
        ActionModifier modifier = new DeltaModifier(100f, DeltaType.PerSecond);
        ActionDuration duration = new TimeDuration(10f);
        Action build = new com.evilbird.engine.action.ModifyAction(value, modifier, duration);

        Action soundAfter = playSound(builder, new Identifier("Complete"));
        Action animateBuilderAfter = setAnimation(builder, new Identifier("Idle"));
        Action animateBuildingAfter = setAnimation(stage, building, new Identifier("Idle"));
        Action after = new com.evilbird.engine.action.ParallelAction(animateBuilderAfter, animateBuildingAfter, soundAfter);

        return new com.evilbird.engine.action.SequenceAction(before, build, after);
    }

    private Action build(Actor builder, Identifier type, Vector2 location)
    {
        Stage stage = builder.getStage();
        Identifier building = new Identifier();

        Action acknowledge = playSound(builder, new Identifier("Acknowledge"));
        Action moveToSite = move(builder, location);
        Action deselectBuilder = setSelected(builder, false);
        Action createFarm = create(stage, type, building, location);
        Action disableFarm = setEnabled(stage, building, false);
        Action buildFarm = build(builder, building, stage);
        Action enableFarm = setEnabled(stage, building, true);
        Action selectBuilder = setSelected(builder, true);

        return new com.evilbird.engine.action.SequenceAction(acknowledge, moveToSite, deselectBuilder, createFarm, disableFarm, buildFarm, enableFarm, selectBuilder);
    }

    private Action buildFarm(Actor builder, Vector2 location)
    {
        return build(builder, new Identifier("Farm"), location);
    }

    private Action buildBarracks(Actor builder, Vector2 location)
    {
        return build(builder, new Identifier("Barracks"), location);
    }

    private Action reduceHealth(Actor target)
    {
        Identifier health = new Identifier("Health");
        ActionValue value = new ItemValue((Item)target, health);
        ActionModifier modifier = new DeltaModifier(-10f, DeltaType.PerSecond, 0f, 100f);
        ActionDuration duration = new PredicateDuration(target, health, 0f);
        return new ModifyAction(value, modifier, duration);
    }

    private Action attack(Actor attacker, Actor target)
    {
        Action move = move(attacker, target);

        Action attackAnimation = setAnimation(attacker, new Identifier("Attack"));
        Action reduceHealth = reduceHealth(target);
        Action attack = new com.evilbird.engine.action.ParallelAction(attackAnimation, reduceHealth);

        Action deadAnimation = setAnimation(target, new Identifier("Die"), 0.5f);
        Action deselect = setSelected(target, false);
        Action disable = setEnabled(target, false);
        Action idleAnimation = setAnimation(attacker, new Identifier("Idle"));
        Action die = new ParallelAction(deadAnimation, deselect, disable, idleAnimation);

        Action decompose = setAnimation(target, new Identifier("Decompose"), 10f);
        Action remove = new RemoveAction(target.getStage(), target);
        Action clean = new com.evilbird.engine.action.SequenceAction(decompose, remove);

        return new SequenceAction(move, attack, die, clean);
    }
}
