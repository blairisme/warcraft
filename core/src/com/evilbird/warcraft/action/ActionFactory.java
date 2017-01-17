package com.evilbird.warcraft.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.evilbird.warcraft.action.duration.ActionDuration;
import com.evilbird.warcraft.action.duration.InstantDuration;
import com.evilbird.warcraft.action.duration.PredicateDuration;
import com.evilbird.warcraft.action.duration.TimeDuration;
import com.evilbird.warcraft.action.modifier.ActionModifier;
import com.evilbird.warcraft.action.modifier.ConstantModifier;
import com.evilbird.warcraft.action.modifier.DeltaModifier;
import com.evilbird.warcraft.action.modifier.DeltaType;
import com.evilbird.warcraft.action.modifier.MoveModifier;
import com.evilbird.warcraft.action.modifier.PassiveModifier;
import com.evilbird.warcraft.action.value.ActionValue;
import com.evilbird.warcraft.action.value.ItemReferenceValue;
import com.evilbird.warcraft.action.value.ItemValue;
import com.evilbird.warcraft.action.value.TransientValue;
import com.evilbird.warcraft.item.Item;
import com.evilbird.warcraft.unit.UnitFactory;
import com.evilbird.warcraft.utility.Identifier;

import java.util.Arrays;
import java.util.Objects;

import static com.evilbird.warcraft.item.ItemUtils.findByType;

