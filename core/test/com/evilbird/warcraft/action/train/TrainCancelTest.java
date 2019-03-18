/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.train;

import com.evilbird.engine.action.Action;
import com.evilbird.test.testcase.ActionTestCase;

/**
 * Instances of this unit test validate the {@link TrainCancel} class.
 *
 * @author Blair Butterworth
 */
public class TrainCancelTest extends ActionTestCase
{
    @Override
    protected Action newAction() {
        TrainCancel action = new TrainCancel();
        action.setIdentifier(TrainActions.TrainFootmanCancel);
        return action;
    }

    @Override
    protected Enum newActionId() {
        return TrainActions.TrainFootmanCancel;
    }
}