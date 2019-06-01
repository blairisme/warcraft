/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.data.camera;

import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link Camera} objects.
 *
 * @author Blair Butterworth
 */
public class CameraFactory implements AssetProvider<Camera>
{
    private Device device;

    @Inject
    public CameraFactory(Device device) {
        this.device = device;
    }

    @Override
    public void load() {
    }

    @Override
    public Camera get() {
        return new Camera(device);
    }
}
