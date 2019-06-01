/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.common;

import com.evilbird.engine.item.Item;
import com.evilbird.test.data.item.TestItems;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.warcraft.item.unit.UnitSound;
import org.junit.Before;
import org.junit.Test;

import static com.evilbird.engine.action.common.RepeatedAudibleAction.playRepeat;

/**
 * Instances of this unit test validate the {@link RepeatedAudibleAction} class.
 *
 * @author Blair Butterworth
 */
public class RepeatedAudibleActionTest
{
    private RepeatedAudibleAction action;

    @Before
    public void setup() {
        action = playRepeat(UnitSound.ChopWood, 5, 3.1f);
        action.setItem(TestItems.newItem("repeatedaudibleaction"));
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(RepeatedAudibleAction.class)
            .withMockedTransientFields(Item.class)
            .excludeTransientFields()
            .verify();
    }
}