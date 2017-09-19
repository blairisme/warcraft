package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.DelayedAction;
import com.evilbird.engine.action.ParallelAction;
import com.evilbird.engine.action.RemoveAction;
import com.evilbird.engine.action.SequenceAction;
import com.evilbird.engine.action.duration.TimeDuration;
import com.evilbird.engine.action.replacement.AnimateAction;
import com.evilbird.engine.action.replacement.DisableAction;
import com.evilbird.engine.action.replacement.SelectAction;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.Animated;
import com.evilbird.engine.item.specialized.animated.AnimationIdentifier;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.item.unit.Destructible;
import com.evilbird.warcraft.item.unit.Movable;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class AttackSequence implements ActionProvider
{
    @Inject
    public AttackSequence()
    {
    }

    @Override
    public Action get(ActionType action, Item item, Item target, UserInput input)
    {
        return get(item, target);
    }

    private Action get(Item attacker, Item target)
    {
        Action moveAnimation = new AnimateAction((Animated)attacker, UnitAnimation.Move);
        Action moveAction = new com.evilbird.warcraft.action.common.MoveAction((Movable)attacker, target);
        Action move = new ParallelAction(moveAnimation, moveAction);

        Action attackAnimation = new AnimateAction((Animated)attacker, UnitAnimation.Attack);
        Action reduceHealth = new com.evilbird.warcraft.action.common.AttackAction((Combatant)attacker, (Destructible)target);
        Action attack = new ParallelAction(attackAnimation, reduceHealth);

        Action deadAnimation = newAnimationAction(target, UnitAnimation.Die, 0.5f);
        Action deselect = new SelectAction(target, false);
        Action disable = new DisableAction(target, false);
        Action idleAnimation = new AnimateAction((Animated)target, UnitAnimation.Idle);
        Action die = new ParallelAction(deadAnimation, deselect, disable, idleAnimation);

        Action decompose = newAnimationAction(target, UnitAnimation.Decompose, 10f);
        Action remove = new RemoveAction(target);
        Action clean = new SequenceAction(decompose, remove);

        return new SequenceAction(move, attack, die, clean);
    }

    private Action newAnimationAction(Item item, AnimationIdentifier animation, float time)
    {
        Action animate = new AnimateAction((Animated)item, animation);
        return new DelayedAction(animate, new TimeDuration(time));
    }
}
