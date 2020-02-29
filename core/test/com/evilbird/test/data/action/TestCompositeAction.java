/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.test.data.action;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.action.framework.CompositeAction;

/**
 * A concrete instance of the abstract {@link CompositeAction} class, useful
 * for testing purposes.
 *
 * @author Blair Butterworth
 */
public class TestCompositeAction extends CompositeAction
{
    public TestCompositeAction() {
    }

    public TestCompositeAction(BasicAction... actions) {
        super(actions);
    }

    @Override
    public boolean run(float delta) {
        return false;
    }
}