public class ActionFactory
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

    private UnitFactory unitFactory;

    public ActionFactory(UnitFactory unitFactory)
    {
        this.unitFactory = unitFactory;
    }

    public Action newAction(Identifier action, Actor actor, Object value)
    {
        if (Objects.equals(action, SELECT_ACTION)) return select(actor, (Boolean)value);
        if (Objects.equals(action, MOVE_ACTION)) return move(actor, (Vector2)value);
        if (Objects.equals(action, PAN_ACTION)) return pan(actor, value);
        if (Objects.equals(action, ZOOM_ACTION)) return zoom(actor, (Vector2)value);
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
        return new ModifyAction(actor, property, modifier, duration);
    }

    private Action setAnimation(Stage stage, Identifier item, Identifier animation)
    {
        ActionValue value = new ItemReferenceValue(stage, item, new Identifier("Animation"));
        ActionModifier modifier = new ConstantModifier(animation);
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(value, modifier, duration);
    }

    private Action setAnimation(Actor actor, Identifier animation, float time)
    {
        Action animate = setAnimation(actor, animation);
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

    private Action moveAction(Actor actor, Vector2 destination)
    {
        Identifier position = new Identifier("Position");
        ActionModifier modifier = new MoveModifier(destination, 64f);
        ActionDuration duration = new PredicateDuration(actor, position, destination);
        return new ModifyAction(actor, position, modifier, duration);
    }

    private Action move(Actor actor, Vector2 destination)
    {
        Action animateMove = setAnimation(actor, new Identifier("Move"));
        Action move = moveAction(actor, destination);
        Action animateIdle = setAnimation(actor, new Identifier("Idle"));
        return new SequenceAction(Arrays.asList(animateMove, move, animateIdle));
    }

    private Action move(Actor actor, Actor destination)
    {
        return move(actor, new Vector2(destination.getX(), destination.getY()));
    }

    private Action pan(Actor actor, Object delta)
    {
        Identifier property = new Identifier("Position");
        ActionModifier modifier = new DeltaModifier(delta, DeltaType.PerUpdate);
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(actor, property, modifier, duration);
    }

    private Action zoom(Actor actor, Vector2 delta)
    {
        Identifier property = new Identifier("Zoom");
        ActionModifier modifier = new DeltaModifier(delta.x, DeltaType.PerUpdate);
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(actor, property, modifier, duration);
    }

    private Action select(Actor actor, boolean selected)
    {
        Action result = setSelected(actor, selected);
        if (selected)
        {
            Action sound = playSound(actor, new Identifier("Selected"));
            result = new ParallelAction(result, sound);
        }
        return result;
    }

    private Action setSelected(Actor actor, boolean selected)
    {
        Identifier property = new Identifier("Selected");
        ActionModifier modifier = new ConstantModifier(selected);
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(actor, property, modifier, duration);
    }

    private Action playSound(Actor actor, Identifier sound)
    {
        Identifier property = new Identifier("Sound");
        ActionModifier modifier = new ConstantModifier(sound);
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(actor, property, modifier, duration);
    }

    private Action resourceReceive(Actor actor, Identifier resource)
    {
        ActionModifier modifier = new DeltaModifier(1f, DeltaType.PerSecond);
        ActionDuration duration = new TimeDuration(10f);
        return new ModifyAction(actor, resource, modifier, duration);
    }

    private Action resourceReceiveAnimated(Actor actor, Identifier resource, Identifier animation)
    {
        Action animateBefore = setAnimation(actor, animation);
        Action gather = resourceReceive(actor, resource);
        Action animateAfter = setAnimation(actor, new Identifier("Idle"));
        return new SequenceAction(animateBefore, gather, animateAfter);
    }

    private Action resourceTake(Actor actor, Identifier resource)
    {
        ActionModifier modifier = new DeltaModifier(-10f, DeltaType.PerSecond);
        ActionDuration duration = new TimeDuration(10f);
        return new ModifyAction(actor, resource, modifier, duration);
    }

    private Action resourceTakeAnimated(Actor actor, Identifier resource, Identifier animation)
    {
        Action animateBefore = setAnimation(actor, animation);
        Action gather = resourceTake(actor, resource);
        Action animateAfter = setAnimation(actor, new Identifier("Idle"));
        return new SequenceAction(animateBefore, gather, animateAfter);
    }

    private Action resourceTransfer(Actor source, Actor destination, Identifier resource, Identifier takeAnimation, Identifier receiveAnimation)
    {
        Action deselect = setSelected(source, false);
        Action resourceTake = resourceTakeAnimated(source, resource, takeAnimation);
        Action resourceReceive = resourceReceiveAnimated(destination, resource, receiveAnimation);
        return new ParallelAction(deselect, resourceTake, resourceReceive);
    }

    private Action gather(Actor gatherer, Actor resource, Actor depot, Identifier property, Identifier gatherAnimation, Identifier depositAnimation)
    {
        Action moveToResource = move(gatherer, resource);
        Action transfer = resourceTransfer(gatherer, resource, property, gatherAnimation, gatherAnimation);
        Action moveToDepot = move(gatherer, depot);
        Action deposit = resourceTransfer(depot, gatherer, property, depositAnimation, depositAnimation);
        Action gather = new SequenceAction(moveToResource, transfer, moveToDepot, deposit);
        return new RepeatedAction(gather);
    }

    private Action gatherGold(Actor actor, Actor resource)
    {
        Actor depot = findByType(actor.getStage(), new Identifier("TownHall"));
        Identifier property = new Identifier("Gold");
        Identifier gatherAnimation = new Identifier("GatherGold");
        Identifier depositAnimation = new Identifier("DepositGold");
        return gather(actor, resource, depot, property, gatherAnimation, depositAnimation);
    }

    private Action gatherWood(Actor actor, Actor resource)
    {
        Actor depot = findByType(actor.getStage(), new Identifier("TownHall"));
        Identifier property = new Identifier("Wood");
        Identifier gatherAnimation = new Identifier("GatherWood");
        Identifier depositAnimation = new Identifier("DepositWood");
        return gather(actor, resource, depot, property, gatherAnimation, depositAnimation);
    }

    private Action create(Stage stage, Identifier type, Identifier id, Vector2 position)
    {
        return new CreateAction(stage, type, unitFactory, id, position);
    }

    private Action setEnabled(Stage stage, Identifier actor, boolean enabled)
    {
        ActionValue value = new ItemReferenceValue(stage, actor, new Identifier("Enabled"));
        ActionModifier modifier = new ConstantModifier(enabled);
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(value, modifier, duration);
    }

    private Action setEnabled(Actor actor, boolean enabled)
    {
        ActionValue value = new ItemValue((Item)actor, new Identifier("Enabled"));
        ActionModifier modifier = new ConstantModifier(enabled);
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(value, modifier, duration);
    }

    private Action build(Actor builder, Identifier building, Stage stage)
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

        return new SequenceAction(acknowledge, moveToSite, deselectBuilder, createFarm, disableFarm, buildFarm, enableFarm, selectBuilder);
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
        Action attack = new ParallelAction(attackAnimation, reduceHealth);

        Action deadAnimation = setAnimation(target, new Identifier("Die"), 0.5f);
        Action deselect = setSelected(target, false);
        Action disable = setEnabled(target, false);
        Action idleAnimation = setAnimation(attacker, new Identifier("Idle"));
        Action die = new ParallelAction(deadAnimation, deselect, disable, idleAnimation);

        Action decompose = setAnimation(target, new Identifier("Decompose"), 10f);
        Action remove = new RemoveAction(target.getStage(), target);
        Action clean = new SequenceAction(decompose, remove);

        return new SequenceAction(move, attack, die, clean);
    }
}
