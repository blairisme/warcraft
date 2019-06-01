/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.resource;

import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.item.Item;
import com.evilbird.test.data.item.TestResources;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import com.evilbird.warcraft.item.unit.UnitType;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Instances of this unit test validate logic in the {@link Resource} class.
 *
 * @author Blair Butterworth
 */
public class ResourceTest extends GameTestCase
{
    private Resource resource;

    @Before
    public void setup() {
        super.setup();
        resource = TestResources.newTestResource(new TextIdentifier("goldmine"), UnitType.GoldMine);
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(Item.class)
            .withDeserializedForm(resource)
            .withSerializedResource("/warcraft/item/resource.json")
            .verify();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(Resource.class)
            .withMockedTransientFields()
            .excludeTransientFields()
            .verify();
    }
}
