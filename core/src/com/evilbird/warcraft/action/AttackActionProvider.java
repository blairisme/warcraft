package com.evilbird.warcraft.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.DelayedAction;
import com.evilbird.engine.action.ModifyAction;
import com.evilbird.engine.action.ParallelAction;
import com.evilbird.engine.action.RemoveAction;
import com.evilbird.engine.action.SequenceAction;
import com.evilbird.engine.action.duration.ActionDuration;
import com.evilbird.engine.action.duration.PredicateDuration;
import com.evilbird.engine.action.duration.TimeDuration;
import com.evilbird.engine.action.modifier.ActionModifier;
import com.evilbird.engine.action.modifier.DeltaModifier;
import com.evilbird.engine.action.modifier.DeltaType;
import com.evilbird.engine.action.value.ActionValue;
import com.evilbird.engine.action.value.ItemValue;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemProperty;
import com.evilbird.engine.item.specialized.AnimationIdentifier;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitProperties;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
//TODO: Read attack damage from unit properties
//TODO: Randomly choose attack damage between min and max
//TODO: Negate attack by armour of target
public class AttackActionProvider implements ActionProvider
{
    private MoveActionProvider moveActionProvider;
    private AnimateActionProvider animateActionProvider;
    private DisableActionProvider disableActionProvider;
    private SelectionActionProvider selectionActionProvider;

    @Inject
    public AttackActionProvider(
        AnimateActionProvider animateActionProvider,
        DisableActionProvider disableActionProvider,
        MoveActionProvider moveActionProvider,
        SelectionActionProvider selectionActionProvider)
    {
        this.moveActionProvider = moveActionProvider;
        this.animateActionProvider = animateActionProvider;
        this.disableActionProvider = disableActionProvider;
        this.selectionActionProvider = selectionActionProvider;
    }

    @Override
    public Action get(Item item, Item target, UserInput input)
    {
        return get(item, target);
    }

    public Action get(Item attacker, Item target)
    {
        Action move = moveActionProvider.get(attacker, target);

        Action attackAnimation = animateActionProvider.get(attacker, UnitAnimation.Attack);
        Action reduceHealth = newAttackAction(target);
        Action attack = new ParallelAction(attackAnimation, reduceHealth);

        Action deadAnimation = newAnimationAction(target, UnitAnimation.Die, 0.5f);
        Action deselect = selectionActionProvider.get(target, false);
        Action disable = disableActionProvider.get(target, false);
        Action idleAnimation = animateActionProvider.get(attacker, UnitAnimation.Idle);
        Action die = new ParallelAction(deadAnimation, deselect, disable, idleAnimation);

        Action decompose = newAnimationAction(target, UnitAnimation.Decompose, 10f);
        Action remove = new RemoveAction(target);
        Action clean = new SequenceAction(decompose, remove);

        return new SequenceAction(move, attack, die, clean);
    }

    private Action newAnimationAction(Item item, AnimationIdentifier animation, float time)
    {
        Action animate = animateActionProvider.get(item, animation);
        return new DelayedAction(animate, new TimeDuration(time));
    }

    private Action newAttackAction(Item target)
    {
        ItemProperty health = UnitProperties.Health;
        ActionValue value = new ItemValue(target, health);
        ActionModifier modifier = new DeltaModifier(-10f, DeltaType.PerSecond, 0f, 100f);
        ActionDuration duration = new PredicateDuration(target, health, 0f);
        return new ModifyAction(value, modifier, duration);
    }
}
