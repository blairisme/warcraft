/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.spatial;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.item.Item;
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
 * Instances of this unit test validate the {@link ItemGraph} class.
 *
 * @author Blair Butterworth
 */
public class ItemGraphTest extends GameTestCase
{
    private ItemGraph graph;

    @Before
    public void setup() {
        super.setup();
        Item item1 = TestItems.newItem("1");
        Item item2 = TestItems.newItem("2");

        graph = new ItemGraph(32, 32, 10, 10);
        graph.addOccupants(graph.getNode(new Vector2(10, 0)), item1);
        graph.addOccupants(graph.getNode(new Vector2(0, 10)), item2);
    }

    @Test
    public void getAdjacentNodesTest() {
        Collection<ItemNode> expected = Arrays.asList(
                itemNode(11, 1, 1), itemNode(21, 2, 1), itemNode(31, 3, 1),
                itemNode(12, 1, 2),                     itemNode(32, 3, 2),
                itemNode(13, 1, 3), itemNode(23, 2, 3), itemNode(33, 3, 3));
        Collection<ItemNode> actual = graph.getAdjacentNodes(
                new GridPoint2(2, 2), new GridPoint2(1, 1));

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertTrue(expected.containsAll(actual));
    }

    @Test
    public void getAdjacentNodesEdgeTest() {
        Collection<ItemNode> expected = Arrays.asList(
                itemNode(10, 1, 0),                     itemNode(30, 3, 0),
                itemNode(11, 1, 1), itemNode(21, 2, 1), itemNode(31, 3, 1));
        Collection<ItemNode> actual = graph.getAdjacentNodes(
                new GridPoint2(2, 0), new GridPoint2(1, 1));

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertTrue(expected.containsAll(actual));
    }

    @Test
    public void getAdjacentNodesWorldTest() {
        Collection<ItemNode> expected = Arrays.asList(
                itemNode(11, 1, 1), itemNode(21, 2, 1), itemNode(31, 3, 1),
                itemNode(12, 1, 2),                     itemNode(32, 3, 2),
                itemNode(13, 1, 3), itemNode(23, 2, 3), itemNode(33, 3, 3));
        Collection<ItemNode> actual = graph.getAdjacentNodes(
                new Vector2(32 * 2, 32 * 2), new Vector2(32, 32));

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertTrue(expected.containsAll(actual));
    }

    private ItemNode itemNode(int index, int x, int y) {
        return new ItemNode(index, new GridPoint2(x, y));
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(ItemGraph.class)
            .withDeserializedForm(graph)
            .withSerializedResource("/item/itemgraph.json")
            .verify();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(ItemGraph.class)
            .withMockedTransientFields()
            .excludeTransientFields()
            .verify();
    }
}