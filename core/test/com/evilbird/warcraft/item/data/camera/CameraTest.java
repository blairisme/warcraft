/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.data.camera;

import com.evilbird.engine.item.Item;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class CameraTest extends GameTestCase
{
    private Camera camera;

    @Before
    public void setup() {
        super.setup();
        camera = new Camera();
        respondWithItem(camera);
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(Item.class)
            .withDeserializedForm(camera)
            .withSerializedResource("/item/camera.json")
            .verify();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(Camera.class)
            .withMockedTransientFields()
            .excludeTransientFields()
            .verify();
    }
}