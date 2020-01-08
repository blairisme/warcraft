/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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