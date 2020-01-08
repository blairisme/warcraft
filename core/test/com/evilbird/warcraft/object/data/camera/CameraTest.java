/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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