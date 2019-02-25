/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.test.mock.item.ItemMocks;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import com.evilbird.warcraft.item.data.DataType;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static nl.jqno.equalsverifier.Warning.REFERENCE_EQUALITY;

/**
 * Instances of this unit test validate the {@link ItemRoot} class.
 *
 * @author Blair Butterworth
 */
public class ItemRootTest extends GameTestCase
{
    private ItemRoot root;

    @Before
    public void setup() {
        super.setup();

        ItemGraph graph = new ItemGraph(32, 32, 128, 128);

        ItemGroup group1 = new ItemGroup();
        group1.setId(new TextIdentifier("Player1"));
        group1.setType(DataType.Player);
        group1.addItem(ItemMocks.newItem("1"));
        group1.addItem(ItemMocks.newItem("2"));

        ItemGroup group2 = new ItemGroup();
        group2.setType(DataType.Player);
        group2.setId(new TextIdentifier("Player2"));
        group2.addItem(ItemMocks.newItem("3"));
        group2.addItem(ItemMocks.newItem("4"));

        root = new ItemRoot();
        root.setSpatialGraph(graph);
        root.addItem(group1);
        root.addItem(group2);
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(ItemRoot.class)
            .withDeserializedForm(root)
            .withSerializedResource("/itemroot.json")
            .verify();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(ItemRoot.class)
            .withMockedTransientFields()
            .withMockedTransientFields(ItemGraph.class)
            .withMockedTransientFields(ItemGroup.class)
            .excludeTransientFields()
            .suppress(REFERENCE_EQUALITY, NONFINAL_FIELDS)
            .verify();
    }
}