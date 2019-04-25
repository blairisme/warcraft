/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.spatial;

import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.test.verifier.EqualityVerifier;
import org.junit.Before;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link ItemNode} class.
 *
 * @author Blair Butterworth
 */
public class ItemNodeTest
{
    private ItemNode node;

    @Before
    public void setup() {
        node = new ItemNode(0, new GridPoint2(0, 0));
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(ItemNode.class)
            .withMockedTransientFields()
            .excludeTransientFields()
            .verify();
    }
}