/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.test.testcase.ActionTestCase;
import org.mockito.Mockito;

/**
 * Instances of this unit test validate the {@link ConstructSequence} class.
 *
 * @author Blair Butterworth
 */
public class ConstructSequenceTest extends ActionTestCase
{
    @Override
    protected Action newAction() {
        ConstructSequence action = new ConstructSequence(Mockito.mock(EventQueue.class));
        action.setIdentifier(ConstructActions.ConstructBarracks);
        return action;
    }

    @Override
    protected Enum newIdentifier() {
        return ConstructActions.ConstructBarracks;
    }
}