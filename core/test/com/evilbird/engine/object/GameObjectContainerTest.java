/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.object;

import com.evilbird.engine.object.spatial.GameObjectGraph;
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
 * Instances of this unit test validate the {@link GameObjectContainer} class.
 *
 * @author Blair Butterworth
 */
public class GameObjectContainerTest extends GameTestCase
{
    private GameObjectContainer root;

    @Before
    public void setup() {
        super.setup();
        root = TestItemRoots.newTestRoot("world");
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(GameObjectContainer.class)
            .withDeserializedForm(root)
            .withSerializedResource("/item/itemroot.json")
            .verify();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(GameObjectContainer.class)
            .withMockedTransientFields()
            .withMockedTransientFields(GameObjectGraph.class)
            .withMockedTransientFields(GameObjectGroup.class)
            .excludeTransientFields()
            .suppress(REFERENCE_EQUALITY, NONFINAL_FIELDS)
            .verify();
    }
}