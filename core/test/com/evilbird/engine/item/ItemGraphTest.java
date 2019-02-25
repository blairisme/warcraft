/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.test.mock.item.ItemMocks;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

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
        Item item1 = ItemMocks.newItem("1");
        Item item2 = ItemMocks.newItem("2");

        graph = new ItemGraph(32, 32, 2, 2);
        graph.addOccupants(graph.getNode(new Vector2(10, 0)), item1);
        graph.addOccupants(graph.getNode(new Vector2(0, 10)), item2);
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(ItemGraph.class)
            .withDeserializedForm(graph)
            .withSerializedResource("/itemgraph.json")
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