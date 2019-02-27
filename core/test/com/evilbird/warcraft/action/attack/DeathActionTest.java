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
import com.evilbird.test.verifier.SerializationVerifier;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class DeathActionTest
{
    private Item item;
    private Item target;
    private DeathAction action;

    @Before
    public void setup() {
        item = TestItems.newItem("footman");
        target = TestItems.newItem("grunt");

        action = new DeathAction();
        action.setItem(item);
        action.setTarget(target);
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(DeathAction.class)
            .withDeserializedForm(action)
            .withSerializedResource("/warcraft/action/deathaction.json")
            .verify();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(DeathAction.class)
            .withMockedTransientFields(Item.class)
            .excludeTransientFields()
            .verify();
    }
}