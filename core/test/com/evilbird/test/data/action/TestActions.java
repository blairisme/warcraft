/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.test.data.action;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.lang.TextIdentifier;

public class TestActions
{
    private TestActions() {
    }

    public static Action newAction(String id) {
        TestBasicAction result = new TestBasicAction();
        result.setIdentifier(new TextIdentifier(id));
        return result;
    }
}
