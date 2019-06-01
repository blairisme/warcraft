/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.test.data.item.TestItemRoots;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
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
        root = TestItemRoots.newTestRoot("world");
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(ItemRoot.class)
            .withDeserializedForm(root)
            .withSerializedResource("/item/itemroot.json")
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