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

/**
 * Instances of this unit test validate the {@link ConstructAction} class.
 *
 * @author Blair Butterworth
 */
public class ConstructActionTest extends ActionTestCase
{
    @Override
    protected Action newAction() {
        ConstructAction action = new ConstructAction(itemFactory);
        action.setIdentifier(ConstructActions.ConstructBarracks);
        return action;
    }

    @Override
    protected Enum newActionId() {
        return ConstructActions.ConstructBarracks;
    }
}