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
import com.evilbird.warcraft.action.move.MoveToItemSequence;
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
        MoveToItemSequence move = Mockito.mock(MoveToItemSequence.class);
        MeleeAttack melee = Mockito.mock(MeleeAttack.class);
        RangedAttack ranged = Mockito.mock(RangedAttack.class);
        DeathAction death = Mockito.mock(DeathAction.class);

        AttackSequence action = new AttackSequence(move, melee, ranged, death);
        action.setIdentifier(AttackActions.Attack);
        return action;
    }

    @Override
    protected Enum newIdentifier() {
        return AttackActions.Attack;
    }
}