/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.lang;

import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Instances of this unit test validate logic in the {@link GenericIdentifier}
 * class.
 *
 * @author Blair Butterworth
 */
public class GenericIdentifierTest extends GameTestCase
{
    private GenericIdentifier identifier;

    @Before
    public void setup() {
        super.setup();
        identifier = GenericIdentifier.Unknown;
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(Identifier.class)
            .withDeserializedForm(identifier)
            .withSerializedResource("/common/genericidentifier.json")
            .verify();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(GenericIdentifier.class)
            .withMockedTransientFields()
            .excludeTransientFields()
            .verify();
    }
}