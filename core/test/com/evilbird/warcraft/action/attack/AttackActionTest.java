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
import com.evilbird.test.testcase.ActionTestCase;
import com.evilbird.warcraft.action.move.MoveAction;
import com.evilbird.warcraft.action.move.MoveFactory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Instances of this unit test validate the {@link AttackAction} class.
 *
 * @author Blair Butterworth
 */
public class AttackActionTest extends ActionTestCase
{
    @Override
    protected Action newAction() {
        return new AttackAction(getMoveFactory());
    }

    @Override
    protected Enum newActionId() {
        return AttackActions.AttackMelee;
    }

    private MoveFactory getMoveFactory() {
        MoveAction moveAction = mock(MoveAction.class);
        MoveFactory moveFactory = mock(MoveFactory.class);
        when(moveFactory.get(any())).thenReturn(moveAction);
        return moveFactory;
    }
}