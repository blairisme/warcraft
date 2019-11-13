/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.data.camera;

import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.object.GameObject;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

/**
 * Instances of this unit test validate the {@link Camera} class.
 *
 * @author Blair Butterworth
 */
public class CameraTest extends GameTestCase
{
    private Camera camera;

    @Before
    public void setup() {
        super.setup();
        DeviceDisplay display = Mockito.mock(DeviceDisplay.class);
        Mockito.when(display.getScaleFactor()).thenReturn(1f);
        camera = new Camera(display);
        respondWithItem(camera);
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(GameObject.class)
            .withDeserializedForm(camera)
            .withSerializedResource("/warcraft/item/camera.json")
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