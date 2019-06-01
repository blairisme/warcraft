/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.data.player;

import com.evilbird.engine.item.Item;
import com.evilbird.test.data.item.TestPlayers;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Instances of this unit test validate the {@link Player} class.
 *
 * @author Blair Butterworth
 */
public class PlayerTest extends GameTestCase
{
    private Player player;

    @Before
    public void setup() {
        super.setup();
        player = TestPlayers.newTestPlayer("Player2");
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(Item.class)
            .withDeserializedForm(player)
            .withSerializedResource("/warcraft/item/player.json")
            .verify();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(Player.class)
            .withMockedTransientFields()
            .excludeTransientFields()
            .verify();
    }
}