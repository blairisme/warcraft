/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.item.Item;
import com.evilbird.test.data.item.TestItems;
import com.evilbird.test.verifier.EqualityVerifier;
import org.junit.Before;
import org.junit.Test;

public class AttackActionTest
{
    private Item item;
    private Item target;
    private AttackAction action;

    @Before
    public void setup() {
        item = TestItems.newItem("footman");
        target = TestItems.newItem("grunt");

        action = new AttackAction();
        action.setItem(item);
        action.setTarget(target);
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(AttackAction.class)
                .withMockedTransientFields(Item.class)
                .excludeTransientFields()
                .verify();
    }
}