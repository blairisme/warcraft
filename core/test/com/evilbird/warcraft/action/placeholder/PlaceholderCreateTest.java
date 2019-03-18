/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.placeholder;

import com.evilbird.engine.action.Action;
import com.evilbird.test.testcase.ActionTestCase;

/**
 * Instances of this unit test validate the {@link PlaceholderCreate} class.
 *
 * @author Blair Butterworth
 */
public class PlaceholderCreateTest extends ActionTestCase
{
    @Override
    protected Action newAction() {
        PlaceholderCreate action = new PlaceholderCreate(itemFactory);
        action.setIdentifier(PlaceholderActions.AddBarracksPlaceholder);
        return action;
    }

    @Override
    protected Enum newActionId() {
        return PlaceholderActions.AddBarracksPlaceholder;
    }
}