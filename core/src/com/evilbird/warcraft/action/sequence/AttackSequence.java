package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.action.common.AnimateAction;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.Animated;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.action.common.AttackAction;
import com.evilbird.warcraft.action.common.DieAction;
import com.evilbird.warcraft.action.common.MoveAction;
import com.evilbird.engine.action.common.ReplacementAction;
import com.evilbird.warcraft.item.common.capability.Destructible;
import com.evilbird.warcraft.item.common.capability.Movable;
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
    private MoveSequence moveSequence;

    @Inject
    public AttackSequence(MoveSequence moveSequence) {
        this.moveSequence = moveSequence;
    }

    @Override
    public Action get(ActionType action, Item item, Item target, UserInput input) {
        Action attack = attack(item, target);
        return new ReplacementAction(item, attack);
    }

    private Action attack(Item attacker, Item target)
    {
        Action moveAnimation = new AnimateAction((Animated)attacker, UnitAnimation.Move);
        Action moveAction = new MoveAction((Movable)attacker, target);
        Action move = new ParallelAction(moveAnimation, moveAction);

        Action attackAnimation = new AnimateAction((Animated)attacker, UnitAnimation.Attack);
        Action reduceHealth = new AttackAction((Combatant)attacker, (Destructible)target);
        Action attack = new ParallelAction(attackAnimation, reduceHealth);

        Action idleAnimation = new AnimateAction((Animated)attacker, UnitAnimation.Idle);
        Action die = new DieAction(target);

        return new SequenceAction(move, attack, idleAnimation, die);
    }
}
