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
 * Instances of this unit test validate the {@link TrainAction} class.
 *
 * @author Blair Butterworth
 */
public class TrainActionTest extends ActionTestCase
{
    @Override
    protected Action newAction() {
        TrainAction action = new TrainAction(itemFactory);
        action.setIdentifier(TrainActions.TrainFootman);
        return action;
    }

    @Override
    protected Enum newActionId() {
        return TrainActions.TrainFootman;
    }
}
