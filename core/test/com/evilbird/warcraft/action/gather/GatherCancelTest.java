/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.action.Action;
import com.evilbird.test.testcase.ActionTestCase;

/**
 * Instances of this unit test validate the {@link GatherWood} class.
 *
 * @author Blair Butterworth
 */
public class GatherCancelTest extends ActionTestCase
{
    @Override
    protected Action newAction() {
        GatherCancel action = new GatherCancel();
        action.setIdentifier(GatherActions.GatherCancel);
        return action;
    }

    @Override
    protected Enum newIdentifier() {
        return GatherActions.GatherCancel;
    }
}