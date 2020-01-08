/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.object.spatial;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.object.GameObject;
import com.evilbird.test.data.item.TestItems;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

/**
 * Instances of this unit test validate the {@link GameObjectGraph} class.
 *
 * @author Blair Butterworth
 */
public class GameObjectGraphTest extends GameTestCase
{
    private GameObjectGraph graph;

    @Before
    public void setup() {
        super.setup();
        GameObject gameObject1 = TestItems.newItem("1");
        GameObject gameObject2 = TestItems.newItem("2");

        graph = new GameObjectGraph(32, 32, 10, 10);
        graph.addOccupants(graph.getNode(new Vector2(10, 0)), gameObject1);
        graph.addOccupants(graph.getNode(new Vector2(0, 10)), gameObject2);
    }

    @Test
    public void getAdjacentNodesTest() {
        Collection<GameObjectNode> expected = Arrays.asList(
                itemNode(11, 1, 1), itemNode(21, 2, 1), itemNode(31, 3, 1),
                itemNode(12, 1, 2),                     itemNode(32, 3, 2),
                itemNode(13, 1, 3), itemNode(23, 2, 3), itemNode(33, 3, 3));
        Collection<GameObjectNode> actual = graph.getAdjacentNodes(
                new GridPoint2(2, 2), new GridPoint2(1, 1));

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertTrue(expected.containsAll(actual));
    }

    @Test
    public void getAdjacentNodesEdgeTest() {
        Collection<GameObjectNode> expected = Arrays.asList(
                itemNode(10, 1, 0),                     itemNode(30, 3, 0),
                itemNode(11, 1, 1), itemNode(21, 2, 1), itemNode(31, 3, 1));
        Collection<GameObjectNode> actual = graph.getAdjacentNodes(
                new GridPoint2(2, 0), new GridPoint2(1, 1));

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertTrue(expected.containsAll(actual));
    }

    @Test
    public void getAdjacentNodesWorldTest() {
        Collection<GameObjectNode> expected = Arrays.asList(
                itemNode(11, 1, 1), itemNode(21, 2, 1), itemNode(31, 3, 1),
                itemNode(12, 1, 2),                     itemNode(32, 3, 2),
                itemNode(13, 1, 3), itemNode(23, 2, 3), itemNode(33, 3, 3));
        Collection<GameObjectNode> actual = graph.getAdjacentNodes(
                new Vector2(32 * 2, 32 * 2), new Vector2(32, 32));

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertTrue(expected.containsAll(actual));
    }

    private GameObjectNode itemNode(int index, int x, int y) {
        return new GameObjectNode(index, new GridPoint2(x, y));
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(GameObjectGraph.class)
            .withDeserializedForm(graph)
            .withSerializedResource("/item/itemgraph.json")
            .verify();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(GameObjectGraph.class)
            .withMockedTransientFields()
            .excludeTransientFields()
            .verify();
    }
}