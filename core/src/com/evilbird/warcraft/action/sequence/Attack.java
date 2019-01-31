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
import com.evilbird.engine.action.common.AudibleAction;
import com.evilbird.engine.action.common.RepeatedAudibleAction;
import com.evilbird.engine.action.common.ReplacementAction;
import com.evilbird.engine.action.framework.*;
import com.evilbird.engine.common.lang.Directionable;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.specialized.animated.Animated;
import com.evilbird.engine.item.specialized.animated.Audible;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.component.*;
import com.evilbird.warcraft.item.common.capability.Destroyable;
import com.evilbird.warcraft.item.common.capability.Movable;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;

import static com.evilbird.engine.action.utilities.ActionPredicates.noError;
import static com.evilbird.engine.common.function.Suppliers.constantValue;
import static com.evilbird.engine.item.ItemSuppliers.isAlive;
import static com.evilbird.warcraft.action.common.ActionPredicates.withinRange;

/**
 * Instances of this factory create {@link Action Actions} that cause a given
 * {@link Item} to attack another, after first moving within attack range.
 *
 * @author Blair Butterworth
 */
public class Attack implements ActionProvider
{
    private ItemFactory itemFactory;

    @Inject
    public Attack(ItemFactory itemFactory) {
        this.itemFactory = itemFactory;
    }

    @Override
    public Action get(ActionIdentifier action, ActionContext context) {
        Action attack = attack((Combatant)context.getItem(), context.getTarget(), context.showFeedback());
        return new ReplacementAction(context.getItem(), attack);
    }

    private Action attack(Combatant attacker, Item target, boolean showFeedback) {
        Action feedback = feedback(attacker, target, showFeedback);
        Action attack = attack(attacker, target);
        Action complete = die(target);
        Action initiate = new ParallelAction(feedback, attack);
        return new SequenceAction(initiate, complete);
    }

    private Action feedback(Item attacker, Item target, boolean include) {
        Action effect = new ConfirmAction(itemFactory, target);
        Action sound = new AudibleAction((Audible)attacker, UnitSound.Acknowledge);
        Action feedback = new ParallelAction(effect, sound);
        return new OptionalAction(constantValue(include),feedback);
    }

    private Action attack(Combatant attacker, Item target) {
        BasicAction reposition = reposition(attacker, target);
        BasicAction damage = damage(attacker, target);
        return new PrerequisiteAction(damage, reposition, withinRange(attacker, target));
    }

    private BasicAction reposition(Item attacker, Item target) {
        BasicAction reposition = new MoveAction((Movable)attacker, target);
        BasicAction animation = new AnimatedAction(reposition, (Animated)attacker, UnitAnimation.Move, UnitAnimation.Idle);
        BasicAction move = new RequirementAction(animation, noError());
        BasicAction reorient = new DirectionAction((Directionable)attacker, target);
        return new SequenceAction(move, reorient);
    }

    private BasicAction damage(Item attacker, Item target) {
        Action attack = new AttackAction((Combatant)attacker, (Destroyable)target);
        Action sound = new RepeatedAudibleAction(attacker, UnitSound.Attack, 0.5f, isAlive((Destroyable)target));
        Action damage = new ParallelAction(attack, sound);
        return new AnimatedAction(damage, (Animated)attacker, UnitAnimation.Attack, UnitAnimation.Idle);
    }

    private Action die(Item target) {
        Action die = new DieAction(target);
        return new ReplacementAction(target, die);
    }
}
