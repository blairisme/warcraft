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
import com.evilbird.engine.item.Item;
import com.evilbird.test.data.item.TestItems;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ConfirmLocationTest extends GameTestCase
{
    private Item item;
    private Item confirm;
    private ConfirmLocation action;

    @Before
    public void setup() {
        super.setup();

        item = TestItems.newItem("map");
        confirm = TestItems.newItem("confirm");

        action = new ConfirmLocation(itemFactory);
        action.setItem(item);

        respondWithAction(action);
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(Action.class)
            .withDeserializedForm(action)
            .withSerializedResource("/warcraft/action/confirmlocationaction.json")
            .verify();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(Action.class)
            .withMockedTransientFields(Item.class)
            .excludeTransientFields()
            .verify();
    }
}