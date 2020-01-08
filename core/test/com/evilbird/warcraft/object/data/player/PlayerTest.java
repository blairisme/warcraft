/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.data.player;

import com.evilbird.engine.object.GameObject;
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
        SerializationVerifier.forClass(GameObject.class)
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