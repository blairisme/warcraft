/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.camera;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.item.Item;
import com.evilbird.test.testcase.ActionTestCase;
import com.evilbird.warcraft.item.data.camera.Camera;

import static com.evilbird.test.data.assets.TestDevices.newTestDevice;

/**
 * Instances of this unit test validate the {@link PanAction} class.
 *
 * @author Blair Butterworth
 */
public class PanActionTest extends ActionTestCase
{
    @Override
    protected Action newAction() {
        return new PanAction();
    }

    @Override
    protected Enum newIdentifier() {
        return CameraActions.Pan;
    }

    @Override
    protected Item newItem() {
        Camera camera = new Camera(newTestDevice());
        camera.setIdentifier(new TextIdentifier("item"));
        return camera;
    }
}