/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.object;

import com.evilbird.test.data.item.TestPlayers;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Instances of this unit test validate the {@link GameObjectGroup} class.
 *
 * @author Blair Butterworth
 */
public class GameObjectGroupTest extends GameTestCase
{
    private GameObjectGroup group;

    @Before
    public void setup() {
        super.setup();
        group = TestPlayers.newTestPlayer("player1");
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(GameObject.class)
            .withDeserializedForm(group)
            .withSerializedResource("/item/itemgroup.json")
            .verify();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(GameObjectGroup.class)
            .withMockedTransientFields()
            .excludeTransientFields()
            .verify();
    }
}