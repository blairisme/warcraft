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
import com.evilbird.engine.action.ActionContext;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.common.AnimateAction;
import com.evilbird.engine.action.common.AudibleAction;
import com.evilbird.engine.action.common.ReplacementAction;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.specialized.animated.Animated;
import com.evilbird.engine.item.specialized.animated.Audible;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.component.AnimatedMoveAction;
import com.evilbird.warcraft.action.component.AttackAction;
import com.evilbird.warcraft.action.component.ConfirmAction;
import com.evilbird.warcraft.action.component.DieAction;
import com.evilbird.warcraft.item.common.capability.Destructible;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;

/**
 * Instances of this class move an item to a given target and attacks it.
 *
 * @author Blair Butterworth
 */
//TODO: Handle impossible path
//TODO: Handle target walking away
//TODO: Orient towards target if they move
//TODO: Stop attacking if target dead
public class Attack implements ActionProvider
{
    private ItemFactory itemFactory;

    @Inject
    public Attack(ItemFactory itemFactory) {
        this.itemFactory = itemFactory;
    }

    @Override
    public Action get(ActionIdentifier action, ActionContext context) {
        Action attack = attack(context.getItem(), context.getTarget(), context.showFeedback());
        return new ReplacementAction(context.getItem(), attack);
    }

    private Action attack(Item attacker, Item target, boolean feedback) {
        Action repositionAttacker = repositionAttacker(attacker, target, feedback);
        Action performAttack = performAttack(attacker, target);
        Action completeAttack = completeAttack(attacker, target);
        return new SequenceAction(repositionAttacker, performAttack, completeAttack);
    }

    private Action repositionAttacker(Item attacker, Item target, boolean feedback) {
        Action move = new AnimatedMoveAction(attacker, target);
        if (feedback) {
            Action effect = new ConfirmAction(itemFactory, target);
            Action sound = new AudibleAction((Audible) attacker, UnitSound.Acknowledge);
            return new ParallelAction(effect, sound, move);
        }
        return move;
    }

    private Action performAttack(Item attacker, Item target) {
        Action animate = new AnimateAction((Animated)attacker, UnitAnimation.Attack);
        Action reduceHealth = new AttackAction((Combatant)attacker, (Destructible)target);
        return new ParallelAction(animate, reduceHealth);
    }

    private Action completeAttack(Item attacker, Item target) {
        Action attackerIdle = new AnimateAction((Animated)attacker, UnitAnimation.Idle);
        Action targetDead = new DieAction(target);
        return new ParallelAction(attackerIdle, targetDead);
    }
}
