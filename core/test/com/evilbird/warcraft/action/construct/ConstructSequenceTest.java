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
import com.evilbird.test.testcase.ActionTestCase;
import com.evilbird.warcraft.action.move.MoveToItemAction;
import com.evilbird.warcraft.action.select.DeselectAction;
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
        ConstructAction construct = Mockito.mock(ConstructAction.class);
        ConstructPlaceholder initialize = Mockito.mock(ConstructPlaceholder.class);
        DeselectAction deselect = Mockito.mock(DeselectAction.class);
        MoveToItemAction move = Mockito.mock(MoveToItemAction.class);

        ConstructSequence action = new ConstructSequence(construct, initialize, deselect, move);
        action.setIdentifier(ConstructActions.ConstructBarracks);
        return action;
    }

    @Override
    protected Enum newIdentifier() {
        return ConstructActions.ConstructBarracks;
    }
}