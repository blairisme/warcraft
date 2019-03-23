/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.lang;

import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

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