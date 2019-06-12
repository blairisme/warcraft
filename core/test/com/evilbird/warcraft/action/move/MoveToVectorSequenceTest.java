/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.test.testcase.ActionTestCase;
import org.mockito.Mockito;

/**
 * Instances of this unit test validate the {@link MoveToVectorSequence} class.
 *
 * @author Blair Butterworth
 */
public class MoveToVectorSequenceTest extends ActionTestCase
{
    @Override
    protected Action newAction() {
        return new MoveToVectorSequence(Mockito.mock(EventQueue.class));
    }

    @Override
    protected Enum newIdentifier() {
        return MoveActions.MoveToLocation;
    }
}