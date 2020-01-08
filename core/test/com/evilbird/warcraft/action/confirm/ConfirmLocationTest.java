/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.confirm;

import com.evilbird.engine.action.Action;
import com.evilbird.test.testcase.ActionTestCase;
import com.evilbird.warcraft.common.WarcraftPreferences;
import org.mockito.Mockito;

/**
 * Instances of this unit test validate the {@link ConfirmLocation} class.
 *
 * @author Blair Butterworth
 */
public class ConfirmLocationTest extends ActionTestCase
{
    @Override
    protected Action newAction() {
        WarcraftPreferences preferences = Mockito.mock(WarcraftPreferences.class);
        return new ConfirmLocation(objectFactory, preferences);
    }

    @Override
    protected Enum newIdentifier() {
        return ConfirmActions.ConfirmLocation;
    }
}