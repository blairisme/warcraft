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
import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.test.data.item.TestItems;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Instances of this unit test validate the {@link com.evilbird.engine.item.spatial.ItemGraph} class.
 *
 * @author Blair Butterworth
 */
public class ItemGraphTest extends GameTestCase
{
    private com.evilbird.engine.item.spatial.ItemGraph graph;

    @Before
    public void setup() {
        super.setup();
        Item item1 = TestItems.newItem("1");
        Item item2 = TestItems.newItem("2");

        graph = new com.evilbird.engine.item.spatial.ItemGraph(32, 32, 2, 2);
        graph.addOccupants(graph.getNode(new Vector2(10, 0)), item1);
        graph.addOccupants(graph.getNode(new Vector2(0, 10)), item2);
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(com.evilbird.engine.item.spatial.ItemGraph.class)
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