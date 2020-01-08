/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.object.GameObject;
import com.evilbird.test.data.item.TestGatherers;
import com.evilbird.test.testcase.ActionTestCase;
import com.evilbird.warcraft.object.unit.UnitType;
import org.mockito.Mockito;

/**
 * Instances of this unit test validate the {@link GatherWood} class.
 *
 * @author Blair Butterworth
 */
public class GatherCancelTest extends ActionTestCase
{
    @Override
    protected Action newAction() {
        GatherCancel action = new GatherCancel(Mockito.mock(GatherEvents.class));
        action.setIdentifier(GatherActions.GatherCancel);
        return action;
    }

    @Override
    protected Enum newIdentifier() {
        return GatherActions.GatherCancel;
    }

    @Override
    protected GameObject newItem() {
        return TestGatherers.newTestGatherer(new TextIdentifier("item"), UnitType.Peasant);
    }
}