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
        return new AttackSequence(Mockito.mock(AttackReporter.class));
    }

    @Override
    protected Enum newIdentifier() {
        return AttackActions.AttackMelee;
    }
}