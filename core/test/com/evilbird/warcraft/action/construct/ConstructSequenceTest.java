/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.action.Action;
import com.evilbird.test.testcase.ActionTestCase;
import com.evilbird.warcraft.action.move.MoveToItemAction;
import com.evilbird.warcraft.action.selection.DeselectAction;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

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
        ConstructBuilding initialize = Mockito.mock(ConstructBuilding.class);
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

    @Test
    @Ignore
    @Override
    public void serializeTest() {
    }
}