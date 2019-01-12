/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.common.AnimateAction;
import com.evilbird.engine.action.common.ReplacementAction;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.Animated;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.component.AnimatedMoveAction;
import com.evilbird.warcraft.action.component.AttackAction;
import com.evilbird.warcraft.action.component.DieAction;
import com.evilbird.warcraft.action.component.MoveAction;
import com.evilbird.warcraft.item.common.capability.Destructible;
import com.evilbird.warcraft.item.common.capability.Movable;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;

/**
 * Instances of this class represent an {@link Action} that attacks a given
 * item.
 *
 * @author Blair Butterworth
 */
public class AttackSequence implements ActionProvider
{
    @Inject
    public AttackSequence() {
    }

    @Override
    public Action get(ActionIdentifier action, Item item, Item target, UserInput input) {
        Action attack = attack(item, target);
        return new ReplacementAction(item, attack);
    }

    private Action attack(Item attacker, Item target) {
        Action move = new AnimatedMoveAction(attacker, target);

        Action animate = new AnimateAction((Animated)attacker, UnitAnimation.Attack);
        Action reduceHealth = new AttackAction((Combatant)attacker, (Destructible)target);
        Action attack = new ParallelAction(animate, reduceHealth);

        Action idleAnimation = new AnimateAction((Animated)attacker, UnitAnimation.Idle);
        Action die = new DieAction(target);

        return new SequenceAction(move, attack, idleAnimation, die);
    }
}
