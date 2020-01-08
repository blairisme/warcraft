/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.resource;

import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.object.GameObject;
import com.evilbird.test.data.item.TestResources;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import com.evilbird.warcraft.object.unit.UnitType;
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
        SerializationVerifier.forClass(GameObject.class)
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
