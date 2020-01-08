/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.object.spatial;

import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.test.verifier.EqualityVerifier;
import org.junit.Before;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link GameObjectNode} class.
 *
 * @author Blair Butterworth
 */
public class GameObjectNodeTest
{
    private GameObjectNode node;

    @Before
    public void setup() {
        node = new GameObjectNode(0, new GridPoint2(0, 0));
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(GameObjectNode.class)
            .withMockedTransientFields()
            .excludeTransientFields()
            .verify();
    }
}