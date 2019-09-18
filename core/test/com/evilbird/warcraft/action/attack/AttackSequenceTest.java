/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.test.testcase.ActionTestCase;
import com.evilbird.warcraft.action.common.death.DeathAction;
import com.evilbird.warcraft.action.move.MoveToItemAction;
import com.evilbird.warcraft.action.move.MoveWithinRangeAction;
import org.mockito.Mockito;

/**
 * Instances of this unit test validate the {@link AttackSequence} class.
 *
 * @author Blair Butterworth
 */
public class AttackSequenceTest extends ActionTestCase
{
    @Override
    protected Action newAction() {
        MoveToItemAction meleeMove = Mockito.mock(MoveToItemAction.class);
        MoveWithinRangeAction rangedMove = Mockito.mock(MoveWithinRangeAction.class);
        MeleeAttack meleeAttack = Mockito.mock(MeleeAttack.class);
        RangedAttack rangedAttack = Mockito.mock(RangedAttack.class);
        DeathAction death = Mockito.mock(DeathAction.class);
        EventQueue events = Mockito.mock(EventQueue.class);

        AttackSequence action = new AttackSequence(meleeMove, rangedMove, meleeAttack, rangedAttack, death, events);
        action.setIdentifier(AttackActions.Attack);
        return action;
    }

    @Override
    protected Enum newIdentifier() {
        return AttackActions.Attack;
    }
}