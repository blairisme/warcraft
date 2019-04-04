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
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.item.Item;
import com.evilbird.test.data.item.TestBuildings;
import com.evilbird.test.testcase.ActionTestCase;
import com.evilbird.warcraft.item.unit.UnitType;
import org.junit.Ignore;

/**
 * Instances of this unit test validate the {@link ConstructCancel} class.
 *
 * @author Blair Butterworth
 */
@Ignore
public class ConstructCancelTest extends ActionTestCase
{
    @Override
    protected Action newAction() {
        ConstructCancel action = new ConstructCancel();
        action.setIdentifier(ConstructActions.ConstructBarracksCancel);
        return action;
    }

    @Override
    protected Enum newActionId() {
        return ConstructActions.ConstructBarracksCancel;
    }

    @Override
    protected Item newItem() {
        return TestBuildings.newTestBuilding(new TextIdentifier("item"), UnitType.Barracks);
    }
}