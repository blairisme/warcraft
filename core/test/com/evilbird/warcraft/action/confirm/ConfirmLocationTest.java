/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.confirm;

import com.evilbird.engine.action.Action;
import com.evilbird.test.testcase.ActionTestCase;
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
        return new ConfirmLocation(Mockito.mock(ConfirmReporter.class));
    }

    @Override
    protected Enum newActionId() {
        return ConfirmActions.ConfirmLocation;
    }
}